package com.chochae.afes.market.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chochae.afes.market.company.MarketClassFactory;
import com.chochae.afes.market.company.MarketInterface;
import com.chochae.afes.market.dao.MarketDAO;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.service.DbLoader;

public class MarketManager {
    private static Map<String, MarketInfo>   		markets 				= new HashMap<String, MarketInfo>();            // DB ��ϵ� ������
    
	public static void initMarkets() {
		MarketDAO marketDAO = DbLoader.getMarketDBConnection();
		List<MarketInfo> marketList = marketDAO.selectMaketList();
		for (MarketInfo market : marketList) {
			if ("Y".equals(market.getActiveYn())) {
				String marketName = market.getMarketUserName();
				markets.put(marketName, market);
				MarketInterface marketConn = MarketClassFactory.create(market.getMarketId());
				market.setMarketCon(marketConn);
				System.out.println(market);
			}
		}
	}
	
	public static MarketInfo getMarketByName(String marketUserName) {
		return markets.get(marketUserName);
	}
	
	public static MarketInfo getFromMarket() {
		for (Map.Entry<String, MarketInfo> entry : markets.entrySet()) {
			if (entry.getValue().isFromYn()) {
				return entry.getValue();
			}
		}
		
		return null;
	}
	
	public static MarketInfo getToMarket() {
		for (Map.Entry<String, MarketInfo> entry : markets.entrySet()) {
			if (entry.getValue().isToYn()) {
				return entry.getValue();
			}
		}
		
		return null;
	}
	
	public static List<MarketInfo> getMarketList() {
		List<MarketInfo> marketList = new ArrayList<MarketInfo>();
		for (Map.Entry<String, MarketInfo> entry : markets.entrySet()) {
			marketList.add(entry.getValue());
	    }
		return marketList;
	}
	
	public static List<MarketInfo> getValidatedMarketList() {
		List<MarketInfo> marketList = new ArrayList<MarketInfo>();
		for (Map.Entry<String, MarketInfo> entry : markets.entrySet()) {
			MarketInfo market = entry.getValue();
			if (market.isValidateYn())
				marketList.add(entry.getValue());
	    }
		return marketList;
	}
	
	public static synchronized void switchToMarket(String altMarketName) {
		MarketInfo toMarket = getToMarket();
		MarketInfo altMarket = getMarketByName(altMarketName);
		
		if (toMarket == null || altMarket == null || toMarket == altMarket) 
			return;
		
		toMarket.setToYn(false);
		altMarket.setToYn(true);
	}
}
