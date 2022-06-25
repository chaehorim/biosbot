package com.chochae.afes.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chochae.afes.asset.AssetManager;
import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.coins.CoinManager;
import com.chochae.afes.common.dto.ReturnMsg;
import com.chochae.afes.market.company.MarketInterface;
import com.chochae.afes.market.modal.IdInfo;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.market.service.IdManager;
import com.chochae.afes.market.service.MarketManager;
import com.chochae.afes.menu.MenuManager;
import com.chochae.afes.record.MsgCode;
import com.chochae.afes.record.RecordManager;
import com.chochae.afes.user.UserManager;

@Service
public class AfesInitializer {
	private static final Logger logger = LoggerFactory.getLogger(AfesInitializer.class);
	@PostConstruct
	public void init() {
		try {
			logger.info("[STARTING AFES INITIALIZAER x]" + AfesInitializer.class);
//			PropertiesManager.initProperties();

			UserManager.initUser();
			MenuManager.initMenu();
			MarketManager.initMarkets();
			CoinManager.init();
			
			AfesSchedular.startScheduler(0);
			AfesEngineManager.setStatus(AfesEngineManager.RUNNING);
			
//			test5();
			RecordManager.addRecord(MsgCode.VALIDATION_NEEDED, "Just Test", "bithumb", "KRW");
			RecordManager.addRecord(MsgCode.SERVER_ABNORMAL, "abnormal Test", "bithumb", "KRW");
			RecordManager.addRecord(MsgCode.TRADE_ERROR, "trade Test", "bithumb", "KRW");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//binance_upbit test
	private void test5() {
		AfesEngineManager.startTestMod();
		testValidate("bithumb", "a988854959d591b42593b7f1ac62d6dc", "29bf49afbf888e619a7f7cc73bea8010");
		testValidate("upbit_iron", "6SoaXxMFZeFYohYxD5xRl5UxD8mW2l0pYkFVtIUH", "k3aDH4thO8NTHMDmDG9Zl67hyJq7C4n71r0o3G3l");
		testValidate("upbit_shuk", "sW8xgZJL12ZiaDQK8ZtJr2MJjcOBXCqjqfzaK9aE", "TSknlj8tqk8xNX4qnWQQLQPBG2D8PbkreT5XFxGx");
		testValidate("upbit_bro", "50yfJ7BrxxRMpB1pL5ayPVHUmHmRvSOhqC0yIlzr", "UQ00qpSLKGIxiwc3MvS5VjBx79xeB0lj4fFmFFNH");

	}


	
	private void testValidate(String marketUserName,String publicKey, String privateKey) {
		try {
			MarketInfo market = MarketManager.getMarketByName(marketUserName);
			MarketInterface marketConn = market.getMarketCon();
			marketConn.validateMarket(publicKey, privateKey);
			List<AssetDto> assetList = marketConn.getAsset();
			market.setValidateYn(true);
			AssetManager.updateAsset(marketUserName, assetList);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(" VALIDATION ERROR " + marketUserName);
		}
	}
}

