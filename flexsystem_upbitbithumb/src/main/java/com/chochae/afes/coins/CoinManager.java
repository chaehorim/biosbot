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
 	public static List<CoinOptionInfo> getCoinOptionList () {
		for (CoinOptionInfo option : coinOptionList) {
//			System.out.println(option);
		}
		return coinOptionList;
	}
	
	public static void updateCoin(List<CoinOptionInfo> optionList) {
		CoinDAO coindao = DbLoader.getCoinDBConnection();
		coinOptionList = optionList;
//		coindao.updateCoinOptions(optionList);
	}
}
