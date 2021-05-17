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

import com.chochae.afes.asset.AssetManager;
import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.market.company.MarketInterface;
import com.chochae.afes.market.dao.MarketDAO;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.market.service.MarketManager;
import com.chochae.afes.service.DbLoader;

@Controller
public class MarketController {
	private static final Logger logger = LoggerFactory.getLogger(MarketController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/markets", method = RequestMethod.GET)
	public String marketsJsp(Locale locale, Model model) {
		return "/admin/markets";
	}
	
	@RequestMapping(value = "/marketLists", method = RequestMethod.GET)
	public @ResponseBody List<MarketInfo> getMarketsLists(HttpServletRequest request, ModelMap model) {
		MarketDAO marketDAO = DbLoader.getMarketDBConnection();
		
		List<MarketInfo> marketList = marketDAO.selectMaketList();
		
//		for (MarketInfo market : marketList) {
//			MarketInfo certMarket = MarketManager.isCertificatedMarket(market.getCurrencyCode(), market.getMarketId());
//			if (certMarket != null) {
//				market.setActiveYn("Y");
//				market.setDefaultYn(certMarket.isDefaultYn());
//			} else {
//				market.setActiveYn("N");
//			}
//		}
		return marketList;
	}
	
	@RequestMapping(value = "/marketSave", method = RequestMethod.POST)
	public @ResponseBody String saveMarket(HttpServletRequest request, ModelMap model) {
		
		String marketId = request.getParameter("marketId");
		String marketName = request.getParameter("marketName");
		int currency = Integer.parseInt(request.getParameter("currencyCode"));
		String url = request.getParameter("url");
		float amount = Float.parseFloat(request.getParameter("amount"));
		float bcCnt = Float.parseFloat(request.getParameter("bcCnt"));
		boolean fromYn = "Y".equals(request.getParameter("fromYn"))? true : false;
		boolean toYn = "Y".equals(request.getParameter("toYn"))? true : false;
		
		MarketInfo market = new MarketInfo(marketId, marketName, currency, url, amount, bcCnt);
		market.setFromYn(fromYn);
		market.setToYn(toYn);
		
		logger.info("[SAVE Market Info] starting.", market);
		
		MarketDAO marketDAO = DbLoader.getMarketDBConnection();
        marketDAO.insertMarket(market);
		return "{}";
	}

	@RequestMapping(value = "/marketUpdate", method = RequestMethod.POST)
	public @ResponseBody String updateMarket(HttpServletRequest request, ModelMap model) {
		
		String marketId = request.getParameter("marketId");
		String marketName = request.getParameter("marketName");
		int currencyCode = Integer.parseInt(request.getParameter("currencyCode"));
//		int currencyName = Integer.parseInt(request.getParameter("currencyName"));
		String url = request.getParameter("url");
		float amount = Float.parseFloat(request.getParameter("amount"));
		float bcCnt = Float.parseFloat(request.getParameter("bcCnt"));
		boolean fromYn = "Y".equals(request.getParameter("fromYn"))? true : false;
		boolean toYn = "Y".equals(request.getParameter("toYn"))? true : false;
		String activeYn = request.getParameter("activeYn");
		MarketInfo market = new MarketInfo(marketId, marketName, currencyCode, url, amount, bcCnt);
		market.setFromYn(fromYn);
		market.setToYn(toYn);
		market.setActiveYn(activeYn);
		
		logger.info("[UPDATE Market Info] starting." + market);

		MarketDAO marketDAO = DbLoader.getMarketDBConnection();
        marketDAO.updateMarket(market);
		return "{}";
	}
	
	@RequestMapping(value = "/keyUpdate", method = RequestMethod.POST)
	public @ResponseBody String updateMarketKeys(HttpServletRequest request, ModelMap model) {
		
		String marketUserName = request.getParameter("marketName");
		String publicKey = request.getParameter("publicKey");
		String privateKey = request.getParameter("privateKey");
		
		// ADD MarketValidate
		MarketInfo market = MarketManager.getMarketByName(marketUserName);
		if (market == null) {
			logger.error("market is exist.");
			return "false";
		}
		MarketInterface marketConn = market.getMarketCon();
		marketConn.validateMarket(publicKey, privateKey);
		List<AssetDto> assetList = marketConn.getAsset();
		if (assetList == null || assetList.size() == 0) {
			logger.error("Failed to validate.");
			return "false"; 
		}
		market.setValidateYn(true);
		AssetManager.updateAsset(marketUserName, assetList);

		logger.info("[keyUpdate Market Info] starting.");
		
		return "true";
	}
	
	@RequestMapping(value = "/marketDelete", method = RequestMethod.POST)
	public @ResponseBody String deleteMarket(HttpServletRequest request, ModelMap model) {
		
		String marketId = request.getParameter("marketId");
		
		logger.info("[DELETE Market Info] starting." + marketId, marketId);
		
		MarketDAO marketDAO = DbLoader.getMarketDBConnection();
        marketDAO.deleteMarket(marketId);
		return "{}";
	}
	
}
