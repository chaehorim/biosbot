package com.chochae.afes.market.company.binance;

import java.util.List;

import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.market.company.dto.OrderBook;

public class BinanceTest {

	public static void main(String[] args) {
		Binance bi = new Binance();
		OrderBook order = bi.getOrderBook("BCH", "BTC");
		
		
		bi.validateMarket("puRzXwcYBfGifvHVgkkfv7RskSqTHPJfZdwb5rIGg2QjuOpeRnPK2L1oBaVh3Bvo", "0qefA3lwjn7sIZZMPJZoPVcyKALIFUJd1D35Vwk5hljPopD87zw6vCsh9zVS85TQ");
		System.out.println(order.asks[1].price + "  <==>  " + order.bids[1].price);
		
		List<AssetDto> assetList = bi.getAsset();
		for (AssetDto asset : assetList) {
			if (asset.getFreeAmount() > 0) {
				System.out.println(asset);
			}
		}
		
	}
}
