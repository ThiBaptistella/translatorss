package au.com.translatorss.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.security.access.AccessDeniedException;


@ControllerAdvice
public class GlobalExceptionController {

	/*@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Exception", exception);
		mav.addObject("headMessage", "System is Unavailable.");
		mav.addObject("bodyMessage", "Sorry, at this moment we are not available, Please contact us!.");
		mav.addObject("returnButtom", false);
		mav.setViewName("errorPages/SystemDown");
		return mav;
	}*/
	
	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView handleAccessDeniedException(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@ExceptionHandler(SdkClientException.class)
	public ModelAndView handleSdkClientException(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Exception", exception);
		mav.addObject("headMessage", "System is Unavailable.");
		mav.addObject("bodyMessage", "Sorry, at this moment we are not available, Please contact us!.");
		mav.addObject("returnButtom", false);
		mav.setViewName("errorPages/SystemDown");
		return mav;
	}
	
	/*To test this change the line 134 in AmazonServiceClent */
	@ExceptionHandler(AmazonS3Exception.class)
	public ModelAndView handleAmazonS3Exception(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Exception", exception);
		mav.addObject("headMessage", "System is Unavailable.");
		mav.addObject("bodyMessage", "Sorry, an Unexpected error happend, Please contact us or try later!.");
		mav.addObject("returnButtom", true);
		mav.setViewName("errorPages/SystemDown");
		return mav;
	}
	
	@ExceptionHandler(PayPalRESTException.class)
	public ModelAndView handlePaypalException(Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Exception", exception);
		mav.setViewName("errorPages/paypalErrorMessage");
		return mav;
	}
}