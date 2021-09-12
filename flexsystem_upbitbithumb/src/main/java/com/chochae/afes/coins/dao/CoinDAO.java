package com.chochae.afes.coins.dao;

import java.util.List;

import com.chochae.afes.coins.model.CoinInfo;
import com.chochae.afes.coins.model.CoinOptionInfo;

public interface CoinDAO {
	public List<CoinInfo>    selectCoin();
	
	public List<CoinOptionInfo> selectCoinOption();
	
	public boolean updateCoinOptions(List<CoinOptionInfo> optionList);
}
