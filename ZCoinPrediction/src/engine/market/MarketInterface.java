package engine.market;

import java.util.List;

import engine.dto.MarketPriceDTO;
import engine.dto.OrderDTO;

public interface MarketInterface {

	public boolean validateMarket(String publicKey, String privateKey);
	
	public MarketPriceDTO getMarketPrice(String coinType, double quantity) throws Exception ;

	public OrderDTO marketBuy(double quantity, double price, String coinType) throws Exception ;

	public OrderDTO marketSell(double quantity, double price, String coinType) throws Exception ;
	
	public OrderDTO buyOrder(double quantity, double price, String coinType) throws Exception ;
	
	public OrderDTO sellOrder(double quantity, double price, String coinType) throws Exception ;
	
	public void orderResult(OrderDTO order) throws Exception ;
	
	public List<OrderDTO> getOpenOrders() throws Exception;
	
	public boolean cancelOrder(String orderId, String coinType, String orderType) throws Exception;
}
