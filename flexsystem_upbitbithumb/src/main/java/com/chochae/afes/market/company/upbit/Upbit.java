package com.chochae.afes.market.company.upbit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.market.company.HttpUtil;
import com.chochae.afes.market.company.MarketInterface;
import com.chochae.afes.market.company.dto.OrderBook;
import com.chochae.afes.market.company.dto.OrderBookPrice;
import com.chochae.afes.market.service.dto.TradeResultInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Upbit implements MarketInterface {
	private static final String marketName = "upbit";
	private static final String STATUS_SUCCESS = "0000"; 
	
	private static Logger logger = LogManager.getLogger(Upbit.class);
	
	private static final String URI_ROOT = "https://api.upbit.com";
	
	private static final String URI_ORDERBOOK = URI_ROOT + "/v1/orderbook?markets=";
	
	private String accessKey;
	private String secretKey;
	@Override
	public boolean validateMarket(String publicKey, String privateKey) {
		  this.accessKey = publicKey;
	      this.secretKey = privateKey;

	        String queryString = "query string »ý¼º";

	        try {
		        MessageDigest md = MessageDigest.getInstance("SHA-512");
		        md.update(queryString.getBytes("utf8"));
	
		        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
	
		        Algorithm algorithm;
				algorithm = Algorithm.HMAC256(secretKey);
				String jwtToken = JWT.create()
						.withClaim("access_key", accessKey)
						.withClaim("nonce", UUID.randomUUID().toString())
						.withClaim("query_hash", queryHash)
						.withClaim("query_hash_alg", "SHA512")
						.sign(algorithm);
				
				String authenticationToken = "Bearer " + jwtToken;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public double getBuyValue(String coinType, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSellValue(String coinType, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderBook getOrderBook(String coinType, String baseCoin) {
	OrderBook orderbook = null;
		
		String result = HttpUtil.httpGet(marketName, URI_ORDERBOOK + baseCoin + "-" + coinType);
		
		if (StringUtils.isEmpty(result)) {
			return orderbook;
		}
		
		JsonParser parser = new JsonParser();
		JsonArray root = parser.parse(result).getAsJsonArray();
		int size = root.size();
		
		orderbook = new OrderBook();
		for (int i = 0; i < size; i ++) {
			JsonObject data = (JsonObject)root.get(i);
			orderbook = json2OrderBookPrice(data.getAsJsonArray("orderbook_units"));
		}
		
		return orderbook;
	}

	private OrderBook json2OrderBookPrice(JsonArray ja) {
		OrderBook orderbook = new OrderBook();
		int size = ja.size();
		
		OrderBookPrice[] ask = new OrderBookPrice[size];
		OrderBookPrice[] bid = new OrderBookPrice[size];
		for (int i = 0; i < size; i++) {
			JsonObject jo = (JsonObject)ja.get(i);
			OrderBookPrice ap = new OrderBookPrice();

			ap.price = jo.get("ask_price").getAsDouble();
			ap.amount = jo.get("ask_size").getAsDouble();
			ask[i] = ap;
			
			OrderBookPrice bp = new OrderBookPrice();

			bp.price = jo.get("bid_price").getAsDouble();
			bp.amount = jo.get("bid_size").getAsDouble();
			bid[i] = bp;
		}
		orderbook.asks = ask;
		orderbook.bids = bid;
		return orderbook;
	}
	@Override
	public List<AssetDto> getAsset() {
		String accessKey = this.accessKey;
        String secretKey = this.secretKey;
        String serverUrl = URI_ROOT;

        List<AssetDto> assetList = null;
        Algorithm algorithm;
		try {
			algorithm = Algorithm.HMAC256(secretKey);
		
	        String jwtToken = JWT.create()
	                .withClaim("access_key", accessKey)
	                .withClaim("nonce", UUID.randomUUID().toString())
	                .sign(algorithm);
	
	        String authenticationToken = "Bearer " + jwtToken;
	
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            String result = EntityUtils.toString(entity, "UTF-8");
            JsonParser parser = new JsonParser();
            
            JsonArray arr = parser.parse(result).getAsJsonArray();
            assetList = new ArrayList<AssetDto>();
            for (int i = 0, size = arr.size(); i < size; i ++) {
            	JsonObject obj = arr.get(i).getAsJsonObject();
            	AssetDto asset = new AssetDto();
            	asset.setCoinType(obj.get("currency").getAsString());
            	asset.setFreeAmount(obj.get("balance").getAsDouble());
            	assetList.add(asset);
            }
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return assetList;
	}

	@Override
	public TradeResultInfo marketBuy(String coinType, double amount, double price, String baseCoin) {
		String accessKey = this.accessKey;
        String secretKey = this.secretKey;
        String serverUrl = URI_ROOT;

        TradeResultInfo msg = new TradeResultInfo(false ,"");
        
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("market", baseCoin + "-" + coinType);
        params.put("side", "bid");
//        params.put("volume", "" + amount);
        params.put("price", "" + price * amount);
        params.put("ord_type", "price");

        ArrayList<String> queryElements = new ArrayList<String>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md;
        String str = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		
	        md.update(queryString.getBytes("UTF-8"));
	
	        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
	
	        Algorithm algorithm = Algorithm.HMAC256(secretKey);
	        String jwtToken = JWT.create()
	                .withClaim("access_key", accessKey)
	                .withClaim("nonce", UUID.randomUUID().toString())
	                .withClaim("query_hash", queryHash)
	                .withClaim("query_hash_alg", "SHA512")
	                .sign(algorithm);
	
	        String authenticationToken = "Bearer " + jwtToken;

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            
            JsonParser parser = new JsonParser();
            
             str = EntityUtils.toString(entity, "UTF-8");
            JsonObject obj = parser.parse(str).getAsJsonObject();
            msg.setTradeId(obj.get("uuid").getAsString());
            msg.setSuccessYn(true);
            Thread.sleep(2000);
            msg.setFinalPrice(getFinalPrice(msg.getTradeId()));
            return msg;
        } catch (Exception e) {
        	System.out.println("upbit Sell trade Failed. [" + coinType +"],[" + amount + "], [" + price + "]" + str);
            e.printStackTrace();
            return msg;
		}
	}

	@Override
	public TradeResultInfo marketSell(String coinType, double amount, double price, String baseCoin) {
		String accessKey = this.accessKey;
        String secretKey = this.secretKey;
        String serverUrl = URI_ROOT;

        TradeResultInfo msg = new TradeResultInfo(false ,"");
        
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("market", baseCoin + "-" + coinType);
        params.put("side", "ask");
        params.put("volume", "" + amount);
//        params.put("price", "" + price);
        params.put("ord_type", "market");

        ArrayList<String> queryElements = new ArrayList<String>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md;
        String str= null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		
	        md.update(queryString.getBytes("UTF-8"));
	
	        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
	
	        Algorithm algorithm = Algorithm.HMAC256(secretKey);
	        String jwtToken = JWT.create()
	                .withClaim("access_key", accessKey)
	                .withClaim("nonce", UUID.randomUUID().toString())
	                .withClaim("query_hash", queryHash)
	                .withClaim("query_hash_alg", "SHA512")
	                .sign(algorithm);
	
	        String authenticationToken = "Bearer " + jwtToken;

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            
            JsonParser parser = new JsonParser();
            
            str = EntityUtils.toString(entity, "UTF-8");
            JsonObject obj = parser.parse(str).getAsJsonObject();
            msg.setTradeId(obj.get("uuid").getAsString());
            msg.setSuccessYn(true);
            Thread.sleep(2000);
            msg.setFinalPrice(getFinalPrice(msg.getTradeId()));
            return msg;
        } catch (Exception e) {
        	System.out.println("upbit Sell trade Failed. [" + coinType +"],[" + amount + "], [" + price + "]" + str);
            e.printStackTrace();
            return msg;
		}
	}

	public double getFinalPrice(String uuid) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String accessKey = this.accessKey;
        String secretKey = this.secretKey;
        String serverUrl = URI_ROOT;

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("uuid", uuid);

        ArrayList<String> queryElements = new ArrayList<String>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/order?" + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            JsonParser parser = new JsonParser();
            
            String str = EntityUtils.toString(entity, "UTF-8");
            JsonObject obj = parser.parse(str).getAsJsonObject();
            JsonArray arr = obj.get("trades").getAsJsonArray();
            
            double funds = 0;
            double amount = 0;
            for (int i = 0, size = arr.size(); i < size; i ++) {
            	JsonObject tmp = arr.get(i).getAsJsonObject();
            	funds += tmp.get("funds").getAsDouble();
            	amount += tmp.get("volume").getAsDouble();
            }
            if (amount == 0)
            	return 0;
            return funds/amount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
	}
	
	@Override
	public double getPrice(String coinType, double amount) {
		
		return 0;
	}

}
