package com.chochae.afes.market.company;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chochae.afes.market.company.binance.Binance;
import com.chochae.afes.market.company.bithumb.Bithumb;
import com.chochae.afes.market.company.upbit.Upbit;

@Service
public class MarketClassFactory {
	private static final Logger logger = LoggerFactory.getLogger(MarketClassFactory.class);
	
	private static Map<String, MarketInterface>     marketMap = new HashMap<String, MarketInterface>();
	
	public static MarketInterface create(String marketId) {
		if (marketId == null) {
			throw new IllegalArgumentException("NUll is unacceptable service.");
		}
		if (marketId.equals("bithumb")){
			return new Bithumb();
		} else if (marketId.equals("upbit")){
			return new Upbit();
	    } else if (marketId.equals("binance")){
			return new Binance();
	    } else {
			return null;
		}
	}
	
//	public static MarketServiceInterface getMarketClass(String marketId) {
//		return marketMap.get(marketId);
//	}
//	
//	public static void addMarketClass(String marketId, MarketServiceInterface market) {
//		marketMap.put(marketId, market);
//	}
	
	public static void removeIdClass(String userId) {
		marketMap.remove(userId);
	}
}


