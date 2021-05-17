package com.chochae.afes.market.service.bithumb_bak;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chochae.afes.common.utils.CommonUtil;
import com.chochae.afes.common.utils.NumberUtil;
import com.chochae.afes.market.modal.OrderRemainStatus;

public class Main_eth {
	private static String currency = "eth";
    public static void main(String args[]) {
    	// xiaoli
//		Api_Client api = new Api_Client("64c9383de21b59397b08038c44931168",
//			"dfdb205374c55826da65ab797cc5508f");
    	// banba
		Api_Client api = new Api_Client("4664fea9fd2c359dbeb87a2c8c8ef078","24a3b19de706c3b4a005044de4334fbf");	

    	// banba
//		Api_Client api = new Api_Client("1feb7947b6f20c834aa776bd74f0149b",
//			"37612c75e6ba446eb9468fef6de119cf");	
//		// rone
//		Api_Client api = new Api_Client("2ee2a1c34aa74c22d280b385a2ed85f3",
//			"f465e49fd393ca8450bc8dcf01ef7e22");
		/*  ETHREUM  */
		getBalance(api);
		
//		if (true) {
//			return;
//		}
		float[] vals = getBuySellVal(api, 10000f, 1);
//		float buyVal = getBuyVal(api, 0.1f);
		System.out.println(" ZZZZZZZZZ " + " [" +vals[0] + "], [" + vals[1] + "]");
//		getsellVal(api, 1f);
		buyMarket(api);
//		sellMarket(api);
		Long a = 10l;
    }
    
    public static void getOrderbook(Api_Client api) {
    	HashMap<String, String> rgParams = new HashMap<String, String>();
	
		try {
		    String result = api.callApi("/public/ticker/" + currency , rgParams);
		    System.out.println("SELL + BUY VALL" + result);
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
    
    public static float getBuyVal(Api_Client api, float bidCnt) {
    	HashMap<String, String> rgParams = new HashMap<String, String>();
    	float val = 0;
		try {
		    String result = api.callApi("/public/orderbook/" + currency , rgParams);
		    System.out.println("SELL + BUY VALL" + result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			
			
			JSONArray  bidArr = (JSONArray )tradeVal.get("asks");
			float buyCnt = 0;
			float price = 0;
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
			val = price;
			System.out.println("buying price : [" + val  + "]");

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return val;
    }
    
    public static void getsellVal(Api_Client api, float bidCnt) {
    	HashMap<String, String> rgParams = new HashMap<String, String>();
    	
		try {
		    String result = api.callApi("/public/orderbook/" + currency , rgParams);
		    System.out.println("SELL + BUY VALL" + result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			
			
			JSONArray  bidArr = (JSONArray )tradeVal.get("bids");
			float sellCnt = 0;
			float price = 0;
			for(int i=0;i<bidArr.size();i++){
				JSONObject bidVal = (JSONObject) bidArr.get(i);
				System.out.println(bidVal);
				float cnt  = Float.parseFloat((String) bidVal.get("quantity"));
				sellCnt = NumberUtil.addFloat(sellCnt, cnt);
				if (bidCnt < sellCnt) {
					price = Float.parseFloat((String) bidVal.get("price"));
					break;
				}
			}
			System.out.println("selling price : [" + price + "]");
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    private static float[] getBuySellVal(Api_Client api, float buyBidCnt, float sellBidCnt) {
    	float[] vals = {0, 0};
       	HashMap<String, String> rgParams = new HashMap<String, String>();
		try {
		    String result = api.callApi("/public/orderbook/" + currency , rgParams);
		    System.out.println("SELL + BUY VALL" + result);
		    JSONParser jsonParser = new JSONParser();
			JSONObject tmp = (JSONObject)jsonParser.parse(result);
			JSONObject tradeVal = (JSONObject)tmp.get("data");
			
			JSONArray  buyBidArr = (JSONArray )tradeVal.get("asks");
			JSONArray  sellBidArr = (JSONArray )tradeVal.get("bids");
			
			vals[0] = CommonUtil.getJsonBidVal(buyBidArr, "quantity", "price", buyBidCnt);
			vals[1] = CommonUtil.getJsonBidVal(sellBidArr, "quantity", "price", sellBidCnt);

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return vals;
    	
    }
    
    public static void getBalance(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("currency", "etc");
    	
		try {
		    String result = api.callApi("/info/balance/", rgParams);
		    System.out.println("[BALANCE]" +result);
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
    
    public static void buyMarket(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("currency", currency);
		rgParams.put("units", Float.toString(0.01f));
    	
		try {
		    String result = api.callApi("/trade/market_buy", rgParams);
		    System.out.println("[BUYMARKET]" + result);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    public static void sellMarket(Api_Client api) {
       	HashMap<String, String> rgParams = new HashMap<String, String>();
    	rgParams.put("apiKey", api.getPublicKey());
		rgParams.put("secretKey", api.getPrivateKey());
		rgParams.put("units", Float.toString(0.1f));
		rgParams.put("currency", currency);
    	
		try {
		    String result = api.callApi("/trade/market_sell", rgParams);
		    System.out.println("[SELLMARKET]" + result);
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

