package com.chochae.afes.market.service.bithumb_bak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chochae.afes.common.utils.CommonUtil;
import com.chochae.afes.common.utils.NumberUtil;
import com.chochae.afes.market.modal.OrderRemainStatus;
import com.chochae.afes.market.service.MarketServiceInterface;
import com.chochae.afes.market.service.dto.TradeResultInfo;
import com.chochae.afes.resource.dto.UserAssetInfo;

public class BithumbMarket implements MarketServiceInterface{
	private Api_Client api;
	private String coinType;
	private String coinSign;
	public BithumbMarket(String coinType) {
		super();
		this.coinType = coinType.toUpperCase();
		this.coinSign = coinType.toLowerCase();
	}

	@Override
	public void init(String publicKey, String privateKey) {
//		api = new Api_Client("8df0693030e0465de19b7b14dc2d199e",
//				"85fceeeef8728b9047dd7a0e68f97e06");
		api = new Api_Client(publicKey,privateKey);
	}
	
	@Override
	public boolean validateMarket(String publicKey, String privateKey) {
		api = new Api_Client(publicKey,privateKey);
		HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", publicKey);
		rgParams.put("secretKey", privateKey);
		rgParams.put("currency", coinType);
    	
		try {
		    String result = api.callApi("/info/balance", rgParams);
		    System.out.println(result);
		    if (result != null && result.startsWith("error")) {
		    	return false;
		    }
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			String status = (String)tmp.get("status");
			if (!"0000".equals(status)) {
				return false;
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return true;
	}
	
	@Override
	public float getBuyValue(float bidCnt) {
		HashMap<String, String> rgParams = new HashMap<String, String>();
		float price = 0;
    	
		try {
		    String result = api.callApi("/public/orderbook/" + coinType , rgParams);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			
			
			JSONArray  bidArr = (JSONArray )tradeVal.get("asks");
			float buyCnt = 0;
			for(int i=0;i<bidArr.size();i++){
				JSONObject bidVal = (JSONObject) bidArr.get(i);
				System.out.println(bidVal);
				float cnt  = Float.parseFloat((String) bidVal.get("quantity"));
				buyCnt = NumberUtil.addFloat(buyCnt, cnt);
				if (bidCnt < buyCnt) {
					price = Float.parseFloat((String) bidVal.get("price"));
					break;
				}
			}

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return price;
	}

	@Override
	public float getSellValue(float bidCnt) {
		HashMap<String, String> rgParams = new HashMap<String, String>();
    	
		float price = 0;
		try {
		    String result = api.callApi("/public/orderbook/" + coinType , rgParams);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			
			
			JSONArray  bidArr = (JSONArray )tradeVal.get("bids");
			float buyCnt = 0;
			for(int i=0;i<bidArr.size();i++){
				JSONObject bidVal = (JSONObject) bidArr.get(i);
				System.out.println(bidVal);
				float cnt  = Float.parseFloat((String) bidVal.get("quantity"));
				buyCnt = NumberUtil.addFloat(buyCnt, cnt);
				if (bidCnt < buyCnt) {
					price = Float.parseFloat((String) bidVal.get("price"));
					break;
				}
			}

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return price;
	}

	@Override
	public List<OrderRemainStatus> getOrders() {
	 	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("currency", coinType);
		List<OrderRemainStatus> remainList = new ArrayList<OrderRemainStatus>();
		try {
		    String result = api.callApi("/info/orders", rgParams);
		    System.out.println(result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			if ("5600".equals(tmp.get("status"))) {
				return remainList;
			}
			JSONArray orderArr = (JSONArray)tmp.get("data");
			for (int i = 0, size = orderArr.size(); i < size; i ++) {
				JSONObject orderVal = (JSONObject)orderArr.get(i);
				String orderId = (String) orderVal.get("order_id");
				String type = (String) orderVal.get("type");
				Float bitCoin = NumberUtil.getFloatFromJSON( orderVal.get("units_remaining"));
				OrderRemainStatus remain = new OrderRemainStatus();
				remain.setOrderId(orderId);
				if ("bid".equals(type)) {
					Float tradeRate = NumberUtil.getFloatFromJSON( orderVal.get("price"));
					remain.setAmount(bitCoin * tradeRate);
					remainList.add(remain);
				} else if ("ask".equals(type)) {
					remain.setBitCoinCnt(bitCoin);
					remainList.add(remain);
				}
			}
			//amount = NumberUtil.getFloatFromJSON( tradeVal.get("available_krw"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return remainList;
	}

	@Override
	public float getRemainAmount() {
		float amount = 0;
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("currency", coinType);
    	
		try {
		    String result = api.callApi("/info/balance", rgParams);
		    System.out.println(result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			amount = NumberUtil.getFloatFromJSON( tradeVal.get("available_krw"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return amount;
	}

	@Override
	public float getRemainBitCoin() {
		float bcCnt = 0;
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("currency", coinType);
    	
		try {
		    String result = api.callApi("/info/balance", rgParams);
		    System.out.println(result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			bcCnt = NumberUtil.getFloatFromJSON( tradeVal.get("available_" + coinSign));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return bcCnt;
	}
	@Override
	public TradeResultInfo buyValue(float amount, float currency) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("units", Float.toString(amount / currency));
		rgParams.put("currency", coinType);
    	
		try {
		    String result = api.callApi("/trade/market_buy", rgParams);
		    System.out.println(result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			String id = tmp.get("order_id").toString();
			boolean successYn = tmp.get("status").equals("0000") ? true : false;
			
			return new TradeResultInfo(successYn, id);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
	@Override
	public TradeResultInfo sellValue(float bitCnt, float currency) {
		float val = (float) (Math.floor(bitCnt * 10000000) / 10000000);
	   	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("units", Float.toString(val));
		rgParams.put("currency", coinType);
		
		try {
		    String result = api.callApi("/trade/market_sell", rgParams);
		    System.out.println(result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			String id = tmp.get("order_id").toString();
			boolean successYn = tmp.get("status").equals("0000") ? true : false;
			
			return new TradeResultInfo(successYn, id);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean cancelOrder(String orderId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean transferBitCoin(String address, float bitCnt, String withdrawPassword) {
//       	HashMap<String, String> rgParams = new HashMap<String, String>();
//    	rgParams.put("apiKey", api.getPublicKey());
//		rgParams.put("secretKey", api.getPrivateKey());
//		rgParams.put("units", Float.toString(bitCnt));
//		rgParams.put("address", address);
//		rgParams.put("currency", coinType);
//    	
//		try {
//		    String result = api.callApi("/trade/btc_withdrawal", rgParams);
//		    System.out.println("BITHUMB : TRANSFER " + result);
//		    JSONParser jsonParser = new JSONParser();
//			JSONObject tmp = (JSONObject)jsonParser.parse(result);
//			boolean successYn = tmp.get("status").equals("0000") ? true : false;
//			return successYn;
//		} catch (Exception e) {
//		    e.printStackTrace();
//		    return false;
//		}
		return true;
	}

	@Override
	public float getCommision(int type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserAssetInfo getRemainAsset() {
		UserAssetInfo userAsset = new UserAssetInfo();
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("currency", coinType);
    	
		try {
		    String result = api.callApi("/info/balance", rgParams);
		    System.out.println(result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			
			float bcCnt = NumberUtil.getFloatFromJSON( tradeVal.get("available_" + coinSign));
			float amount = NumberUtil.getFloatFromJSON( tradeVal.get("available_krw"));
			userAsset.setRemainAmount(amount);
			userAsset.setRemainBcCnt(bcCnt);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return userAsset;
	}

	@Override
	public void changeCoinType(String coinType) {
		this.coinType = coinType.toUpperCase();
		this.coinSign = coinType.toLowerCase();
	}

	@Override
	public float[] getBuySellVal(float buyCnt, float sellCnt) {
		float[] vals = {0, 0};
       	HashMap<String, String> rgParams = new HashMap<String, String>();
		try {
		    String result = api.callApi("/public/orderbook/" + coinType , rgParams);
//		    System.out.println("SELL + BUY VALL" + result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			
			JSONArray  buyBidArr = (JSONArray )tradeVal.get("asks");
			JSONArray  sellBidArr = (JSONArray )tradeVal.get("bids");
			
			vals[0] = CommonUtil.getJsonBidVal(buyBidArr, "quantity", "price", buyCnt);
			vals[1] = CommonUtil.getJsonBidVal(sellBidArr, "quantity", "price", sellCnt);

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return vals;
	}

}
