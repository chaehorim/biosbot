package engine.analysis;

import java.util.ArrayList;
import java.util.List;

import engine.analysis.alogorithm1.Algorithm1;
import engine.dto.DealDTO;
import engine.dto.DealType;

public class AlgorithmManager {

	private static List<Algorithm> algoList = new ArrayList<Algorithm>();
	public static void init(){
		algoList.add(new Algorithm1());
	}
	
	public static List<DealDTO> run() {
		List<DealDTO> dealList = new ArrayList<DealDTO>();
		for (Algorithm algo : algoList) {
			DealDTO deal = algo.run();
			System.out.println(deal);
			if (deal != null && deal.getType() != DealType.NONE) {
				deal.setTimestamp(System.currentTimeMillis());
				dealList.add(deal);
			}
		}
		
		return dealList;
	}
}
