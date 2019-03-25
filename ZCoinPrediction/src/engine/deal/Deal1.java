package engine.deal;

import engine.common.CommonVariables;
import engine.dto.MarketPriceDTO;
import engine.dto.OrderDTO;
import engine.dto.OrderStatus;
import engine.market.MarketInterface;

/*
 * A deal that runs in market where price is steady.
 */
public class Deal1 extends Deal  {
	
	private String id = "Deal1" + System.currentTimeMillis(); 

	@Override
	public void runDeal(MarketInterface dealer, MarketPriceDTO dto, double amount) throws Exception {
		
		OrderDTO sellOrder = dealer.buyOrder(CommonVariables.BASIC_TRADE_UNIT, dto.getBuyPrice() * (1 - CommonVariables.BASIC_PROFIT/2), CommonVariables.BASIC_TRADE_COIN);
		OrderDTO buyOrder = dealer.sellOrder(CommonVariables.BASIC_TRADE_UNIT, dto.getPrice() * (1 + CommonVariables.BASIC_PROFIT/2), CommonVariables.BASIC_TRADE_COIN);
		System.out.println(buyOrder );
		System.out.println(sellOrder );
		
	}

	@Override
	public String getDealId() {
		return id;
	}

	@Override
	public String getDealType() {
		return "DEAL1";
	}
}
