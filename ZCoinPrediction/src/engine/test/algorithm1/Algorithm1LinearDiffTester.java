package engine.test.algorithm1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import engine.analysis.alogorithm1.Algorithm1;
import engine.analysis.alogorithm1.Algorithm1Cache;
import engine.analysis.alogorithm1.Algorithm1Result;
import engine.analysis.alogorithm1.Algorithm1StatisticAnalyzer;
import engine.analysis.alogorithm1.FunctionResult;
import engine.analysis.statistic.PriceWindow;
import engine.dto.DealDTO;
import engine.dto.MarketPriceDTO;
import engine.test.Algorithm1Tester;
import engine.test.algorithm1.dto.LinearDiffDTO;
import engine.test.tool.FileSaver;

public class Algorithm1LinearDiffTester {

	public static final double PROFIT_RANGE = 0.002;
	public static final int MAX_QUEUE = 100;
	public static int VALIDATE_PERIOD = 10 * 12;
	public static final String marketName = "binance";
	public static long totalCnt = 0;
	private static boolean functionSwitch = false;
	private static Queue<FunctionResult> dealQueue = new LinkedList<FunctionResult>();
	private static LinearDiffDTO ldDto = new LinearDiffDTO();
	private static List<LinearDiffDTO> linearList = new ArrayList<LinearDiffDTO>();
	
