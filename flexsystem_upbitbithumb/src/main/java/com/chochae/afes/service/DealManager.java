package com.chochae.afes.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chochae.afes.asset.AssetManager;
import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.coins.CoinManager;
import com.chochae.afes.coins.model.CoinInfo;
import com.chochae.afes.coins.model.CoinOptionInfo;
import com.chochae.afes.market.company.MarketInterface;
import com.chochae.afes.market.company.dto.OrderBook;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.market.service.MarketManager;
import com.chochae.afes.market.service.dto.TradeResultInfo;
import com.chochae.afes.offer.dto.DealInfo;
import com.chochae.afes.record.MsgCode;
import com.chochae.afes.record.RecordManager;

public class DealManager {
	private static SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	// Markets list from market -> to market , default profit
	// Dealing coins.
	private MarketInfo fromMarket = new MarketInfo();
	private MarketInfo toMarket =  new MarketInfo();
	private CoinInfo coin;
	public void init(MarketInfo fromMarket, MarketInfo toMarket, CoinInfo coin) {
		this.fromMarket = fromMarket;
		this.toMarket = toMarket;
		this.coin = coin;
	}
	
	public void startOffer() {
		
		// 1. ask for prices
		// bithumb , take all prices. 
		// upbit, ....
		Map<String, OrderBook> orderbookMap = new HashMap<String, OrderBook>();

		List<CoinOptionInfo> coinOptionsList = CoinManager.getCoinOptionList();
		Thread t1 = new priceGetThread(0, fromMarket, coin, orderbookMap); 
		Thread t2 = new priceGetThread(0, toMarket, coin, orderbookMap); 
		
		long curTime = System.currentTimeMillis();
		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (orderbookMap.size() != 2) {
			System.out.println(time_formatter.format(System.currentTimeMillis()) +  ": NOT ENOUGH DATA :" + coin);
			return;
		}
		// 2. calculate
		double profit1 = orderbookMap.get(toMarket.getMarketUserName()).bids[1].price * 100 / orderbookMap.get(fromMarket.getMarketUserName()).asks[1].price - 100;
		double profit2 = orderbookMap.get(fromMarket.getMarketUserName()).bids[1].price * 100 / orderbookMap.get(toMarket.getMarketUserName()).asks[1].price - 100;
//		if (profit1 > 0.5 || profit2 > 0.5) {
//			AfesEngineManager.addSuccessCnt();
//		}

		DealInfo offer1 = new DealInfo(curTime, fromMarket.getMarketUserName(), toMarket.getMarketUserName(), coin.getCoinType(), profit1, 1000000, orderbookMap.get(fromMarket.getMarketUserName()).asks[1].price, orderbookMap.get(toMarket.getMarketUserName()).bids[1].price);
		AfesEngineManager.addOffer(offer1);
		DealInfo offer2 = new DealInfo(curTime, toMarket.getMarketUserName(), fromMarket.getMarketUserName(), coin.getCoinType(), profit2, 1000000, orderbookMap.get(toMarket.getMarketUserName()).asks[1].price, orderbookMap.get(fromMarket.getMarketUserName()).bids[1].price);
		AfesEngineManager.addOffer(offer2);
//		System.out.println( coin.getCoinType() + " PROFIT :" + String.format("%.3f", profit1) + "%" + "  <==>" + String.format("%.3f", profit2) + "%" + " ==> " + AfesEngineManager.getSuccessCnt());
		
		// 3. send order
		if (!(AfesEngineManager.isTradeYn() && fromMarket.isValidateYn() && toMarket.isValidateYn())) {
			System.out.println(time_formatter.format(System.currentTimeMillis()) +  ": TRADE STOP!!! tradeYn:" + AfesEngineManager.isTradeYn() + ", " + fromMarket.getMarketUserName() + ":" + fromMarket.isValidateYn() + ", " + toMarket.getMarketUserName() + ":" + toMarket.isValidateYn());
			return;
		}
		
		for (CoinOptionInfo option : coinOptionsList) {
			if (fromMarket.getMarketId().equals(option.getFromMarket()) && toMarket.getMarketId().equals(option.getToMarket()) && coin.getCoinType().equals(option.getCoinType())  && option.isActiveYn() ) {
				offer1.setBetAmount(option.getBetAmount());
				if ((option.getBetProfit() <= profit1 ) ){
					System.out.println(option.getFromMarket() + " : " + option.getBetProfit() + " <--->" + profit1);
					calculateAmount(offer1, option);
					if (checkEnoughCoin(offer1, option)) {
						if (AfesEngineManager.isTestMode())  {
							makeNewTestDeal(offer1);
						} else {
							makeDeal(offer1);
					
						}
					}
				}
			}
			if (toMarket.getMarketId().equals(option.getFromMarket()) && fromMarket.getMarketId().equals(option.getToMarket()) && coin.getCoinType().equals(option.getCoinType())  && option.isActiveYn() ) {
				offer2.setBetAmount(option.getBetAmount());
				if (option.getBetProfit() <= profit2) {
					System.out.println(option.getFromMarket() + " : " + option.getBetProfit() + " <--->" + profit2);
					calculateAmount(offer2, option);
					if (checkEnoughCoin(offer2, option)) {
						if (AfesEngineManager.isTestMode())  {
							makeNewTestDeal(offer2);
						} else {
							makeDeal(offer2);
						}
					}
				}
			}
			
		}
	}
	
