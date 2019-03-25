package com.afes.engine.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.afes.common.dto.MarketCoin;
import com.afes.common.dto.OfferInfo;
import com.afes.common.dto.OrderInfo;

public class DataManager {
	private static List<String> marketList;
	private static Map<String, MarketCoin> coinMap;
	private static List<OfferInfo> offerList;
	private static List<OrderInfo> orderList;
	
	public static void initData() {
		marketList = new ArrayList<String>();
		coinMap = new HashMap<String, MarketCoin>();
		offerList = new ArrayList<OfferInfo>();
		orderList = new ArrayList<OrderInfo>();
	}
}
