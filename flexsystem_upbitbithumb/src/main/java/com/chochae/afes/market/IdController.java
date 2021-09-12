package com.chochae.afes.market;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chochae.afes.common.dto.ReturnMsg;
import com.chochae.afes.market.modal.IdInfo;
import com.chochae.afes.market.service.IdManager;


@Controller
public class IdController {
	private static final Logger logger = LoggerFactory.getLogger(IdController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/idmanage", method = RequestMethod.GET)
	public String marketsJsp(Locale locale, Model model) {
		return "/admin/idmanage";
	}
	
	
	@RequestMapping(value = "/idList", method = RequestMethod.GET)
	public @ResponseBody List<IdInfo> getIdInfo(HttpServletRequest request, ModelMap model) {
		List<IdInfo> idList = IdManager.getIdList();
		
		return idList;
	}
	
	
	@RequestMapping(value = "/idAdd", method = RequestMethod.GET)
	public @ResponseBody ReturnMsg addId(HttpServletRequest request, ModelMap model) {
		
		String marketId = request.getParameter("marketId");
		int currency = Integer.parseInt(request.getParameter("currencyCode"));
		String publicKey = request.getParameter("publicKey");
		String privateKey = request.getParameter("privateKey");
		String userId  = request.getParameter("userId");
		String desc  = request.getParameter("desc");
		
		//verification
		//  if fail return false;
		
		IdInfo id = new IdInfo(userId, desc, publicKey, privateKey, currency, marketId);
		
		ReturnMsg msg = IdManager.addMarketId(id);
	
		return msg;
	}	
	
	@RequestMapping(value = "/idDelete", method = RequestMethod.POST)
	public @ResponseBody String deleteId(HttpServletRequest request, ModelMap model) {
		
		String userId  = request.getParameter("userId");
		
		IdManager.deleteId(userId);
		return "{}";
	}
	
	@RequestMapping(value = "/setDefaultId", method = RequestMethod.POST)
	public @ResponseBody String idDefault(HttpServletRequest request, ModelMap model) {
		
		String userId  = request.getParameter("userId");
		Boolean defaultYn = Boolean.valueOf(request.getParameter("defaultYn"));
		IdManager.setDefault(userId, defaultYn);
		
		return "{}";
	}
}
