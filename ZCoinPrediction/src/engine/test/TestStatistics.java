package engine.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import engine.dto.DealDTO;
import engine.dto.Prediction;

public class TestStatistics {
	private  Map<String, FunctionAB> funcAStatMap = new HashMap<String, FunctionAB> ();
	private Map<String, List<Long>> riseSuccTimeMap = new HashMap<String, List<Long>> ();
	private Map<String, List<Long>> riseFailTimeMap = new HashMap<String, List<Long>> ();
	private Map<String, List<Long>> downSuccTimeMap = new HashMap<String, List<Long>> ();
	private Map<String, List<Long>> downFailTimeMap = new HashMap<String, List<Long>> ();
	
	public void addDeal(DealDTO deal, String coinType) {
		funcAStatMap.putIfAbsent(coinType, new FunctionAB());
		funcAStatMap.get(coinType).addCount(deal.getVarA());
		funcAStatMap.get(coinType).addMinCost(deal.getVarA(), deal.getMinCost());
	}
	
	public void addSuccDeal(String coinType,Prediction pred) {
		funcAStatMap.get(coinType).addSuccCount(pred.getDeal().getVarA());
		riseSuccTimeMap.putIfAbsent(coinType, new ArrayList<Long>());
		downSuccTimeMap.putIfAbsent(coinType, new ArrayList<Long>());
		switch(pred.getDeal().getType()) {
		case STEADY: 
			break;
		case RISE:
			riseSuccTimeMap.get(coinType).add(pred.getTimeStamp());
			break;
		case DOWN:
			downSuccTimeMap.get(coinType).add(pred.getTimeStamp());
			break;
		default:
			break;
		}
	}
	
	public void addFailDeal(String coinType, Prediction pred) {
		riseFailTimeMap.putIfAbsent(coinType, new ArrayList<Long>());
		downFailTimeMap.putIfAbsent(coinType, new ArrayList<Long>());
		
		switch(pred.getDeal().getType()) {
		case STEADY: 
			break;
		case RISE:
			riseFailTimeMap.get(coinType).add(pred.getTimeStamp());
			break;
		case DOWN:
			downFailTimeMap.get(coinType).add(pred.getTimeStamp());
			break;
		default:
			break;
		}
	}
	
	public void print(){
//		for (Entry<String, FunctionAB> entry : funcAStatMap.entrySet()) {
//			System.out.println("coinType:" + entry.getKey());
//			riseSuccTimeMap.putIfAbsent(entry.getKey(), new ArrayList<Long>());
//			downSuccTimeMap.putIfAbsent(entry.getKey(), new ArrayList<Long>());
//			riseFailTimeMap.putIfAbsent(entry.getKey(), new ArrayList<Long>());
//			downFailTimeMap.putIfAbsent(entry.getKey(), new ArrayList<Long>());
//			
//			List<Long> timeList = riseSuccTimeMap.get(entry.getKey());
//			System.out.print("var riseSuccess = [");
//			for (Long time: timeList) {
//				System.out.print(time + ",");
//			}
//			System.out.print("];\nvar downSuccess = [");
//			timeList = downSuccTimeMap.get(entry.getKey());
//			for (Long time: timeList) {
//				System.out.print(time + ",");
//			}
//			System.out.print("];\nvar riseFail = [");
//			timeList = riseFailTimeMap.get(entry.getKey());
//			for (Long time: timeList) {
//				System.out.print(time + ",");
//			}
//			System.out.print("];\nvar downFail = [");
//			timeList = downFailTimeMap.get(entry.getKey());
//			for (Long time: timeList) {
//				System.out.print(time + ",");
//			}
//			System.out.println("];");
//		}
//		
		for (Entry<String, FunctionAB> entry : funcAStatMap.entrySet()) {
			System.out.println("coinType:" + entry.getKey());
			entry.getValue().print();
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(Math.round((1662 * 10000/34194)) / 100.0);
	}
	class FunctionAB{
		private Map<Double, AStatic> aMap = new HashMap<Double, AStatic>();
		private Map<Double, Double> minCostMap = new HashMap<Double, Double>();
		public void addCount(double a) {
			aMap.putIfAbsent(a, new AStatic());
			AStatic aSta = aMap.get(a);
			aSta.addCount();
		}
		
		public void addSuccCount(double a) {
			AStatic aSta = aMap.get(a);
			aSta.addSuccessCnt();
		}
		
		public void addMinCost(double a, double minCost) {
			minCostMap.putIfAbsent(a, (double) 0);
			minCostMap.put(a, minCostMap.get(a) + minCost);
		}
		
		public void print() {
			Map<Double, AStatic> treeMap = new TreeMap<Double, AStatic>(aMap); 
			long riseSuc = 0;
			long riseTot = 0;
			long downSuc = 0;
			long downTot = 0;
			for (Entry<Double, AStatic> entry : treeMap.entrySet()) {
//				System.out.println(entry.getValue() + "^t" + entry.getKey() + ", minCost=[" + minCostMap.get(entry.getKey()) + "],avgCost=["
//						+ (minCostMap.get(entry.getKey())/ entry.getValue().getCount())+ ""); 
				if (entry.getKey() > 0) {
					riseTot += entry.getValue().getCount();
					riseSuc += entry.getValue().getSuccessCnt();
				} else if (entry.getKey() < 0) {
					downSuc += entry.getValue().getSuccessCnt();
					downTot += entry.getValue().getCount();
				}
			}
			if (riseTot == 0)
				riseTot = 1;
			if (downTot == 0)
				downTot = 1;
			System.out.println("RISE :[" + Math.round((riseSuc * 10000/riseTot) ) / 100.0 + "% /" + riseSuc +"/" + riseTot + "], "
					+ "DOWN:[" + Math.round((downSuc * 10000/downTot) ) / 100.0 + "% /" + downSuc +"/" + downTot + "], "
					+ "TOT:[" + Math.round(((riseSuc + downSuc) * 10000/(riseTot + downTot)) ) / 100.0 + "% /" + (riseSuc + downSuc) +"/" + (riseTot + downTot) + "], ");

		}
	}
	
	class AStatic{
		private long count;
		private long successCnt;
		
		public AStatic() {
			super();
			this.count = 0;
			this.successCnt = 0;
		}
		public long getCount() {
			return count;
		}
		public void addCount() {
			this.count++;
		}
		public long getSuccessCnt() {
			return successCnt;
		}
		public void addSuccessCnt() {
			this.successCnt++;
		}
		@Override
		public String toString() {
//			return "AStatic [count=" + successCnt + "/"+ count + ", rate=" + Math.round(successCnt * 10000 / count ) / 100 + "]";
			return successCnt + "/"+ count + "^t" + Math.round(successCnt * 10000 / count ) / 100 + "%";
		}
		
	}
}
