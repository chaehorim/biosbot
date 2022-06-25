package com.chochae.afes.coins;

import java.util.ArrayList;
import java.util.List;

import com.chochae.afes.coins.dao.CoinDAO;
import com.chochae.afes.coins.model.CoinInfo;
import com.chochae.afes.coins.model.CoinOptionInfo;
import com.chochae.afes.service.DbLoader;

public class CoinManager {
	private static List<CoinInfo> coinList = new ArrayList<CoinInfo>();
	private static List<CoinInfo> dealCoinList = new ArrayList<CoinInfo>();
	public static String    BASE_COIN;
	
	
	private static List<CoinOptionInfo> coinOptionList = new ArrayList<CoinOptionInfo>();
	public static void init() {
		CoinDAO coindao = DbLoader.getCoinDBConnection();
		coinOptionList = coindao.selectCoinOption();
		coinList = coindao.selectCoin();
		for (CoinInfo coin: coinList) {
			if (coin.isBaseCoin()) {
				BASE_COIN = coin.getCoinType();
				continue;
			}
			if (coin.isUseYn()) {
				coin.setRunningCoin(true);
				dealCoinList.add(coin);
			}
		}
	}
	
	public static List<CoinInfo> getCoinList() {
		return coinList;
	}
	public static List<CoinInfo> getDealCoinList() {
		return dealCoinList;
	}
	public static CoinInfo getCoinByType(String coinId) {
		for (CoinInfo coin : dealCoinList) {
			if (coinId.equals(coin.getCoinType())) {
				return coin;
			}
		}
		return null;
	}
	public static List<CoinInfo> getRunningCoinList() {
		List<CoinInfo> tmpList = new ArrayList<CoinInfo>();
		for (CoinInfo coin : dealCoinList) {
			if (coin.isRunningCoin()) {
				tmpList.add(coin);
			}
		}
		return tmpList;
	}
	
 	public static List<CoinOptionInfo> getCoinOptionList () {
		return coinOptionList;
	}
 	
 	public static List<CoinOptionInfo> getRunningCoinOptionList() {
 		List<CoinOptionInfo> tmpList = new ArrayList<CoinOptionInfo>();
 		for (CoinOptionInfo option: coinOptionList) {
 			CoinInfo coin = getCoinByType(option.getCoinType());
 			if (coin != null && coin.isRunningCoin()) {
 				tmpList.add(option);
 			}
 		}
 		return tmpList;
 	}
	
	public static void updateCoin(List<CoinOptionInfo> optionList) {
		for (CoinOptionInfo newOption: optionList) {
			for (CoinOptionInfo option: coinOptionList) {
				if (option.getCoinType().equals(newOption.getCoinType()) && option.getFromMarket().equals(newOption.getFromMarket())) {
					option.setBetAmount(newOption.getBetAmount());
					option.setBetProfit(newOption.getBetProfit());
					option.setActiveYn(newOption.isActiveYn());
				} 
			}
		}
		
//		coinOptionList = optionList;
//		coindao.updateCoinOptions(optionList);
	}
}
