package au.com.translatorss.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import au.com.translatorss.bean.Quotation;
import au.com.translatorss.bean.ServiceRequest;
import au.com.translatorss.bean.Translator;
import au.com.translatorss.bean.dto.QuoteSettingDTO;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import au.com.translatorss.bean.dto.TranslatorQuotationDTO;

public interface TranslatorQuotationService {

    void saveOrUpdate(Quotation entity);

    void add(Quotation entity);

    void update(Quotation entity);

    void remove(Quotation entity) throws Exception;

    Quotation find(long key);

    List<Quotation> getQuotationListFromTranslator(long translatorid, boolean status);

    void updateQuotations(Translator translatorloged, QuoteSettingDTO quoteSetting, String timeFrame);

    List<Quotation> getQuotationList(ServiceRequest serviceRequest);

    Quotation getQuoteFromServiceRequestAndTranslator(Long servicerequestid, Long translatorid);

    List<TranslatorQuotationDTO> getQuotationArrayDTO(ServiceRequestDTO serviceRequestDTO) throws IllegalStateException, IOException;

    void disableStandarQuotations(Long translatorid, String timeFrame);

    void enableStandarQuotations(Long translatorid, String timeFrame);

    void populateMediaRating(TranslatorQuotationDTO dto, Translator translator);

    List<Quotation> getQuoteListByState(String state, Long id);

    List<Quotation> getQuotesFromServiceRequestQuotedAndUnquoted();

    Date getFinishDateSelectionQuote(String timeFrame);
}