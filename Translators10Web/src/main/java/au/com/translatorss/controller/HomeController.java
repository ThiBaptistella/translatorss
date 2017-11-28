package au.com.translatorss.controller;

import au.com.translatorss.bean.Language;
import au.com.translatorss.bean.Loggin;
import au.com.translatorss.bean.dto.ServiceRequestDTO;
import au.com.translatorss.service.LanguageService;
import au.com.translatorss.service.PDFService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.SdkClientException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Locale;
















/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
    private LanguageService languageService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Loggin customerloginForm = new Loggin();
		model.addAttribute("customerloginForm", customerloginForm );

		Loggin translatorloginForm = new Loggin();
		model.addAttribute("translatorloginForm", translatorloginForm);
		
		ServiceRequestDTO ServiceRequestDTO = new ServiceRequestDTO();
        model.addAttribute("serviceRequestDTO", ServiceRequestDTO);
        model.addAttribute("languageList", initializeProfiles());

		HttpSession session = request.getSession();
		session.setAttribute("servcieRequestLead",null);

		return "home";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Loggin customerloginForm = new Loggin();
		model.addAttribute("customerloginForm", customerloginForm );

		Loggin translatorloginForm = new Loggin();
		model.addAttribute("translatorloginForm", translatorloginForm);
		HttpSession session = request.getSession();
		session.setAttribute("servcieRequestLead",null);

		return "Login";
	}

	public List<Language> initializeProfiles() {
        List<Language> languageList = languageService.getAvailableLanguages();
        return languageList;
    }

	
//private void loadLanguages(){
//	String csvFile2 = "/Users/mkyong/csv/country.csv";
//	String csvFile = "C:\\Users\\Javier\\Desktop\\NAATILanguague.csv";
//	BufferedReader br = null;
//	String line = "";
//	String cvsSplitBy = ",";
//
//	try {
//		br = new BufferedReader(new FileReader(csvFile));
//		while ((line = br.readLine()) != null) {
//			Language language = new Language();
//			language.setDescription(line);
//			languageService.save(language);
//			System.out.println(line);
//		}
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	} finally {
//		if (br != null) {
//			try {
//				br.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//
//}



}
