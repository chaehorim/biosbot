package com.chochae.afes.market.company.upbit;

import java.util.List;

import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.market.company.dto.OrderBook;

public class UpbitTest {
    public static void main(String[] args) {
    	Upbit up = new Upbit();
    	OrderBook book = up.getOrderBook("QTUM","KRW");
    	
    	up.validateMarket("HmtOMrIoW77DoelOBiBsl5PL8ZYljqOjDdIZeouF", "dI98oRHH7RLuwMnvTcHV32ykH0OvUJ7PW2MxdR1d");
    	
//    	TradeResultInfo msg = up.marketSell("XRP", 200, 0,"KRW");
//    	TradeResultInfo msg = up.marketBuy("XRP", 10.5, 1250);
//    	System.out.println(msg);
    	List<AssetDto> assetList = up.getAsset();
    	for (AssetDto asset : assetList) {
    		System.out.println(asset);
    	}
    }
}