	//TEST
	public static final  double IGNORE_MINCOST = 1;
	private static Map<String,  List<String>> getFileNames() {
//		String folderName = "C:\\work\\log\\bithumb.tar\\bithumb\\201809\\";
//		String folderName = "F:\\work\\log\\binance.tar\\detail\\";
		String folderName = "./log/";
		
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		Map<String,  List<String>> coinFileMap = new HashMap<String, List<String>>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				int pos = fileName.indexOf("20");
				String coinType = fileName.substring(0, pos);
				if (coinType.length() == 0) {
					System.out.println(fileName);
				}
				if (!coinFileMap.containsKey(coinType))
					coinFileMap.put(coinType, new ArrayList<String>());
				coinFileMap.get(coinType).add(folderName + fileName);
			} 
		}
		return coinFileMap;
	}
	
	private static List<MarketPriceDTO> readFile(String fileName, String marketName)  {
		BufferedReader br = null;
		String contents = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    contents = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		List<MarketPriceDTO> priceList = new ArrayList<MarketPriceDTO>();
		if ("bithumb".equals(marketName)) {
			String[] tmps = contents.split(">");
			
			
			String[] datas = tmps[1].split(";");
			for (int i = 0, size = datas.length; i < size-1; i ++) {
				
				String[] value = datas[i].split(":");			
				MarketPriceDTO price = new MarketPriceDTO();
				price.setBuyPrice(Double.parseDouble(value[1]));
				price.setSellPrice(Double.parseDouble(value[2]));
				price.setTimeStamp(Long.parseLong(value[0]));
				price.setPrice();			
				priceList.add(price);
//	        System.out.println(price);
			}
		}
		if ("bitfinex".equals(marketName)) {
			String[] datas = contents.split(";");
			for (int i = 0, size = datas.length; i < size-1; i ++) {
				
				String[] value = datas[i].split(":");			
				MarketPriceDTO price = new MarketPriceDTO();
				price.setBuyPrice(Double.parseDouble(value[1]));
				price.setSellPrice(Double.parseDouble(value[3]));
				price.setTimeStamp(Long.parseLong(value[0]));
				price.setPrice();			
				priceList.add(price);
//				 System.out.println(price);
			}
		} 
		if ("binance".equals(marketName)) {
			String[] datas = contents.split(";");
			for (int i = 0, size = datas.length; i < size-1; i ++) {
				
				String[] value = datas[i].split(":");			
				MarketPriceDTO price = new MarketPriceDTO();
				price.setBuyPrice(Double.parseDouble(value[1]));
				price.setSellPrice(Double.parseDouble(value[3]));
				price.setTimeStamp(Long.parseLong(value[0]) * 1000);
				price.setPrice();			
				priceList.add(price);
//				 System.out.println(price);
			}
		}
		return priceList;
	}
	public void run(double range, double aaRange) {
		
		Algorithm1 algo = new Algorithm1();
		algo.setARange(Math.round(aaRange*100) /100.0);
		algo.setRange(Math.round(range*100) /100.0);
		Map<String,  List<String>> fileMap = getFileNames();
		
		//TEST
		for (Map.Entry<String,List<String>> entry : fileMap.entrySet()) {
			
			List<String> fileList = entry.getValue();
			for (String fileName : fileList) {
				List<MarketPriceDTO> priceList = readFile(fileName, marketName);
				
				for (MarketPriceDTO price : priceList) {
					PriceWindow.push(price);
					
					if (PriceWindow.getPriceStatistic().size() < PriceWindow.getQueueSize()) {
						continue;
					}
					
					algo.setQueueSize(PriceWindow.getQueueSize());
					DealDTO deal = algo.run();
					if (deal == null) {
						functionSwitch = false;
						continue;
					}
					if (functionSwitch) {
						ldDto.update(deal);
						if (turnOffCondition(deal)) {
							ldDto.finalizeDto(deal, price.getTimeStamp(), price.getSellPrice());
							linearList.add(ldDto);
							FileSaver.saveToFile(ldDto.toString());
							functionSwitch = false;
						}
					}else {
						if (checkTurnonCondition(deal)) {
							functionSwitch = true;
							ldDto = new LinearDiffDTO();
							ldDto.init(deal, price.getTimeStamp(), price.getBuyPrice());
						}
					}
		
				}
			}
			PriceWindow.cleanQueue();
		}
	}
	
	// need test in here.
	private boolean checkTurnonCondition(DealDTO deal) {
		
		Algorithm1Result res = (Algorithm1Result)deal.getResult();
		dealQueue.add(res.getLatest());
		if (dealQueue.size() > 5) {
			dealQueue.poll();
		} else {
			return false;
		}
		
		if (res.getLatest().getA() > res.getStandard().getA()) {
			//Condition 1 correct
			FunctionResult origin = null;
			for (FunctionResult newOne : dealQueue) {
				if (origin != null){
					if (origin.getA() >= newOne.getA()) {
						return false;
					}
				}
				origin  = newOne;
			}
			return true;
		}
		
		return false;
	}
	
	// END Condition
	private boolean turnOffCondition(DealDTO deal) {
		Algorithm1Result res = (Algorithm1Result)deal.getResult();
		if (res.getLatest().getA() < res.getStandard().getA()) {
			return true;
		}
		return false;
	}
	
	
	public static void readCertainFile() {
		readFile("C:\\work\\log\\bithumb.tar\\bithumb\\201809\\XRP20181012.dat", "bithumb");
	}
	public static void main(String[] args) {
		//STATISTICAL USE
		FileSaver.init();

		long curTime = System.currentTimeMillis();
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
		System.out.println(time_formatter.format(curTime));
		for (int j = 5; j < 6; j = j + 1) {
		for (int i = 40; i < 45; i = i + 5) {
			Algorithm1StatisticAnalyzer.init(0.5, 3, 0.5, 1.0, 9000);
			for (double aa = 0.5; aa < 0.6; aa += 0.1 ) {
				for (double a = 0.5; a < 0.6; a += 0.1) {
					System.out.println("TIME:"  + i + " TIME2 : " + j +"a :" + a + ", aa : " + aa);
					PriceWindow.setQUEUE_SIZE(i);
					Algorithm1Cache.setQueueSize(j);
					Algorithm1Tester tester = new Algorithm1Tester();
					tester.run(a, aa);
				}
			}
			Algorithm1StatisticAnalyzer.print();
			}
		}
		System.out.println("cost Time : " + (System.currentTimeMillis() - curTime) / 6000);
		FileSaver.finalizer();
	}
	
	

}
