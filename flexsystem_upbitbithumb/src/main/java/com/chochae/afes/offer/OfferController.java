package com.chochae.afes.offer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

import com.chochae.afes.asset.AssetManager;
import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.coins.CoinManager;
import com.chochae.afes.coins.model.CoinInfo;
import com.chochae.afes.coins.model.CoinOptionInfo;
import com.chochae.afes.market.service.MarketManager;
import com.chochae.afes.offer.dto.DealInfo;
import com.chochae.afes.offer.dto.OfferUserData;
import com.chochae.afes.record.RecordManager;
import com.chochae.afes.record.dao.RecordInfo;
import com.chochae.afes.resource.ResourceManager;
import com.chochae.afes.resource.dto.UserAssetInfo;
import com.chochae.afes.service.AfesEngineManager;
import com.chochae.afes.user.UserManager;
import com.chochae.afes.user.model.LoginForm;
import com.chochae.afes.user.model.UserSettingInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class OfferController {

	private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
	
	@RequestMapping(value = "/dealList", method = RequestMethod.GET)
	public String offerList(HttpServletRequest request, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
		UserSettingInfo setting = UserManager.getUserSetting(userData.getUsername());
		setting.setUserId(userData.getUsername());
		
		List<UserAssetInfo> assetList = ResourceManager.getUserAssets(userData.getUsername());
		List<CoinOptionInfo> coinlOptionList = CoinManager.getCoinOptionList();

		try {
			String str = mapper.writeValueAsString(setting);
			model.addAttribute("userSetting", str);
			String assetStr = mapper.writeValueAsString(assetList);
			model.addAttribute("marketAsset", assetStr);
			String coinOptions = mapper.writeValueAsString(coinlOptionList);
			model.addAttribute("coinOptions", coinOptions);
			model.addAttribute("tradeYn", AfesEngineManager.isTradeYn());
			model.addAttribute("fromMarket", MarketManager.getFromMarket().getMarketUserName());
			model.addAttribute("fromMarketId", MarketManager.getFromMarket().getMarketId());
			model.addAttribute("toMarket", MarketManager.getToMarket().getMarketUserName());
			model.addAttribute("toMarketId", MarketManager.getToMarket().getMarketId());
			model.addAttribute("altMarket", getExtraMarket(AssetManager.getAssetMap()));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/operator/dealList";
	}	
	
	@RequestMapping(value = "/offerUserData", method = RequestMethod.GET)
	public @ResponseBody OfferUserData getUserOfferList(HttpServletRequest request, ModelMap model) {
		LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
		OfferUserData offerData = new OfferUserData();

		offerData.setOfferList(reverseDeal(AfesEngineManager.getOfferList()));
		offerData.setDealList(reverseDeal(AfesEngineManager.getDealList()));
		Map<String, List<AssetDto>> assetMap = AssetManager.getAssetMap();
		offerData.setCoinAssetMap(makeCoinAssetMap(assetMap));
		
		offerData.setLogList(reverseRecords(RecordManager.getrRecordList()));
		return offerData;
	}
	
	private Map<String, Double[]> makeCoinAssetMap(Map<String, List<AssetDto>> assetMap) {
		Map<String, Double[]> coinAssetMap = new HashMap<String, Double[]>();
		List<CoinInfo> coinList = CoinManager.getCoinList();
		String fromMarket = MarketManager.getFromMarket().getMarketUserName();
		String toMarket = MarketManager.getToMarket().getMarketUserName();
		boolean hasExtraAsset = false;
		String extraMarket = "";
		if (assetMap.size() > 2) {
			hasExtraAsset = true;
			for (Map.Entry<String, List<AssetDto>> entry : assetMap.entrySet()) {
				String marketName = entry.getKey();
				if(marketName.equals(fromMarket) || marketName.equals(toMarket)) {
					continue;
				}
				extraMarket = marketName;
			}
		}
		for (CoinInfo coin : coinList) {
			String coinType = coin.getCoinType();
			Double[] freeAsset = new Double[2];
			if (hasExtraAsset) {
				freeAsset = new Double[3];
			}
			List<AssetDto> fromList = assetMap.get(fromMarket);
			if (fromList != null){
				for (AssetDto asset : fromList) {
					if(coinType.equals(asset.getCoinType())) {
						freeAsset[0] = asset.getFreeAmount();
					}
				}
			}
			
			List<AssetDto> toList = assetMap.get(toMarket);
			if (toList != null){
				for (AssetDto asset : toList) {
					if(coinType.equals(asset.getCoinType())) {
						freeAsset[1] = asset.getFreeAmount();
					}
				}
			}
			if (hasExtraAsset) {
				List<AssetDto> extraList = assetMap.get(extraMarket);
				if (toList != null){
					for (AssetDto asset : extraList) {
						if(coinType.equals(asset.getCoinType())) {
							freeAsset[2] = asset.getFreeAmount();
						}
					}
				}
			}
			coinAssetMap.put(coinType, freeAsset);
		}
		return coinAssetMap;
	}
	
	private String getExtraMarket(Map<String, List<AssetDto>> assetMap) {
		if (assetMap.size() > 2) {
			String fromMarket = MarketManager.getFromMarket().getMarketUserName();
			String toMarket = MarketManager.getToMarket().getMarketUserName();
			for (Map.Entry<String, List<AssetDto>> entry : assetMap.entrySet()) {
				String marketName = entry.getKey();
				if(marketName.equals(fromMarket) || marketName.equals(toMarket)) {
					continue;
				}
				return marketName;
			}
		}
		return null;
	}
	private static List<DealInfo> reverseDeal(List<DealInfo> inputList) {
		List<DealInfo> copy = new ArrayList<DealInfo>(inputList);
		Collections.reverse(copy);
		return copy;
	}
	
	private static List<RecordInfo> reverseRecords(List<RecordInfo> logList) {
		List<RecordInfo> copy = new ArrayList<RecordInfo>(logList);
		Collections.reverse(copy);
		return copy;
	}
	@RequestMapping(value = "/userOfferSetting", method = RequestMethod.POST)
	public @ResponseBody boolean offerSetting(HttpServletRequest request, ModelMap model) {
		String result = request.getParameter("optionList");
		JsonParser parser = new JsonParser();
		JsonArray arr = parser.parse(result).getAsJsonArray();
		
		List<CoinOptionInfo> coinOptionList =  new ArrayList<CoinOptionInfo>();
		for (int i = 0, size = arr.size(); i < size; i ++) {
			CoinOptionInfo option = new CoinOptionInfo();
			JsonObject obj = arr.get(i).getAsJsonObject();
			option.setFromMarket(obj.get("fromMarket").getAsString());
			option.setToMarket(obj.get("toMarket").getAsString());
			option.setCoinType(obj.get("coinType").getAsString());
			option.setBetAmount(obj.get("betAmount").getAsDouble());
			option.setBetProfit(obj.get("betProfit").getAsDouble());
			option.setActiveYn(obj.get("activeYn").getAsBoolean());
		
			if (!validateCoinOption(option)) {
				return false;
			}
			if (option.getBetProfit() > 10 || option.getBetProfit() < -1) {
				return false;
			}
			coinOptionList.add(option);

		}
		boolean tradeYn = "true".equals(request.getParameter("tradeYn"))? true : false;
		System.out.println(request.getParameter("tradeYn") + tradeYn);
		AfesEngineManager.setTradYn(tradeYn);
		
		CoinManager.updateCoin(coinOptionList);
		return true;
	}
	
	private boolean validateCoinOption(CoinOptionInfo option) {
		double maxVal = 2000000;
		double minVal = 50000;
		if ("BTC".equals(CoinManager.BASE_COIN)) {
			maxVal = 0.1;
			minVal = 0.0001;
		}
		if (option.getBetAmount() > maxVal || option.getBetAmount() < minVal) {
			return false;
		}
		return true;
	}
	@RequestMapping(value = "/switchToMarket", method = RequestMethod.POST)
	public @ResponseBody boolean switchToMarket(HttpServletRequest request, ModelMap model) {
		String altMarketName = request.getParameter("altMarketName");
		MarketManager.switchToMarket(altMarketName);
		return true;
	}
}
