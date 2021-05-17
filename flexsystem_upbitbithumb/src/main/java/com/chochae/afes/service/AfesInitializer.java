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
			
			test5();
			RecordManager.addRecord(MsgCode.VALIDATION_NEEDED, "Just Test", "bithumb", "KRW");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//binance_upbit test
	private void test5() {

	}


	
	private void testValidate(String marketUserName,String publicKey, String privateKey) {
		MarketInfo market = MarketManager.getMarketByName(marketUserName);
		MarketInterface marketConn = market.getMarketCon();
		marketConn.validateMarket(publicKey, privateKey);
		List<AssetDto> assetList = marketConn.getAsset();
		market.setValidateYn(true);
		AssetManager.updateAsset(marketUserName, assetList);
	}
}

