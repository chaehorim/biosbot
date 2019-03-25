package engine.analysis.alogorithm1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import engine.dto.DealType;
import engine.dto.MarketPriceDTO;
import engine.dto.Prediction;
import engine.test.Algorithm1Tester;
import engine.test.TestStatistics;

public class Algorithm1StatisticAnalyzer {
	private static Map<RangeKey, List<Prediction>> rangeMap = new HashMap<RangeKey, List<Prediction>>();
	private static Map<RangeKey, TestStatistics> statMap = new HashMap<RangeKey, TestStatistics>();
	public static void init(double standardStart, double standardEnd, double lateStart, double lateEnd, double minCost) {
		for (double i = standardStart; i < standardEnd; i = i + 0.1) {
			for (double j = lateStart; j < lateEnd;j = j + 0.1) {
				RangeKey key = new RangeKey();
				key.setStandardRange(Math.round(i * 100) / 100.0);
				key.setLateRange(Math.round(j * 100) / 100.0);
				key.setMinCost(minCost);
				rangeMap.put(key, new ArrayList<Prediction>());
				statMap.put(key, new TestStatistics());
			}
		}
	}
	
	public static void addPrediction(Prediction pred, MarketPriceDTO price, String coinType) {
		for (Map.Entry<RangeKey, List<Prediction>> entry : rangeMap.entrySet())  {
			Prediction newPred = pred.copyPrediction();
			RangeKey range = entry.getKey();
			FunctionResult standardAResult = ((Algorithm1Result)newPred.getDeal().getResult()).getStandard();
			FunctionResult AAResult = ((Algorithm1Result)newPred.getDeal().getResult()).getLatest();
			if (AAResult == null) {
				continue;
			}
			
			if (newPred.getDeal().getMinCost() > range.getMinCost()) {
				continue;
			}
			if (standardAResult.getA() >= range.getStandardRange() && AAResult.getA() >= range.getLateRange()) {
				newPred.getDeal().setType(DealType.RISE);
				entry.getValue().add(newPred);
				statMap.get(range).addDeal(newPred.getDeal(), coinType);
			} else if (standardAResult.getA() <= (range.getStandardRange() * -1) && AAResult.getA() <= (range.getLateRange() * -1)) {
					
				newPred.getDeal().setType(DealType.DOWN);
				entry.getValue().add(newPred);
				statMap.get(range).addDeal(newPred.getDeal(), coinType);
			} 
			
			switch(newPred.getDeal().getType()) {
			case STEADY:
				newPred.setBuyPrice(price.getBuyPrice() * (1 - Algorithm1Tester.PROFIT_RANGE));
				newPred.setSellPrice(price.getSellPrice() * (1 + Algorithm1Tester.PROFIT_RANGE));
				break;
			case DOWN:
				newPred.setSellPrice(price.getSellPrice());
				newPred.setSellYn(true);
				newPred.setBuyPrice(price.getBuyPrice() * (1 - Algorithm1Tester.PROFIT_RANGE * 2));
				break;
			case RISE:
				newPred.setBuyPrice(price.getBuyPrice());
				newPred.setBuyYn(true);
				newPred.setSellPrice(price.getSellPrice() * (1 + Algorithm1Tester.PROFIT_RANGE * 2));
				break;
			default:
				break;
			}
			
		}
	}

	public static void checkStatistics(MarketPriceDTO dto, long count, String coinType) {
		for (Map.Entry<RangeKey, List<Prediction>> entry : rangeMap.entrySet())  {
			List<Prediction> predList = entry.getValue();
			RangeKey range = entry.getKey();
			TestStatistics stater = statMap.get(range);
			
			for (Iterator<Prediction> it = predList.iterator(); it.hasNext();) {
				Prediction pred = it.next();
				if (count - pred.getIndex() > Algorithm1Tester.VALIDATE_PERIOD) {
					it.remove();
					if (pred.getDeal().getType() != DealType.STEADY) {
//						System.out.println("failed" +pred);
						stater.addFailDeal(coinType, pred);
					}
					continue;
				}
				if (pred.getBuyPrice() > dto.getBuyPrice()) {
					pred.setBuyYn(true);
				}
				if (pred.getSellPrice() < dto.getSellPrice()) {
					pred.setSellYn(true);
				}
				if (pred.isBuyYn() && pred.isSellYn()) {
//					System.out.println(pred);
					stater.addSuccDeal(coinType, pred);
					it.remove();
				}
			}
		}
	}
	
	public static void print() {
		SortedSet<RangeKey> keys = new TreeSet<>(statMap.keySet());
		
//		for (Map.Entry<RangeKey, TestStatistics>  entry : statMap.entrySet())  {
		for (RangeKey key: keys) {
			System.out.println(key);
			statMap.get(key).print();
		}
	}
	
}
class RangeKey implements Comparable<RangeKey>{
	private double standardRange;
	private double lateRange;
	private double minCost;
	public double getStandardRange() {
		return standardRange;
	}
	public void setStandardRange(double standardRange) {
		this.standardRange = standardRange;
	}
	public double getLateRange() {
		return lateRange;
	}
	public void setLateRange(double lateRange) {
		this.lateRange = lateRange;
	}
	public double getMinCost() {
		return minCost;
	}
	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(lateRange);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(standardRange);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangeKey other = (RangeKey) obj;
		if (Double.doubleToLongBits(lateRange) != Double.doubleToLongBits(other.lateRange))
			return false;
		if (Double.doubleToLongBits(minCost) != Double.doubleToLongBits(other.minCost))
			return false;
		if (Double.doubleToLongBits(standardRange) != Double.doubleToLongBits(other.standardRange))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RangeKey [standardRange=" + standardRange + ", lateRange=" + lateRange + ", minCost=" + minCost + "]";
	}
	@Override
	public int compareTo(RangeKey arg0) {
		if (arg0.getStandardRange() > this.standardRange) {
			return 1;
		} else if (arg0.getStandardRange() == this.standardRange) {
			if (arg0.getLateRange() > this.lateRange) {
				return 1;
			} else if (arg0.getLateRange() == this.lateRange) {
				return 0;
			} else return -1;
		} else {
			return -1;
		}
	}
	
}