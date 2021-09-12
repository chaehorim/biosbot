package com.chochae.afes.market.dao;

import java.util.List;

import com.chochae.afes.market.modal.MarketInfo;

public interface MarketDAO {
	
	public List<MarketInfo> selectMaketList();
	
	public MarketInfo selectMaketById(String marketId);
	
	public boolean insertMarket(MarketInfo market);
	
	public boolean updateMarket(MarketInfo market);
	
	public boolean deleteMarket(String marketId);
}
