package com.chochae.afes.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chochae.afes.asset.AssetManager;
import com.chochae.afes.coins.CoinManager;
import com.chochae.afes.coins.model.CoinInfo;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.market.service.MarketManager;
import com.chochae.afes.offer.dao.OfferDAO;
import com.chochae.afes.offer.dto.DealInfo;
import com.chochae.afes.service.trigger.AfesEngineTrigger;

public class AfesEngineManager {
	private static final Logger logger = LoggerFactory.getLogger(AfesEngineManager.class);
	private static SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	
	public static   int RUNNING  	= 	1;
	public static   int STOP  		= 	0;
	public static   int PAUSE  		= 	2;
	public static final int		DEFAULT_LOCALE = 86;
	private  static int status		= 	STOP;
	private static boolean tradeYn = true;
	private static  int offerCnt    = 0;
	private static  long successCnt = 0;
	
	private static boolean testModeYn = true;
	
	
	private static List<DealInfo>    offerList = new ArrayList<DealInfo>();
	private static List<DealInfo>    dealList = new ArrayList<DealInfo>();
	
	public static long getSuccessCnt() {
		return successCnt;
	}
	
	public static void addSuccessCnt() {
		successCnt++;
	}
	
	public static List<DealInfo> getOfferList() {
		return offerList;
	}
	
	public static void addOffer(DealInfo deal) {
		offerList.add(deal);
		if (offerList.size() > 100) {
			offerList.remove(0);
		}
	}
	
	public static boolean isTradeYn() {
		return tradeYn;
	}
	
	public static void setTradYn(boolean val){
		tradeYn = val;
	}
	public static List<DealInfo> getDealList() {
		return dealList;
	}
	
	public static void addDeal(DealInfo deal) {
		OfferDAO dao = DbLoader.getOfferDBConnection();
		dao.insertDeal(deal);
		dealList.add(deal);
		if (dealList.size() > 100) {
			dealList.remove(0);
		}
	//	AssetManager.updateAssumeAsset(deal);
	}
	
	public static int getStatus() {
		return status;
	}
	public static void setStatus(int status) {
		AfesEngineManager.status = status;
	}
	
	public static void generateOffer(int sequence) {
		offerCnt++;
		
		
		if (AfesEngineTrigger.prevTrigger()) {
			if (offerCnt % 5 == 0) {
				AssetManager.checkAsset();
			}
			List<CoinInfo> coinList = CoinManager.getDealCoinList();
			CoinInfo coin = coinList.get(sequence % coinList.size());
			
			MarketInfo fromMarket = MarketManager.getFromMarket();
			MarketInfo toMarket = MarketManager.getToMarket();
			DealManager man = new DealManager();
			man.init(fromMarket, toMarket, coin);
			man.startOffer();
		}
	}
	
	public static void setSwitch(int command) {
		status = command;
	}
	
	public static int getOfferCount() {
		return offerCnt;
	}
	
	public static void resetOfferCount() {
		offerCnt = 0;
	}
	
	public static boolean isTestMode() {
		return testModeYn;
	}
	
	public static void main(String[] args) {
		CoinManager.init();
		try {
			for (int i = 0; i < 10; i ++) {
				generateOffer(i);
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
