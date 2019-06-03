package engine;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import engine.analysis.AlgorithmManager;
import engine.analysis.PriceCollector;
import engine.analysis.statistic.PriceWindow;
import engine.common.CommonVariables;
import engine.deal.DealManager;
import engine.dto.DealDTO;
import engine.dto.MarketPriceDTO;
import engine.market.MarketApiFactory;
import engine.market.MarketInterface;

public class CoinDealer {
	private static MarketInterface market;
	private static int cnt = 0;
	public static void init() {
		// init market
		market = MarketApiFactory.create("binance");
		market.validateMarket("8yhG6i7G0gkR7uJCpSJ41npWhcY9uNvl4anSQqIGwkmfSd9bcKSpLbZ2y6F6T4Gj", "TMs3ZmqzxoVa5xRJrWrR00dc25lvMUXOkU9ANg23O5RxdUqLgvZwfivQxogmIB2L");		
		
		// init Algorith
		AlgorithmManager.init();
		CommonVariables.init("XRPBTC");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
				String current_time_str = time_formatter.format(System.currentTimeMillis());
				System.out.println("TIME IS :" + current_time_str );
				try {
					dealRun();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		};

		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 5 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
		
		// init schedular
		// set running status 
	}
	
	public static void dealRun() throws Exception {
		//1 preporcess --> like check if previous action has done.
		try {
			DealManager.updateOpenOrders(market.getOpenOrders());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		MarketPriceDTO price = null;
		//2 get price
		try {
			price = PriceCollector.getMarketData(market);
			System.out.println(price);
		} catch (Exception e) {
			e.printStackTrace();
			PriceWindow.cleanQueue();
		}
		if (price != null  ){
			PriceWindow.push(price);
			//3 run algorithm
			List<DealDTO> dealList = AlgorithmManager.run();
			
			//4 run market or pass
			for (DealDTO deal : dealList) {
//				System.out.println("RUN" + deal);
				DealManager.runDeal(CommonVariables.BASIC_TRADE_UNIT * deal.getBetUnit(), CommonVariables.BASIC_TRADE_COIN, deal, market, price);
			}
			
			//5 post process
			
		}
	}
	
	public static void main(String[] args) {
		init();
	}
}
