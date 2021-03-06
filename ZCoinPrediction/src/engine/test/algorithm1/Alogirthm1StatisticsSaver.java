package engine.test.algorithm1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import engine.analysis.alogorithm1.Algorithm1;
import engine.analysis.alogorithm1.Algorithm1Cache;
import engine.analysis.alogorithm1.Algorithm1StatisticAnalyzer;
import engine.analysis.statistic.PriceWindow;
import engine.dto.DealDTO;
import engine.dto.DealType;
import engine.dto.MarketPriceDTO;
import engine.dto.Prediction;
import engine.test.TestStatistics;
import engine.test.tool.FileSaver;

public class Alogirthm1StatisticsSaver {

		public static final double PROFIT_RANGE = 0.002;
		public static final int MAX_QUEUE = 100;
		public static int VALIDATE_PERIOD = 10 * 12;
		public static final String marketName = "binance";
		public static long totalCnt = 0;
		
		//TEST
		public static final  double IGNORE_MINCOST = 1;
		private static Map<String,  List<String>> getFileNames() {
//			String folderName = "C:\\work\\log\\bithumb.tar\\bithumb\\201809\\";
//			String folderName = "F:\\work\\log\\binance.tar\\detail\\";
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
//		        System.out.println(price);
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
//					 System.out.println(price);
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
//					 System.out.println(price);
				}
			}
			return priceList;
		}
		public void run(double range, double aaRange) {
			
			Algorithm1 algo = new Algorithm1();
			algo.setARange(Math.round(aaRange*100) /100.0);
			algo.setRange(Math.round(range*100) /100.0);
			Map<String,  List<String>> fileMap = getFileNames();
//			fileMap.put("xrp", new ArrayList<String>(Arrays.asList("F:\\work\\log\\binance.tar\\201810\\BTCUSDT20181019.dat")));
			
			//TEST
			TestStatistics stater = new TestStatistics();
			for (Map.Entry<String,List<String>> entry : fileMap.entrySet()) {
				String coinType = entry.getKey();
//				System.out.println(coinType);
				Result result = new Result();
				
				List<Prediction> procQueue = new ArrayList<Prediction>();
				long dataCount = 0;
				List<String> fileList = entry.getValue();
				for (String fileName : fileList) {
					List<MarketPriceDTO> priceList = readFile(fileName, marketName);
					
					for (MarketPriceDTO price : priceList) {
//						System.out.println(price);
						PriceWindow.push(price);
						dataCount++;
						checkNewValue(price, procQueue, dataCount, result, stater, coinType);
						
						if (PriceWindow.getPriceStatistic().size() < PriceWindow.getQueueSize()) {
							FileSaver.saveToFile(null, price.getTimeStamp());
							continue;
						}
						
						algo.setQueueSize(PriceWindow.getQueueSize());
						DealDTO deal = algo.run();
						FileSaver.saveToFile(deal, price.getTimeStamp());
						
					}
				}
				PriceWindow.cleanQueue();
			}
//			stater.print();
		}
		
		private static void checkNewValue(MarketPriceDTO dto, List<Prediction> predList, long count, Result result, TestStatistics stater, String coinType) {
			Algorithm1StatisticAnalyzer.checkStatistics(dto, count, coinType);
			for (Iterator<Prediction> it = predList.iterator(); it.hasNext();) {
				Prediction pred = it.next();
				if (count - pred.getIndex() > VALIDATE_PERIOD) {
					it.remove();
					result.addFailValue(pred.getDeal().getType());
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
					result.addSuccessValue(pred.getDeal().getType());
					if (pred.getDeal().getType() != DealType.STEADY) {
//						System.out.println("Prediction successed." + pred);
					}
					stater.addSuccDeal(coinType, pred);
					it.remove();
				}
			}
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
				System.out.println("TIME:"  + i + " TIME2 : " + j );
				PriceWindow.setQUEUE_SIZE(i);
				Algorithm1Cache.setQueueSize(j);
				Alogirthm1StatisticsSaver tester = new Alogirthm1StatisticsSaver();
//						FileSaver.saveToFile(i + "<" + j + "@");
				tester.run(0.5, 0.5);
//						tester.run(0.5, 0.5);
				}
			}
			System.out.println("cost Time : " + (System.currentTimeMillis() - curTime) / 6000);
			FileSaver.finalizer();
		}
		
		class Result{
			private long steadyCnt = 0;
			private long steadyFailed = 0;
			private long steadySuccCnt = 0;
			private long downCnt = 0;
			private long downFailed = 0;
			private long downSuccCnt = 0;
			private long riseCnt = 0;
			private long riseFailed = 0;
			private long riseSuccCnt = 0;
			
			
			public long getSteadyCnt() {
				return steadyCnt;
			}
			public void addSteadyCnt() {
				this.steadyCnt++;
			}
			public long getSteadyFailed() {
				return steadyFailed;
			}
			public long getDownCnt() {
				return downCnt;
			}
			public void addDownCnt() {
				this.downCnt++;
			}
			public long getDownFailed() {
				return downFailed;
			}
			public long getRiseCnt() {
				return riseCnt;
			}
			public void addRiseCnt() {
				this.riseCnt++;
			}
			public long getRiseFailed() {
				return riseFailed;
			}
			public void setValues(DealType type) {
				switch(type) {
				case STEADY:
					steadyCnt++;
					break;
				case DOWN:
					downCnt++;
					break;
				case RISE:
					riseCnt++;
					break;
				default:
					break;
				}
			}
			
			public void addFailValue(DealType type) {
				switch(type) {
				case STEADY:
					steadyFailed++;
					break;
				case DOWN:
					downFailed++;
					break;
				case RISE:
					riseFailed++;
					break;
				default:
					break;
				}
			}
			public void addSuccessValue(DealType type) {
				switch(type) {
				case STEADY:
					steadySuccCnt++;
					break;
				case DOWN:
					downSuccCnt++;
					break;
				case RISE:
					riseSuccCnt++;
					break;
				default:
					break;
				}
			}
			@Override
			public String toString() {
				String str = null;
				try {
					if (downCnt == 0) {
						downCnt = 1;
					}
					if (riseCnt == 0) {
						riseCnt = 1;
					}
					if (steadyCnt == 0) {
						steadyCnt = 1;
					}
					str = "Result [steadyCnt=" + steadyCnt + ", steadyFailed=" + steadyFailed + ", steadySuccCnt="
						+ steadySuccCnt + ", steadyPerc=" + Math.round((steadySuccCnt* 10000/steadyCnt) ) / 100.0 + ", downCnt=" + downCnt + ", downFailed=" + downFailed + ", downSuccCnt="
						+ downSuccCnt + ", downPerc=<" + Math.round((downSuccCnt* 10000/downCnt) ) / 100.0 + ">, riseCnt=" + riseCnt + ", riseFailed=" + riseFailed + ", riseSuccCnt="
						+ riseSuccCnt + ",risePerc=<" + Math.round((riseSuccCnt * 10000/riseCnt) ) / 100.0+ ">, totPerc=<" +Math.round(((riseSuccCnt + downSuccCnt) * 10000/(riseCnt + downCnt)) ) / 100.0 
						+ ">, total=" + (riseCnt + downCnt) + "]";
				} catch(Exception e) {
					e.printStackTrace();
				}
				return str;
			}
		}
	}