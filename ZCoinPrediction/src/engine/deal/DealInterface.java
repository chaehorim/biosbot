package engine.deal;

import engine.dto.MarketPriceDTO;
import engine.market.MarketInterface;

public interface DealInterface {

	public boolean isDealRunning();
	
	public void runDeal(MarketInterface dealer, MarketPriceDTO dto, double amount) throws Exception;
	
	public String getDealType();
	
	public String getDealId();
	
	public void increaseIdx();
	
	public int getIndex();
	
	public String saveToString();
}
