package engine.analysis;

import engine.common.CommonVariables;
import engine.dto.MarketPriceDTO;
import engine.market.MarketInterface;

public class PriceCollector {

	public static MarketPriceDTO getMarketData(MarketInterface market) throws Exception {
		MarketPriceDTO priceDTO = market.getMarketPrice(CommonVariables.BASIC_TRADE_COIN, CommonVariables.BASIC_TRADE_UNIT);
		priceDTO.setTimeStamp(System.currentTimeMillis());
		return priceDTO;
	}
}
