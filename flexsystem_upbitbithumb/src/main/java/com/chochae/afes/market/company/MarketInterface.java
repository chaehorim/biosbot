package com.chochae.afes.market.company;

import java.util.List;

import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.market.company.dto.OrderBook;
import com.chochae.afes.market.service.dto.TradeResultInfo;

public interface MarketInterface {
	public boolean validateMarket(String publicKey, String privateKey); 
	
	public double getBuyValue(String coinType, double amount);
	
	public double getSellValue(String coinType, double amount);
	
	public double getPrice(String coinType, String baseCoin);
	
	public OrderBook getOrderBook(String coinType, String baseCoin);
	
	public List<AssetDto>  getAsset();
	
	public TradeResultInfo marketBuy(String coinType, double amount, double price, String baseCoin) throws Exception;      // using
	
	public TradeResultInfo marketSell(String coinType, double amount, double price, String baseCoin) throws Exception;     // using 
}
