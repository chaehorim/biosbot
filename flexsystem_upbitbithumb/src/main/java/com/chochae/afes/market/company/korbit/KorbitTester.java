package com.chochae.afes.market.company.korbit;

import com.chochae.afes.market.company.dto.OrderBook;

public class KorbitTester {
	public static void main(String[] args) {
		Korbit ko = new Korbit();
		OrderBook ob = ko.getOrderBook("ETH", "KRW");
		try { 
			for (int i = 0; i < 1000; i ++) {
				System.out.println(ob.asks[0].price + "       <--- >    " + ob.bids[0].price);
					Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
