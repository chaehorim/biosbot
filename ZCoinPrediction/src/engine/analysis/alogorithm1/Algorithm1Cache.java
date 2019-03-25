package engine.analysis.alogorithm1;

import java.util.LinkedList;
import java.util.Queue;

import engine.dto.MarketPriceDTO;


public class Algorithm1Cache {
	private static Queue<Double> aQueue = new LinkedList<Double>();
	private static Integer QUEUE_SIZE = 5;
	
	public static void push(double a) {
		aQueue.add(a);
		if (aQueue.size() > QUEUE_SIZE) {
			aQueue.poll();
		}
	}
	
	public static void setQueueSize(int size) {
		QUEUE_SIZE = size;
	}
	
	public static void clear() {
		aQueue = new LinkedList<Double>();
	}
	public static Queue<Double> getPriceStatistic () {
		if (aQueue.size() < QUEUE_SIZE) {
			return null;
		}
		return aQueue;
	}
}
