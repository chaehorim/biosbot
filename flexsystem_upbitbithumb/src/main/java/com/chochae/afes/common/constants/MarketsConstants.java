package com.chochae.afes.common.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketsConstants {
	private static Map<String, List<String>>  MarketCoinMap;
	static
    {
		MarketCoinMap = new HashMap<String,  List<String>>();
		MarketCoinMap.put("bithumb", Arrays.asList("btc", "eth", "etc", "ltc", "bch"));
		MarketCoinMap.put("yunbi", Arrays.asList("btc", "eth", "etc"));
		MarketCoinMap.put("huobi", Arrays.asList("btc", "eth", "etc", "ltc", "bch"));
    }
	
	public static List<String> getSameTypeList(String market1, String market2) {
		List<String> typeList = new ArrayList<String>();
		
		List<String> market1CoinList = MarketCoinMap.get(market1);
		List<String> market2CoinList = MarketCoinMap.get(market2);
		for (String coin : market1CoinList) {
			if (market2CoinList.contains(coin)) {
				typeList.add(coin);
			}
		}
		return typeList;
	}
	
	public static void main(String[] args) {
		List<String> typeList = getSameTypeList("bithumb", "yunbi");
		for (String type : typeList) {
			System.out.println(type);
		}
	}
}
