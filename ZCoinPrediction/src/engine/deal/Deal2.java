package engine.deal;

import engine.common.CommonVariables;
import engine.dto.MarketPriceDTO;
import engine.dto.OrderDTO;
import engine.dto.OrderStatus;
import engine.market.MarketInterface;

/*
 * A deal that runs in market where price is going down
 */
public class Deal2 extends Deal  {
	
	private String id = "Deal2" + System.currentTimeMillis(); 

	@Override
	public void runDeal(MarketInterface dealer, MarketPriceDTO dto, double amount) throws Exception {
		sellOrder = dealer.marketSell(amount, dto.getSellPrice(), CommonVariables.BASIC_TRADE_COIN);
		Thread.sleep(CommonVariables.DEAL_RUNNING_TIME);
		
		buyOrder = dealer.buyOrder(amount, Math.round(sellOrder.getPrice() * (1 - CommonVariables.BASIC_PROFIT) * 100) /100.0, CommonVariables.BASIC_TRADE_COIN);
		System.out.println("BUYOrder" + buyOrder );
		System.out.println("SELLOrder" + sellOrder );
		DealManager.addWaitingOrder(buyOrder);
		DealManager.addWaitingOrder(sellOrder);
	}

	@Override
	public String getDealId() {
		return id;
	}
	
	@Override
	public String getDealType() {
		return "DEAL2";
	}

}
