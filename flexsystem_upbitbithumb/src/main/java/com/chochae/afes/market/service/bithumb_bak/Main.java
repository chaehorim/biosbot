package com.chochae.afes.market.service.bithumb_bak;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chochae.afes.common.utils.NumberUtil;
import com.chochae.afes.market.modal.OrderRemainStatus;

public class Main {
	private static String currency = "ETC";
    public static void main(String args[]) {
    	// banba
		Api_Client api = new Api_Client("6fd34df318b93facff22b74250607c02",
			"e3e7cb5f9d1060281284fd84befe8241");
    	
    	// xiaoli account
//    	Api_Client api = new Api_Client("9bf9c1e52e7489af7105fc9eaf3dcca4",
//    			"62f3dfd93f3bb8d5a88f9cc795c3c31c");
		
    	// leechul account
//    	Api_Client api = new Api_Client("7cfab03d463e6b2582352b3ee3bbe83f",
//    			"5a1ba42e2c78804415eca5f5005e3fa5");
//	
		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", currency);
		rgParams.put("apiKey", "9bf9c1e52e7489af7105fc9eaf3dcca4");
		rgParams.put("secretKey", "62f3dfd93f3bb8d5a88f9cc795c3c31c");
//		rgParams.put("payment_currency", "KRW");
//		rgParams.put("payment_currency", "KRW");
	
		try {
		    String result = api.callApi("/info/balance", rgParams);
		    System.out.println(result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
//		getOrderbook(api);
		
//		getAccountInfo(api);
		
//		getBalance(api);
		//buy(api);
//		buyMarket(api);
//		sell(api);
	//     sellMarket(api);
	//	getOrders(api);
		//withdraw(api, "0.03");
		
		//getList(api);
	//	transferMoney(api);
    }
    
    public static void getOrderbook(Api_Client api) {
    	HashMap<String, String> rgParams = new HashMap<String, String>();
	
		try {
		    String result = api.callApi("/public/ticker", rgParams);
		   // System.out.println("SELL + BUY VALL" + result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			System.out.println(tradeVal.get("sell_price").getClass().getName());
			float buyVal = Float.parseFloat((String) tradeVal.get("sell_price"));
//			System.out.println(buyVal);
			System.out.println("SELLVAL :" + tradeVal.get("sell_price"));
			System.out.println("SELLVAL :" + tradeVal.get("buy_price"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    public static void getAccountInfo(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
    	
    	
    		try {
    		    String result = api.callApi("/info/account", rgParams);
    		    System.out.println(result);
    		} catch (Exception e) {
    		    e.printStackTrace();
    		}
    }
    
    public static void getBalance(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("currency", "BTC");
    	
		try {
		    String result = api.callApi("/info/balance", rgParams);
		    System.out.println(result);
		    if (result != null && result.startsWith("error")) {
		    	System.out.println("FAILURE" + result);
		    }
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			String status = (String)tmp.get("status");
			if ("0000".equals(status)) {
				System.out.println("SUCCESSFUL;");
			} else {
				System.out.println("FAILURE");
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    public static void buy(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("Order_currency", "ETH");
		rgParams.put("Payment_currency", "KRW");
		rgParams.put("units", Float.toString(2290));
		rgParams.put("price", Integer.toString(22900));
		rgParams.put("type", "ask");
		rgParams.put("misu", "N");
    	
		try {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		    String result = api.callApi("/trade/place", rgParams);
		    System.out.println("[BUY]" + result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    public static void buyMarket(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("Order_currency", currency);
//		rgParams.put("Payment_currency", "KRW");
		rgParams.put("units", Float.toString(0.01f));
//		rgParams.put("price", Integer.toString(733000));
//		rgParams.put("type", "bid");
//		rgParams.put("misu", "N");
    	
		try {
		    String result = api.callApi("/trade/market_buy", rgParams);
		    System.out.println("[BUYMARKET]" + result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    public static void sell(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("Order_currency", "BTC");
		rgParams.put("Payment_currency", "KRW");
		rgParams.put("units", Float.toString(0.001f));
		rgParams.put("price", Integer.toString(932000));
		rgParams.put("type", "ask");
		rgParams.put("misu", "N");
    	
		try {
		    String result = api.callApi("/trade/place", rgParams);
		    System.out.println("[SELL]" + result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    public static void sellMarket(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("units", Float.toString(0.001f));
    	
		try {
		    String result = api.callApi("/trade/market_sell", rgParams);
		    System.out.println("[SELLMARKET]" + result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    public static void getOrders(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("after", Long.toString(System.currentTimeMillis() - 100000));
		//rgParams.put("type", "bid");
//		rgParams.put("Order_currency", "BTC");
//		rgParams.put("Payment_currency", "KRW");
//		rgParams.put("units", Float.toString(0.005f));
//		rgParams.put("price", Integer.toString(732000));
//		rgParams.put("type", "ask");
//		rgParams.put("misu", "N");
    	
		try {
			
		    String result = api.callApi("/trade/orders", rgParams);
		    System.out.println(result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			if (!"0000".equals(tmp.get("status"))) {
				return ;
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
//					remainList.add(remain);
				} else if ("ask".equals(type)) {
					remain.setBitCoinCnt(bitCoin);
//					remainList.add(remain);
				}
				System.out.println(remain);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    public static void withdraw(Api_Client api, String amount) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("units", amount);
		rgParams.put("address", "12vS5pGtTJAcSiRgRjRT2fSx5kRsPoXDwB");
    	
		try {
		    String result = api.callApi("/trade/btc_withdrawal", rgParams);
		    System.out.println(result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    //user_transactions
    public static void getList(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("offset", "10");
		rgParams.put("count", "50");
		rgParams.put("searchGb", "0");
    	
		try {
		    String result = api.callApi("/info/user_transactions", rgParams);
		    System.out.println(result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    //transfer money
    public static void transferMoney(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("bank", "020");
		rgParams.put("account", "55");
		rgParams.put("price", "10000");
    	
		try {
		    String result = api.callApi("/trade/krw_withdrawal", rgParams);
		    System.out.println(result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
}

