package com.chochae.afes.market.company.bithumb;

import com.chochae.afes.market.company.dto.OrderBook;

public class BithumbTest {

	public static void main(String[] args) {
		Bithumb bi = new Bithumb();
		long time = System.currentTimeMillis();
		OrderBook order = bi.getOrderBook("QTUM", "KRW");
//		bi.getPrice("BTC", "KRW");
//		bi.validateMarket("a988854959d591b42593b7f1ac62d6dc", "29bf49afbf888e619a7f7cc73bea8010");
		
//		TradeResultInfo msg = bi.marketBuy("XRP", 10.1, 0);
//		TradeResultInfo msg = bi.marketSell("XRP", 1.2, 0);
//		System.out.println(msg);
//		double price = bi.getOrderPrice("C0106000000191579284", "XRP");
//		System.out.println(price);
//		List<AssetDto> assetList = bi.getAsset();
//		for (AssetDto asset : assetList) {
//			if (asset.getFreeAmount() > 0) {
//				System.out.println(asset);
//			}
//		}
//		System.out.println(System.currentTimeMillis() - time); //C0106000000191567214
		                                                        //C0106000000191569414
	}
}
