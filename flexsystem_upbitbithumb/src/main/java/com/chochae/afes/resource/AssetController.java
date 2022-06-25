package com.chochae.afes.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chochae.afes.asset.AssetManager;
import com.chochae.afes.asset.dao.AssetDAO;
import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.common.dto.ReturnMsg;
import com.chochae.afes.resource.dto.AssetInfo;
import com.chochae.afes.service.DbLoader;

@Controller
public class AssetController {
	private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
	
	@RequestMapping(value = "/assetSnapshot", method = RequestMethod.GET)
	public String marketsJsp(Locale locale, Model model) {
//		Map<String, AssetInfo> defaultAsset = ResourceManager.getDefaultAsset();
//		Map<String, AssetInfo> currentAsset = ResourceManager.getCurrentAsset();
//		
//		
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			String str = mapper.writeValueAsString(defaultAsset);
//			model.addAttribute("defaultAsset", str);
//			str = mapper.writeValueAsString(currentAsset);
//			model.addAttribute("currentAsset", str);
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "/operator/assetSnapshot";
	}
	
	@RequestMapping(value = "/getAssetDate", method = RequestMethod.GET)
	public @ResponseBody List<AssetDto> getAssetDate(HttpServletRequest request, ModelMap model) {
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		fromDate.replace("-", "");
		toDate.replace("-", "");
		AssetDAO assetDao = DbLoader.getAssetDBConnection();
		List<AssetDto> fromAsset = assetDao.selectAssetList(fromDate);
		model.addAttribute("fromAsset", fromAsset);
		List<AssetDto> toAsset = null;
		if (toDate == null || toDate.length() < 8) {
			Map<String, List<AssetDto>> assetMap = AssetManager.getAssetMap();
			List<AssetDto> tmpList = new ArrayList<AssetDto>();
			for (Map.Entry<String, List<AssetDto>> entry : assetMap.entrySet()) {
		        tmpList.addAll(entry.getValue());
		    }
		} else {
			toAsset = assetDao.selectAssetList(toDate);
		}
		model.addAttribute("toAsset", toAsset);
		return null;
	}
	
	@RequestMapping(value = "/marketAsset", method = RequestMethod.GET)
	public @ResponseBody AssetInfo saveMarket(HttpServletRequest request, ModelMap model) {
		String marketId = request.getParameter("marketId");
		
		return ResourceManager.getMarketAsset(marketId);
	}
	
	@RequestMapping(value = "/setDefaultAsset", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, AssetInfo>> setDefault(HttpServletRequest request, ModelMap model) {
//		ResourceManager.resetAllMarketAsset();
//		Map<String, AssetInfo> defaultAsset = ResourceManager.getDefaultAsset();
//		Map<String, AssetInfo> currentAsset = ResourceManager.getCurrentAsset();
//		List<Map<String, AssetInfo>>  assetList = new ArrayList<Map<String, AssetInfo>>();
//		assetList.add(defaultAsset);
//		assetList.add(currentAsset);
//		
//		return assetList;
		return null;
	}
	
	@RequestMapping(value = "/transferBitcoin", method = RequestMethod.GET)
	public @ResponseBody ReturnMsg transferBitcoin(HttpServletRequest request, ModelMap model) {
		
		String fromMarket = request.getParameter("fromMarket");
		String toMarket = request.getParameter("toMarket");
		float bitCoinCnt  = Float.parseFloat(request.getParameter("value"));
		
		boolean successYn = ResourceManager.transferBitCoin(fromMarket, toMarket, bitCoinCnt);
		ReturnMsg ret = new ReturnMsg();
		ret.setSuccesYn(successYn);
		return ret;
	}
}
