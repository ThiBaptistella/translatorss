package au.com.translatorss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.translatorss.bean.Quotation;
import au.com.translatorss.service.TranslatorQuotationService;
import com.amazonaws.services.devicefarm.model.BillingMethod;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.com.translatorss.paypal.configuration.PaypalSuccess;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "paypal")
public class PaypalController {

    private static final Logger LOGGER = Logger.getLogger(PaypalController.class.getName());

    private static final String INTENT_SALE = "sale";

    private static Map<String, String> map = new HashMap<String, String>();

    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.clientSecret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Value("${donation.value}")
    private Float donationValue;

    private TranslatorQuotationService quotationService;

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(ModelMap modelMap, HttpServletRequest request) {
        PaypalSuccess ps = new PaypalSuccess();
        modelMap.put("result", ps.getPaypal(request));
        return "success";
    }

//    Can be converted to REST method and used for other operations while further development
//    @RequestMapping("/checkout")
//    public void checkoutSale(@RequestParam(required=false, value = "quotationId") Long quotationId, @RequestParam(required=false, value = "isDonation") Boolean isDonation, HttpServletRequest request, HttpServletResponse response) {
    public void checkoutSale(Long quotationId, Boolean isDonation, String processPaymentUri, String cancelPaymentUri, String guid, HttpServletRequest request, HttpServletResponse response) {
        Quotation quotation = quotationService.find(quotationId);
        try {
            checkout_base(request, response, INTENT_SALE, quotation.getValue().floatValue(), "Payment for translating services", isDonation, processPaymentUri, cancelPaymentUri, guid);
        } catch (Exception e) {
            //TODO: handle properly
            e.printStackTrace();
        }
    }

	public void checkoutSaleSuscription(Long type, Long suscriptionvalue, Boolean isDonation, String processPaymentUri, String cancelPaymentUri, String guid, HttpServletRequest request, HttpServletResponse response) {
		try {
            checkout_base(request, response, INTENT_SALE, suscriptionvalue.floatValue(), "Payment for Suscription Services", isDonation, processPaymentUri, cancelPaymentUri, guid);
        } catch (Exception e) {
            //TODO: handle properly
            e.printStackTrace();
        }
		
	}
    
    
    
    
    // Can be converted to REST method and used for other operations while further development
    public Payment payment(HttpServletRequest request) throws Exception {
        return processPayment(request);
    }

    // Can be converted to REST method and used for other operations while further development
//    @RequestMapping("/cancel_payment")
//    public String cancelPayment(HttpServletRequest request, HttpServletResponse response) {
    public void cancelPayment(String guid) {
        // TODO: Process cancelling somehow if DB is used
        map.remove(guid);
    }

    @Autowired
    public void setQuotationService(TranslatorQuotationService quotationService) {
        this.quotationService = quotationService;
    }

    private void checkout_base(HttpServletRequest request, HttpServletResponse response, String intent, Float subtotal, String description, Boolean isDonation, String processPaymentUri, String cancelPaymentUri, String guid) throws Exception {
        Payment createdPayment = createPayment(request, intent, subtotal, description, isDonation, processPaymentUri, cancelPaymentUri, guid);
        for (Links link : createdPayment.getLinks()) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                try {
                    response.sendRedirect(link.getHref());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Should not happen
        throw new Exception("Payment is not created correctly. Wrong integration with Paypal services via In-Context popup.");
    }

    private Payment processPayment(HttpServletRequest request) throws Exception {
        Payment executedPayment = null;
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        String payerId;
        if ((payerId = request.getParameter("PayerID")) != null) {
            Payment payment = new Payment();
            String paymentId;
            String guid = request.getParameter("guid");

//            TODO: Handle in DB if DB is used
            paymentId = map.remove(guid);

            if (paymentId != null) {
                payment.setId(paymentId);
            } else {
                throw new Exception("Wrong guid =" + guid + " provided.");
            }

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);

            try {
                executedPayment = payment.execute(apiContext, paymentExecution);
                LOGGER.info("Executed the payment with id = " + executedPayment.getId() + " and status = " + executedPayment.getState());
            } catch (PayPalRESTException e) {
//                 LOGGER.error(req, resp, "Executed The Payment", Payment.getLastRequest(), null, e.getMessage());
            }
        }
        return executedPayment;
    }

    private Payment createPayment(HttpServletRequest request, String intent, Float subtotal, String description, Boolean isDonation, String processPaymentUri, String cancelPaymentUri, String guid) {

        Payment createdPayment = null;
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
      
        Float total = subtotal;
        // Items
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();
        Item item = new Item();
        item.setName(description).setQuantity("1").setCurrency("AUD").setPrice(subtotal.toString());
        items.add(item);
        if (isDonation != null && isDonation) {
            item = new Item();
            item.setName("Donation").setQuantity("1").setCurrency("AUD").setPrice(donationValue.toString());
            items.add(item);
            total+=donationValue;
        }
        itemList.setItems(items);

        // Details
        Details details = new Details();
//        details.setShipping();
//        details.setTax();
        details.setSubtotal(total.toString());

        // Amount
        Amount amount = new Amount();
        amount.setCurrency("AUD");
        // Total must be equal to sum of shipping, tax and subtotal.
        amount.setTotal(total.toString());
        amount.setDetails(details);

        // Transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setItemList(itemList);

        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        // Payer
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Payment
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // Redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + cancelPaymentUri + guid);
        redirectUrls.setReturnUrl(request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + processPaymentUri + guid);
        payment.setRedirectUrls(redirectUrls);

        try {
            createdPayment = payment.create(apiContext);
            LOGGER.info("Created payment with id = " + createdPayment.getId() + " and status = " + createdPayment.getState());

            //TODO: Save guid and paymentId in DB to track it further
            // Temporary Map is used. Better move to DB
            map.put(guid, createdPayment.getId());
        } catch (PayPalRESTException e) {
            e.printStackTrace();
//               LOGGER.error(req, resp, "Payment with PayPal", Payment.getLastRequest(), null, e.getMessage());
        }
        return createdPayment;
    }


}

