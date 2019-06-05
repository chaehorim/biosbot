package engine.analysis.alogorithm1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import engine.analysis.Algorithm;
import engine.analysis.statistic.PriceWindow;
import engine.dto.DealDTO;
import engine.dto.DealType;
import engine.dto.MarketPriceDTO;

/*
 * Linear regression to check current trend and 
 * 
 * calc a,b from y = ax + b 
 * 
 * */
public class Algorithm1 implements Algorithm{
	private int QUEUE_SIZE = 45; 
	private final double MIN_A = -10;
	private final double MAX_A = 10;
	private double MIN_B = -100;
	private double MAX_B = 100;
	private final double increase = 0.1;
	private double upperRange = 0.5;
	private double downerRange = -0.5;
	private double aUpperRange =  0.5;
	private double aDownerRange =  -0.5;
	private double outMinCost = 4000;
	
	@Override
	public DealDTO run() {

		Queue<MarketPriceDTO> priceQueue = PriceWindow.getPriceStatistic();
		if (priceQueue.size() < QUEUE_SIZE) {
			return null;
		}
		double[][] values = new double[priceQueue.size()][2];
		int seq = 0;
		double standard = 0;
		
		for (MarketPriceDTO price : priceQueue) {
			double yVal = price.getPrice();
			if (seq == 0) {
				standard = yVal;
			}
			values[seq][1] = (yVal/standard - 1) * 10000;
			values[seq][0] = seq++;
		}
		FunctionResult standardAResult = calculate(values, MIN_A, MAX_A, MIN_B, MAX_B);
		
		Algorithm1Cache.push(standardAResult.getA());
		Queue<Double> aQueue = Algorithm1Cache.getPriceStatistic();
		FunctionResult AAResult = null;
		DealDTO deal = new DealDTO();
		
		if (aQueue != null) {
			double[][] aaVals = new double[aQueue.size()][2];
			int aSeq = 0;
			for (Double price : aQueue) {
				aaVals[aSeq][1] = price * 10;
				aaVals[aSeq][0] = aSeq++;
			}
			AAResult = calculate(aaVals, -10, 10, -100, 100);
		}
		
		if (AAResult == null) {
			deal.setType(DealType.NONE);
			return deal;
		} else if (standardAResult.getA() >= upperRange && AAResult.getA() >= aUpperRange) {
			deal.setType(DealType.RISE);
		} else if (standardAResult.getA() <= downerRange && AAResult.getA() <= aDownerRange) {
			deal.setType(DealType.DOWN);
		} else {
			// set DealType.STEADY case Think when steady case is necessary
			deal.setType(DealType.NONE);
		}
		
//		if (standardAResult.getMinCost() > outMinCost) {
//			deal.setType(DealType.NONE);
//		}
		
		Algorithm1Result result = new Algorithm1Result();
		result.setStandard(standardAResult);
		result.setLatest(AAResult);
		deal.setVarA(standardAResult.getA());
		deal.setMinCost(standardAResult.getMinCost());
		deal.setResult(result);
		if (standardAResult != null && AAResult != null) {
			deal.setBetUnit(calcBettingUnit(standardAResult.getA(), AAResult.getA(), deal.getType()));
			deal.setComment(standardAResult.toString() + AAResult.toString());
		}
		return deal;
	}
	
	private FunctionResult calculate(double[][] values, double minA, double maxA, double minB, double maxB) {
		
		double b = 0;
		double minCost = calculateCost(minA, 0, values);
		double a = minA;
		
		for (double i = minA; i < maxA; i = i + increase) {
			for (double j = minB; j < maxB; j = j + (increase)) {
				double curCost = calculateCost(i, j, values);
				if (curCost < minCost) {
					minCost = curCost;
					a = i;
					b = j;
				}
			}
		}
		
//		System.out.println(" a=[" +  Math.round((a) * 1000000) / 1000000.0 + "], b=[" + Math.round((b) * 10000) / 10000.0 + "], minCost=[" + Math.round((minCost) * 100) / 100.0 + "]");
		FunctionResult res = new FunctionResult();
		res.setA(Math.round((a) * 10000) / 10000.0);
		res.setB(Math.round((b) * 10000) / 10000.0);
		res.setMinCost(minCost);
		return res;
	}

	private double calculateCost(double a, double b, double[][] values) {
		double xReal = 0, yReal = 0, yPredict = 0, yDiff = 0, cost = 0;
		for (double[] value : values) {
			xReal = value[0];
			yReal = value[1];
			
			yPredict = a * xReal + b;
			yDiff = yReal - yPredict;
			cost += yDiff * yDiff;
//			System.out.println("x:" + xReal + ", y" + yPredict + ",cost:" + cost + ",a:" + a + ",b:" + b + ",yDiff:" + yDiff);
		}
//		System.out.println("cost " + cost + ", a " + a );
		
		return cost;
	}
	
	private int calcBettingUnit(double a, double aa, DealType type) {
		if (type == DealType.RISE) {
			return 10;
		}
		return 2;
	}
	
	public void setUpperRange(double upperRange) {
		this.upperRange = upperRange;
	}

	public void setRange(double range) {
		this.downerRange = range * -1;
		this.upperRange = range ;
	}

	public void setARange (double range) {
		this.aUpperRange = range;
		this.aDownerRange = range * -1;
	}
	
	public void setQueueSize(int size){
		QUEUE_SIZE = size;
	}
	public static void main(String[] args) {
		Queue<MarketPriceDTO> priceWindow = new LinkedList<MarketPriceDTO>();
		priceWindow.add(new MarketPriceDTO(6367.88));
		priceWindow.add(new MarketPriceDTO(6367.99));
		priceWindow.add(new MarketPriceDTO(6368.36));
		priceWindow.add(new MarketPriceDTO(6369.19));
		priceWindow.add(new MarketPriceDTO(6369.21));
		priceWindow.add(new MarketPriceDTO(6369.96));
		priceWindow.add(new MarketPriceDTO(6369.99));
		priceWindow.add(new MarketPriceDTO(6370.02));
		priceWindow.add(new MarketPriceDTO(6370.02));
		priceWindow.add(new MarketPriceDTO(6370.22));
		priceWindow.add(new MarketPriceDTO(6370.55));
		
//		double[][] values = { {0, 0}, {1, 15}, {2, 24}, {3, 39}, {4,56}, {5, 68} };
////		double[][] values = { {1, 5.1}, {2, 4.1}, {3, 2.9}, {4, 2}, {5,0.8}, {6, 0.1} };
		
		double[][] values = new double[priceWindow.size()][2];
		int seq = 0;
		double standard = 0;
		for (MarketPriceDTO price : priceWindow) {
			double yVal = price.getPrice();
			double xVal = 0;
			if (seq == 0) {
				standard = yVal;
			}
			values[seq][1] = (yVal/standard - 1) * 1000;
			values[seq][0] = seq++;
		}
		Algorithm1 algo = new Algorithm1();
		algo.calculate(values, -10, 10, -10, 10);
		double[][] purevalues = { {1, -4.7}, {2, -4.8}, {3, -4.9}, {4, -4.8}, {5,-4.8} };
		Algorithm1 algo1 = new Algorithm1();
		algo1.calculate(values, -10, 10, -10, 10);
		
	}
	
}
