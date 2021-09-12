package com.chochae.afes.properties;

import java.io.IOException;
import java.util.Locale;

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

import com.chochae.afes.properties.dao.PropertiesDAO;
import com.chochae.afes.properties.modal.PropertyInfo;
import com.chochae.afes.service.DbLoader;


@Controller
public class PropertiesController {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesController.class);
	
	/* Properties */
	@RequestMapping(value = "/properties", method = RequestMethod.GET)
	public String marketsJsp(Locale locale, Model model) {
		PropertiesDAO propDAO = DbLoader.getPropertyDBConnection();
		PropertyInfo prop = new PropertyInfo();
        try {
        	prop = propDAO.selectPrperties();
        	ObjectMapper mapper = new ObjectMapper();
			String str = mapper.writeValueAsString(prop);
			model.addAttribute("properties", str);
    	
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/properties";
	}
	
	@RequestMapping(value = "/saveProperties", method = RequestMethod.POST)
	public @ResponseBody String registerProcess(HttpServletRequest request, ModelMap model) {
		int period = Integer.parseInt(request.getParameter("period"));
		float bidBitcoin = Float.parseFloat(request.getParameter("bidBitcoin"));
		float minMargin = Float.parseFloat(request.getParameter("minMargin"));
		float minSellMargin = Float.parseFloat(request.getParameter("minSellMargin"));
		float minBuyMargin = Float.parseFloat(request.getParameter("minBuyMargin"));
		int orderPeriod = Integer.parseInt(request.getParameter("orderPeriod"));
		int orderChangeCnt = Integer.parseInt(request.getParameter("orderChangeCnt"));
		int orderWaitCnt = Integer.parseInt(request.getParameter("orderWaitCnt"));
		PropertyInfo prop = new PropertyInfo();
		prop.setPeriod(period);
		prop.setBidBitcoin(bidBitcoin);
		prop.setMinMargin(minMargin);
		prop.setMinSellMargin(minSellMargin);
		prop.setMinBuyMargin(minBuyMargin);
		prop.setOrderConfirmPeriod(orderPeriod);
		prop.setOrderChangeWaitCnt(orderChangeCnt);
		prop.setOrderChangeWaitCnt(orderWaitCnt);
		
		PropertiesDAO propDAO = DbLoader.getPropertyDBConnection();
        try {
			propDAO.insertProperties(prop);
			PropertiesManager.initProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping(value = "/selectProperties", method = RequestMethod.GET)
	public @ResponseBody PropertyInfo selectPropertyData(HttpServletRequest request, ModelMap model) {
		
		PropertiesDAO propDAO = DbLoader.getPropertyDBConnection();
		PropertyInfo prop = new PropertyInfo();
		try {
			prop = propDAO.selectPrperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prop;
	}
}
