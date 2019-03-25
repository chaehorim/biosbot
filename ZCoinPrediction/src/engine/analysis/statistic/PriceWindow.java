package engine.analysis.statistic;

import java.util.LinkedList;
import java.util.Queue;

import engine.analysis.alogorithm1.Algorithm1Cache;
import engine.common.CommonVariables;
import engine.dto.MarketPriceDTO;
import engine.market.MarketInterface;

public class PriceWindow {
	private static Integer QUEUE_SIZE = 45;
	private static Integer LATEST_QUEUE_SIZE = 30;
	private static Queue<MarketPriceDTO> priceWindow = new LinkedList<MarketPriceDTO>();
	private static Queue<MarketPriceDTO> latestWindow = new LinkedList<MarketPriceDTO>();
	
	public static void push(MarketPriceDTO price) {
		priceWindow.add(price);
		if (priceWindow.size() > QUEUE_SIZE) {
			MarketPriceDTO popPrice = priceWindow.poll();
			if (price.getTimeStamp() - popPrice.getTimeStamp() > (QUEUE_SIZE + 2) * 5000) {
				System.out.println("XXXXXX" + price);
				System.out.println("XXXXXX" +popPrice);
				cleanQueue();
			}
		}
		latestWindow.add(price);
		if (latestWindow.size() > LATEST_QUEUE_SIZE) {
			latestWindow.poll();
		}
	}
	
	public static Queue<MarketPriceDTO> getPriceStatistic () {
		return priceWindow;
	}
	
	public static Queue<MarketPriceDTO> getLatestStatistc() {
		return latestWindow;
	}
	
	public static void cleanQueue(){
		priceWindow = new LinkedList<MarketPriceDTO>();
		latestWindow = new LinkedList<MarketPriceDTO>();
		Algorithm1Cache.clear();
	}

	public static void setQUEUE_SIZE(Integer qUEUE_SIZE) {
		QUEUE_SIZE = qUEUE_SIZE;
	}

	public static void setLATEST_QUEUE_SIZE(Integer lATEST_QUEUE_SIZE) {
		LATEST_QUEUE_SIZE = lATEST_QUEUE_SIZE;
	}
	
	public static Integer getQueueSize() {
		return QUEUE_SIZE;
	}

	public static void main(String[] args) {
	    Queue<Integer> myQ = new LinkedList<Integer>();
	    myQ.add(1);
	    myQ.add(6);
	    myQ.add(3);
	    System.out.println(myQ);   // 1 6 3
	    int first = myQ.poll();    // retrieve and remove the first element
	    System.out.println(first); // 1
	    System.out.println(myQ);   // 6 3
	}
}
