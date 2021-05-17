package engine.market.binance;

import java.text.SimpleDateFormat;
import java.util.List;

import engine.dto.OrderDTO;

public class BinanceTest {

	public static void main(String[] args) {

		String coinType = "XRPBTC";
		Binance bin = new Binance();
		bin.validateMarket("8yhG6i7G0gkR7uJCpSJ41npWhcY9uNvl4anSQqIGwkmfSd9bcKSpLbZ2y6F6T4Gj", "TMs3ZmqzxoVa5xRJrWrR00dc25lvMUXOkU9ANg23O5RxdUqLgvZwfivQxogmIB2L");

		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
		System.out.println(bin.getServerTime() - System.currentTimeMillis());
		System.out.println(time_formatter.format(bin.getServerTime()));
		try {
			System.out.println(bin.getMarketPrice(coinType, 0.1));
//			System.out.println(bin.marketBuy(0.4, 88.0, coinType));
//			System.out.println(bin.marketSell(0.4, 88.0, coinType));
//			OrderDTO order = bin.buyOrder(0.5, 0.030060, coinType);
//			OrderDTO order = bin.sellOrder(9, 92.9, coinType);
//			OrderDTO order = new OrderDTO();
//			order.setOrderId("149870529");
//			order.setCoinType(coinType);
//			System.out.println(order);
			Thread.sleep(2000);
//			bin.orderResult(order);
//			System.out.println(order);
			List<OrderDTO> orderList = bin.getOpenOrders();
			double totalAmount = 0;
			double totalCnt = 0;
			for (OrderDTO orderStr : orderList) {
				System.out.println(orderStr);
				if ("ETHBTC".equals(orderStr.getCoinType())) {
					if ("SELL".equals(orderStr.getOrderType())) {
						totalAmount += orderStr.getAmount() * orderStr.getPrice();
					} else 				 
						totalCnt += orderStr.getAmount();
				}
			}
			System.out.println("ETHUSDT : totalAmount: [" + totalAmount + "], totalCount :["  + totalCnt + "]");
			totalAmount = 0;
			totalCnt = 0;
			for (OrderDTO orderStr : orderList) {
				if ("XRPBTC".equals(orderStr.getCoinType())) {
					if ("SELL".equals(orderStr.getOrderType())) {
						totalAmount += orderStr.getAmount() * orderStr.getPrice();
					} else 				 
						totalCnt += orderStr.getAmount();
				}
			}
			System.out.println("XRPBTC : totalAmount: [" + totalAmount + "], totalCount :["  + totalCnt + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
