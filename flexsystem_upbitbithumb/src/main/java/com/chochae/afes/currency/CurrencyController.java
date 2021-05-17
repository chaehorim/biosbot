package com.chochae.afes.currency;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chochae.afes.currency.dao.CurrencyDAO;
import com.chochae.afes.currency.model.CurrencyInfo;
import com.chochae.afes.service.DbLoader;

@Controller
public class CurrencyController {
	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
	
	/* Currency */
	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public String currencyJsp(Locale locale, Model model) {
		
		CurrencyDAO dao = DbLoader.getCurrencyDBConnection();
		List<CurrencyInfo> currencyList = dao.getCurrencyList();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String str = mapper.writeValueAsString(currencyList);
			model.addAttribute("currencyList", str);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/manager/currency";
	}
	
	@RequestMapping(value = "/selectCurrency", method = RequestMethod.GET)
	public @ResponseBody List<CurrencyInfo> selectCurrency(HttpServletRequest request, ModelMap model) {
		
		logger.info("[Currency Add] starting.");

        CurrencyDAO currencyDAO = (CurrencyDAO) DbLoader.getCurrencyDBConnection();
        List<CurrencyInfo> currecyList = currencyDAO.getCurrencyList();
		return currecyList;
	}
	
	@RequestMapping(value = "/addCurrency", method = RequestMethod.POST)
	public @ResponseBody List<CurrencyInfo> addCurrency(HttpServletRequest request, ModelMap model) {
		int fromCode  = Integer.parseInt(request.getParameter("fromCode"));
		int toCode = Integer.parseInt(request.getParameter("toCode"));
		float value = Float.parseFloat(request.getParameter("value"));
		
		logger.info("[Currency Add] starting.");

		CurrencyInfo currency = new CurrencyInfo(fromCode, toCode, value, System.currentTimeMillis());
        CurrencyDAO currencyDAO = (CurrencyDAO) DbLoader.getCurrencyDBConnection();
        currencyDAO.insertCurrency(currency);
        List<CurrencyInfo>  curList = currencyDAO.getCurrencyList();
		return curList;
	}
	
	@RequestMapping(value = "/updateCurrency", method = RequestMethod.POST)
	public @ResponseBody String updateCurrency(HttpServletRequest request, ModelMap model) {
		int fromCode  = Integer.parseInt(request.getParameter("fromCode"));
		int toCode = Integer.parseInt(request.getParameter("toCode"));
		float value = Float.parseFloat(request.getParameter("value"));
		
		logger.info("[Currency Add] starting.");

		CurrencyInfo currency = new CurrencyInfo(fromCode, toCode, value, System.currentTimeMillis());
        CurrencyDAO currencyDAO = (CurrencyDAO) DbLoader.getCurrencyDBConnection();
        currencyDAO.updateCurrency(currency);
        CurrencyManager.setCurrency(currency);
		return "";
	}
}
