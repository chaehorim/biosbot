package engine.deal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import engine.common.CommonDataCache;
import engine.dto.DealDTO;
import engine.dto.DealType;
import engine.dto.MarketPriceDTO;
import engine.dto.OrderDTO;
import engine.dto.OrderStatus;
import engine.market.MarketInterface;

public class DealManager {
	private static List<DealInterface> dealList = new ArrayList<DealInterface>();
	
	private static List<OrderDTO> waitingOrderList = new ArrayList<OrderDTO>();
	
	private static double maxOpenorderBuyPrice = Double.MAX_VALUE;
	private static double minOpenorderSellPrice = 0;
	
	public static void init() {
	}
	
	public static void runDeal(double amount, String coinType, DealDTO dealdto, MarketInterface market, MarketPriceDTO priceDTO) throws Exception {
		 for(Iterator<DealInterface> i = dealList.iterator(); i.hasNext();) {
			 DealInterface deal = i.next();
			 deal.increaseIdx();
			 if (!deal.isDealRunning()) {
				 System.out.println("Success!");
				 i.remove();
				 saveTofile(deal);
			 }
		 }

		 // new deal Yn
		 if (newDealYn(priceDTO, dealdto)) {
			DealInterface deal = DealFactory.create(dealdto.getType());
			if (deal != null) {
				System.out.println("Starting new Deal."  + deal.getDealId() + " dealdto: " + dealdto);
				dealList.add(deal);
				deal.runDeal(market, priceDTO,amount);
			}
		 }
	}
	
	private static boolean newDealYn(MarketPriceDTO priceDTO, DealDTO dealdto) {
		if (dealList.size() >= CommonDataCache.DEAL_MAX_SLOT_CNT) {
			System.out.println("[NEWDEALYN] : Too many Slots");
			return false;
		}
		
//		for (DealInterface deal : dealList) {
//			if (deal.getIndex() < CommonDataCache.DEAL_MAX_WAIT_CNT) {
//				System.out.println("[NEWDEALYN] : The previous one runned a bit earlier" + deal.getIndex());
//				return false;
//			}
//		}
		
		if (dealdto.getType() == DealType.DOWN && priceDTO.getBuyPrice() < maxOpenorderBuyPrice * 1.02) {
			System.out.println("Current Price is " + priceDTO.getBuyPrice() + ", maxOpenorderBuyPrice " + maxOpenorderBuyPrice );
			return false;
		}
		if (dealdto.getType() == DealType.RISE && priceDTO.getBuyPrice() > minOpenorderSellPrice * 0.98) {
			System.out.println("Current Price is " + priceDTO.getBuyPrice() + ", minOpenorderSellPrice " + minOpenorderSellPrice );
			return false;
		}
		return true;
	}
	
	public static void updateOpenOrders(List<OrderDTO> openOrderList) {
		maxOpenorderBuyPrice = 0;
		minOpenorderSellPrice = Double.MAX_VALUE;
		for(Iterator<OrderDTO> i = waitingOrderList.iterator(); i.hasNext();) {
			OrderDTO order = i.next();
			boolean isOrderOpen = false;
			for (OrderDTO orderdto : openOrderList) {
				if (orderdto.getOrderId().equals(order.getOrderId())) {
					isOrderOpen = true;
				}
				
			}
			if (!isOrderOpen) {
				order.setStatus(OrderStatus.DONE);
				i.remove();
			} 
			
		}
		for (OrderDTO orderdto : openOrderList) {
			// check min max openorder price 
			if ("BUY".equals(orderdto.getOrderType()) && maxOpenorderBuyPrice < orderdto.getPrice()) {
				maxOpenorderBuyPrice  = orderdto.getPrice();
			} else if ("SELL".equals(orderdto.getOrderType())&& minOpenorderSellPrice > orderdto.getPrice()) {
				minOpenorderSellPrice = orderdto.getPrice();
			}
		}
//		System.out.println("maxOpenorderBuyPrice :" + maxOpenorderBuyPrice + ", minOpenorderSellPrice :" + minOpenorderSellPrice);
	}
	
	public static void addWaitingOrder(OrderDTO order) {
		waitingOrderList.add(order);
	}
	
	public static void saveTofile(DealInterface deal) {
		
	}
	
	public static void main(String[] args) {
		List<String> aa = new ArrayList<String>();
		aa.add("a");
		aa.add("b");
		aa.add("c");
		aa.add("d");
		
		for(Iterator<String> i = aa.iterator(); i.hasNext();) {
			String x = i.next();
			if ("a".equals(x) || "c".equals(x)) {
				i.remove();
			}
		}
		for (String x : aa) {
			System.out.println(x);
		}
		
	}
}
