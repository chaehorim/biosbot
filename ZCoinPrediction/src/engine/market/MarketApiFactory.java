package engine.market;

import engine.market.binance.Binance;

public class MarketApiFactory {
	public static MarketInterface create(String marketName) {
		if (marketName == null) {
			throw new IllegalArgumentException("NUll is unacceptable service.");
		}
		if (marketName.equals("binance")) {
			return new Binance();
		}
		
		return null;
	}
}