	private void calculateAmount(DealInfo offer, CoinOptionInfo option) {
		offer.setBaseAmount(option.getBetAmount());
		double pow = Math.pow(10, coin.getPointLength());
		offer.setCoinAmount(Math.round(option.getBetAmount() * pow / offer.getSearchBuyPrice()) / pow);
	}
	
	private boolean checkEnoughCoin(DealInfo offer, CoinOptionInfo option) {
		if (AfesEngineManager.isTestMode()) 
			return true;
		
		Map<String, List<AssetDto>> assetMap = AssetManager.getAssetMap();
		List<AssetDto> fromAsset = assetMap.get(offer.getFromMarket());
		boolean hasEnoughKrw = false;
		boolean hasEnoughCoin = false;
		for (AssetDto asset : fromAsset) {
			if (CoinManager.BASE_COIN.equals(asset.getCoinType()))  {
				if(option.getBetAmount() * 5 < asset.getFreeAmount()) {
					hasEnoughKrw = true;
				}
			}
		}
		if (!hasEnoughKrw) {
			System.out.println(offer.getFromMarket() +  CoinManager.BASE_COIN + " IS NOT ENOUGH!!!!");
			RecordManager.addRecord(MsgCode.NOT_ENOUGH_COIN, "Coin IS NOT ENOUGH", offer.getFromMarket(), CoinManager.BASE_COIN);
		}
		List<AssetDto> toAsset = assetMap.get(offer.getToMarket());
		for (AssetDto asset : toAsset) {
			if (offer.getCoinType().equals(asset.getCoinType())) {
				System.out.println(offer.getCoinType() + " " + offer.getCoinAmount() + " " + asset.getFreeAmount());
				if(offer.getCoinAmount() * 3 < asset.getFreeAmount()) {
					hasEnoughCoin = true;
				}
			}
		}
		if (!hasEnoughCoin){
			System.out.println(offer.getToMarket() + " " + offer.getCoinType() + " IS NOT ENOUGH!!!!");
			RecordManager.addRecord(MsgCode.NOT_ENOUGH_COIN, "Coin IS NOT ENOUGH", offer.getToMarket(), offer.getCoinType());
		}
		
		return (hasEnoughCoin && hasEnoughKrw) ? true : false;
	}
	
	// FIXME: MAKE REAL DEAL
	private void makeDeal(DealInfo offer) {
		MarketInfo fromMarket = MarketManager.getMarketByName(offer.getFromMarket());
		MarketInfo toMarket = MarketManager.getMarketByName(offer.getToMarket());
		String coinType = offer.getCoinType();
		
		Thread t1 = new MarketTrade(0, fromMarket, coinType, offer, "buy"); 
		Thread t2 = new MarketTrade(0, toMarket, coinType, offer, "sell"); 
		
		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (offer.getBuyPrice() != Double.NaN && offer.getSellPrice() != Double.NaN && offer.getBuyPrice() != 0){
			double profit = (offer.getSellPrice() - offer.getBuyPrice()) * 100 / offer.getBuyPrice() ;
			offer.setProfit(profit);
		}
		AfesEngineManager.addDeal(offer);
	}

