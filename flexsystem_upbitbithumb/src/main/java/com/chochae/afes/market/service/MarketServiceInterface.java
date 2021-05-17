/**
 * 
 */
package com.chochae.afes.market.service;

import java.util.List;

import com.chochae.afes.market.modal.OrderRemainStatus;
import com.chochae.afes.market.service.dto.TradeResultInfo;
import com.chochae.afes.resource.dto.UserAssetInfo;

/**
 * @author Isco
 *
 */
public interface MarketServiceInterface {
	
	public void init(String publicKey, String privateKey);              // using
	
	public boolean validateMarket(String publicKey, String privateKey); // using
	
	public float getBuyValue(float buyCnt);                             // using  
	
	public float getSellValue(float sellCnt);                           // using
	
	public float[] getBuySellVal(float buyCnt, float sellCnt);          // using
	
	public float getRemainAmount();                                     // using
	
	public float getRemainBitCoin();                                    // using  
	
	public UserAssetInfo getRemainAsset();                             // using
	
	public void changeCoinType(String coinType);                       // using
	
	
	public TradeResultInfo buyValue(float amount, float currency);      // using
	
	public TradeResultInfo sellValue(float bitCnt, float currency);     // using 
	
	public float getCommision(int type);
	
	public List<OrderRemainStatus> getOrders();                         
	
	public boolean cancelOrder(String orderId);
	
	public boolean transferBitCoin(String address, float bitCnt, String withdrawPassword);     // not using
}
