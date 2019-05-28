package engine.deal;

import engine.common.CommonVariables;
import engine.dto.MarketPriceDTO;
import engine.dto.OrderDTO;
import engine.dto.OrderStatus;
import engine.market.MarketInterface;

/*
 * A deal that runs in market where price is going up
 */
public class Deal3 extends Deal  {
	
	private String id = "Deal3" + System.currentTimeMillis(); 

	@Override
	public void runDeal(MarketInterface dealer, MarketPriceDTO dto, double amount) throws Exception {
		
		buyOrder = dealer.marketBuy(amount, 0d, CommonVariables.BASIC_TRADE_COIN);
		Thread.sleep(CommonVariables.DEAL_RUNNING_TIME);
		sellOrder = dealer.sellOrder(amount, Math.round(buyOrder.getPrice() * (1 + CommonVariables.BASIC_PROFIT) * 1000000) / 1000000.0, CommonVariables.BASIC_TRADE_COIN);
		System.out.println("BUYOrder" + buyOrder );
		System.out.println("SELLOrder" + sellOrder );
		DealManager.addWaitingOrder(buyOrder);
		DealManager.addWaitingOrder(sellOrder);
	}
	
	
	public static void main(String[] args) {
		System.out.println((Math.round(0.031084 * (1 + CommonVariables.BASIC_PROFIT) * 1000000) / 1000000.0));
	}
	@Override
	public String getDealId() {
		return id;
	}
	
	@Override
	public String getDealType() {
		return "DEAL3";
	}

}