	private  void makeNewTestDeal(DealInfo deal) {
		Map<String, OrderBook> orderbookMap = new HashMap<String, OrderBook>();
		Thread t1 = new priceGetThread(0, fromMarket, coin, orderbookMap); 
		Thread t2 = new priceGetThread(0, toMarket, coin, orderbookMap); 
		
		long curTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (orderbookMap.size() != 2) {
			System.out.println( "NOT ENOUGH DATA :" + coin);
			return;
		}
		// 2. calculate
		double profit1 = orderbookMap.get(toMarket.getMarketUserName()).bids[0].price * 100 / orderbookMap.get(fromMarket.getMarketUserName()).asks[0].price - 100;
		double profit2 = orderbookMap.get(fromMarket.getMarketUserName()).bids[0].price * 100 / orderbookMap.get(toMarket.getMarketUserName()).asks[0].price - 100;
		if (fromMarket.getMarketUserName().equals(deal.getFromMarket()) && toMarket.getMarketUserName().equals(deal.getToMarket())&& coin.getCoinType().equals(deal.getCoinType())) {
			deal.setProfit(profit1);
			deal.setBuyPrice(orderbookMap.get(fromMarket.getMarketUserName()).asks[0].price);
			deal.setSellPrice(orderbookMap.get(toMarket.getMarketUserName()).bids[0].price);
			AfesEngineManager.addDeal(deal);
		}
		if (toMarket.getMarketUserName().equals(deal.getFromMarket()) && fromMarket.getMarketUserName().equals(deal.getToMarket()) && coin.getCoinType().equals(deal.getCoinType()) ) {
			deal.setProfit(profit2);
			deal.setBuyPrice(orderbookMap.get(toMarket.getMarketUserName()).asks[0].price);
			deal.setSellPrice(orderbookMap.get(fromMarket.getMarketUserName()).bids[0].price);
			AfesEngineManager.addDeal(deal);
		}
	}
}

class MarketTrade extends Thread {
    public int processingCount = 0;
    public MarketInfo market;
    public MarketInterface marketCon;
    public String coinType;
    public String tradeType;
    public DealInfo deal;
    
    MarketTrade(int processingCount, MarketInfo market, String coinType, DealInfo deal, String tradeType) {
        this.processingCount = processingCount;
        this.marketCon = market.getMarketCon();
        this.market = market;
        this.coinType = coinType;
        this.tradeType = tradeType;
        this.deal = deal;
    }

    @Override
    public void run() {
    	TradeResultInfo msg = new TradeResultInfo(false,"");
    	try {
	    	if ("buy".equals(tradeType)) {
	    		msg = marketCon.marketBuy(coinType, deal.getCoinAmount(), deal.getSearchBuyPrice(), CoinManager.BASE_COIN);
	    		if (msg.isSuccessYn()) {
	    			deal.setBuyPrice(msg.getFinalPrice());
	    		}
	    	} else if ("sell".equals(tradeType)) {
	    		msg = marketCon.marketSell(coinType, deal.getCoinAmount(), deal.getSearchSellPrice(), CoinManager.BASE_COIN);
	    		if (msg.isSuccessYn()) {
	    			deal.setSellPrice(msg.getFinalPrice());
	    		}
	    	}
	    	if (!msg.isSuccessYn()) {
	    		RecordManager.addRecord(MsgCode.TRADE_ERROR, "SomeTrade did not go well", market.getMarketUserName(), coinType);
	    	}
    	} catch (Exception e) {
    		if (!msg.isSuccessYn()) {
	    		RecordManager.addRecord(MsgCode.TRADE_ERROR, "SomeTrade did not go well", market.getMarketUserName(), coinType);
	    	}
    		e.printStackTrace();
    	}
    }
}

class priceGetThread extends Thread {
    public int processingCount = 0;
    public MarketInfo market;
    public CoinInfo coin;
    public Map<String, OrderBook> orderbookMap;

    priceGetThread(int processingCount, MarketInfo market, CoinInfo coin, Map<String, OrderBook> orderbookMap) {
        this.processingCount = processingCount;
        this.market = market;
        this.coin = coin;
        this.orderbookMap = orderbookMap;
    }

    @Override
    public void run() {
    	
		MarketInterface marketConn = market.getMarketCon();
		OrderBook orderbook = marketConn.getOrderBook(coin.getCoinType(), CoinManager.BASE_COIN);
		if (orderbook != null) {
			orderbookMap.put(market.getMarketUserName(), orderbook);
		} else {
			System.out.println(market.getMarketUserName());
		}
    }
}
