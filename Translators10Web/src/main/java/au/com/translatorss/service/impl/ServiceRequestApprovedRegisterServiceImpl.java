package au.com.translatorss.service.impl;

import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.translatorss.bean.BusinessUser;
import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.ServiceRequestApprovedRegister;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.ServiceRequestApprovedDTO;
import au.com.translatorss.service.ServiceRequestApprovedRegisterService;
import au.com.translatorss.service.TranslatorQuotationService;
import au.com.translatorss.service.TranslatorSettingsService;
import au.com.translatorss.dao.ServiceRequestApprovedRegisterDao;

@Service
@Transactional
public class ServiceRequestApprovedRegisterServiceImpl extends GenericServiceImplementation<ServiceRequestApprovedRegister, Long> implements ServiceRequestApprovedRegisterService{

	@Autowired
	private ServiceRequestApprovedRegisterDao ServiceRequestApprovedRegisterDao;
	
	@Autowired
	private TranslatorSettingsService translatorService;
	
	@Autowired
	private TranslatorQuotationService quotationService;
	
	@Override
	public void registerApprovedSR(ServiceRequest serviceRequest) {
		ServiceRequestApprovedRegister register = new ServiceRequestApprovedRegister();
		register.setApprovedDate(new Date());
		register.setServiceRequest(serviceRequest);
		ServiceRequestApprovedRegisterDao.saveOrUpdate(register);
	}

	@Override
	public List<ServiceRequestApprovedDTO> getApprovedSRByCustomer(BusinessUser businessUser) {
		 List<ServiceRequestApprovedDTO> dtoList = new ArrayList<ServiceRequestApprovedDTO>();
		 List<ServiceRequestApprovedRegister> list=ServiceRequestApprovedRegisterDao.getApprovedSRByCustomer(businessUser);

		 for(ServiceRequestApprovedRegister register: list){
			 if(!containsMonthValue(dtoList, register)){
				 ServiceRequestApprovedDTO dto= new ServiceRequestApprovedDTO();
				 Calendar calendar = Calendar.getInstance();
				 calendar.setTime(register.getApprovedDate());

				 int monthNumber = calendar.get(Calendar.MONTH);
				 String month = getMonthForInt(monthNumber);

				 dto.setMonth(month);
				 dto.setAmount(dto.getAmount()+1);
				 dtoList.add(dto);
			 }
		 }
		 return dtoList;
	}

	@Override
	public List<ServiceRequestApprovedDTO> getApprovedSRByTranslator(Translator translator) {
		 ServiceRequestApprovedRegisterDao.getApprovedSRByTranslator(translator);
		 return null;
	}
	
	private boolean containsMonthValue(List<ServiceRequestApprovedDTO> dtoList, ServiceRequestApprovedRegister register) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(register.getApprovedDate());
		int monthNumber = calendar.get(Calendar.MONTH);

		String month = getMonthForInt(monthNumber);
		
		for(ServiceRequestApprovedDTO dto: dtoList){
			 if(dto.getMonth().equals(month)){
				 dto.setAmount(dto.getAmount()+1);
				 return true;
			 }
		 }

		return false;
	}

	private String getMonthForInt(int num) {
        String month = "";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

	@Override
	public int getTotalRevenue(Long translatorid) {
		Translator translator = translatorService.getTranslatorById(translatorid);	
		List<ServiceRequestApprovedRegister> list = ServiceRequestApprovedRegisterDao.getApprovedSRByTranslator(translator);
		Integer revenue = 0;
		if(list!=null) {
			for(ServiceRequestApprovedRegister sra: list) {
				ServiceRequest sr = sra.getServiceRequest();
				Quotation quote = quotationService.getQuoteFromServiceRequestAndTranslator(sr.getId(), translatorid);
				revenue += quote.getValue().intValue();
			}
		}
		return revenue;
	}
	
}
