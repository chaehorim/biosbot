package com.chochae.afes.market.company.bithumb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.market.company.HttpUtil;
import com.chochae.afes.market.company.MarketInterface;
import com.chochae.afes.market.company.dto.OrderBook;
import com.chochae.afes.market.company.dto.OrderBookPrice;
import com.chochae.afes.market.service.dto.TradeResultInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Bithumb implements MarketInterface{
	private static final String marketName = "bithumb";
	private static final String STATUS_SUCCESS = "0000"; 
	
	private static Logger logger = LogManager.getLogger(Bithumb.class);
	
	private static final String URI_ROOT = "https://api.bithumb.com";
	
	private static final String URI_PUBLIC = "/public";
	private static final String URI_INFO = "/info";
	private static final String URI_TRADE = "/trade";
	
	private static final String URI_DEPTH = URI_PUBLIC + "/orderbook/";
	private static final String URI_ASSET = URI_INFO + "/balance";
	private static final String URI_TICKER = URI_PUBLIC + "/ticker/";
	
	
	private static final String URI_MARKET_BUY = URI_TRADE + "/market_buy";
	private static final String URI_MARKET_SELL = URI_TRADE + "/market_sell";
	private static final String URI_ORDER_DETAIL = URI_INFO + "/order_detail";
	
	private String publicKey;
	private String privateKey;
	
	@Override
	public boolean validateMarket(String publicKey, String privateKey) {
		
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		return false;
	}

	@Override
	public double getBuyValue(String coinType, double amount) {
	
		return 0;
	}

	@Override
	public double getSellValue(String coinType, double amount) {
		return 0;
	}
	
	@Override
	public OrderBook getOrderBook(String coin, String baseCoin) {
		OrderBook orderbook = null;
		
		String result = HttpUtil.httpGet(marketName, URI_ROOT + URI_DEPTH + coin + "_" + baseCoin);
		
		if (StringUtils.isEmpty(result)) {
			return orderbook;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		String status = root.get("status").getAsString();
		
		if (!STATUS_SUCCESS.equals(status)) {
			logger.error(coin + " -" + baseCoin + " : " + result);
			return orderbook;
		}
		
		JsonObject data = (JsonObject)root.get("data");
		orderbook = new OrderBook();
		orderbook.bids = json2OrderBookPrice(data.getAsJsonArray("bids"));
		orderbook.asks = json2OrderBookPrice(data.getAsJsonArray("asks"));
		return orderbook;
	}

	@Override
	public List<AssetDto> getAsset() {
		final String PREFIX_AVIALABLE = "available_";
		final String PREFIX_TOTAL = "total_";
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("endpoint", URI_ASSET);
		param.put("apiKey", publicKey);
		param.put("secretKey", privateKey);
		param.put("currency", "ALL");
		
		String result = HttpUtil.httpPost(marketName, URI_ROOT + URI_ASSET, getHttpHeader(URI_ASSET, param), param, ContentType.APPLICATION_FORM_URLENCODED);
		
		if (StringUtils.isEmpty(result)) {
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		String status = root.get("status").getAsString();
		
		if (!STATUS_SUCCESS.equals(status)) {
			logger.error(result);
			return null;
		}
		List<AssetDto> assetList = new ArrayList<AssetDto>();
		JsonObject data = (JsonObject)root.get("data");

		for (String key : data.keySet()) {
			AssetDto asset = new AssetDto();
			if (key.startsWith(PREFIX_AVIALABLE)) {
				String coinName = key.replaceFirst(PREFIX_AVIALABLE, "");
				asset.setCoinType(coinName);
				asset.setFreeAmount(data.get(key).getAsDouble());
			}
			if (key.startsWith(PREFIX_TOTAL)) {
				asset.setTotalAmount(data.get(key).getAsDouble());
			}
			if (asset.getCoinType() == null || "null".equals(asset.getCoinType())) {
				continue;
			}
			asset.setCoinType(asset.getCoinType().toUpperCase());
			assetList.add(asset);
		}
		return assetList;
	}

	@Override
	public TradeResultInfo marketBuy(String coinType, double amount, double price, String baseCoin) {
		TradeResultInfo msg = new TradeResultInfo(false, "");
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("endpoint", URI_MARKET_BUY);
		param.put("apiKey", this.publicKey);
		param.put("secretKey", this.privateKey);
		param.put("units", "" + amount);
		param.put("order_currency", coinType );
		param.put("payment_currency", baseCoin);
		
		String result = HttpUtil.httpPost("bithumb", URI_ROOT + URI_MARKET_BUY, getHttpHeader(URI_MARKET_BUY, param), param, ContentType.APPLICATION_FORM_URLENCODED);
		
		if (StringUtils.isEmpty(result)) {
			System.out.println("Bithumb Buy trade Failed. [" + coinType +"],[" + amount + "], [" + price + "]");
			return msg;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		String status = root.get("status").getAsString();
		
		if (!STATUS_SUCCESS.equals(status)) {
			logger.error(result);
			System.out.println("Bithumb Buy trade Failed. [" + coinType +"],[" + amount + "], [" + price + "]");
			return msg;
		}
		
		msg.setSuccessYn(true);;
		msg.setTradeId(root.get("order_id").getAsString());
		try {
			Thread.sleep(2000);
			msg.setFinalPrice(getOrderPrice(msg.getTradeId(), coinType));
		} catch (InterruptedException e) {
			e.printStackTrace();
			return msg;
		}
		return msg;
	}

	@Override
	public TradeResultInfo marketSell(String coinType, double amount, double price, String baseCoin) {
		TradeResultInfo msg = new TradeResultInfo(false, "");
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("endpoint", URI_MARKET_SELL);
		param.put("apiKey", this.publicKey);
		param.put("secretKey", this.privateKey);
		param.put("units", "" + amount);
		param.put("order_currency", coinType );
		param.put("payment_currency", baseCoin);
		
		String result = HttpUtil.httpPost("bithumb", URI_ROOT + URI_MARKET_SELL, getHttpHeader(URI_MARKET_SELL, param), param, ContentType.APPLICATION_FORM_URLENCODED);
		
		if (StringUtils.isEmpty(result)) {
			System.out.println("Bithumb Sell trade Failed. [" + coinType +"],[" + amount + "], [" + price + "]");
			return msg;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		String status = root.get("status").getAsString();
		
		if (!STATUS_SUCCESS.equals(status)) {
			logger.error(result);
			System.out.println("Bithumb Sell trade Failed. [" + coinType +"],[" + amount + "], [" + price + "]");
			return msg;
		}
		
		msg.setSuccessYn(true);;
		msg.setTradeId(root.get("order_id").getAsString());
		try {
			Thread.sleep(2000);
			msg.setFinalPrice(getOrderPrice(msg.getTradeId(), coinType));
		} catch (InterruptedException e) {
			e.printStackTrace();
			return msg;
		}
		return msg;
	}
	
	public double getOrderPrice(String uuid, String coinType) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("endpoint", URI_ORDER_DETAIL);
		param.put("apiKey", this.publicKey);
		param.put("secretKey", this.privateKey);
		param.put("order_id", "" + uuid);
		param.put("order_currency", coinType );
		
		String result = HttpUtil.httpPost("bithumb", URI_ROOT + URI_ORDER_DETAIL, getHttpHeader(URI_ORDER_DETAIL, param), param, ContentType.APPLICATION_FORM_URLENCODED);
		
		System.out.println(" XXX " + result);
		if (StringUtils.isEmpty(result)) {
			return 0;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		String status = root.get("status").getAsString();
		
		if (!STATUS_SUCCESS.equals(status)) {
			logger.error(result);
			return 0;
		}
		
		JsonObject obj = root.get("data").getAsJsonObject();
		JsonArray arr = (JsonArray)obj.get("contract");
		
		double amount = 0;
		double funds = 0;
		for (int i = 0, size = arr.size(); i < size; i ++) {
			JsonObject jo = arr.get(i).getAsJsonObject();
			double val = jo.get("units").getAsDouble();
			amount += val;
			funds += val * jo.get("price").getAsDouble();
		}
		if (amount == 0) {
			return 0;
		}
		return funds/amount;
		
	}
	
	private HashMap<String, String> getHttpHeader(String endpoint, HashMap<String, String> param) {
		HashMap<String, String> header = new HashMap<String, String>();
		
		String nonce = getNonce();
		
		String strParam = HttpUtil.mapToRequestString(param);
		strParam = encodeURIString(strParam);

		header.put("api-client-type", "2");
		header.put("Api-Key", publicKey);
		header.put("Api-Sign", getSignature(endpoint + ";" + strParam + ";" + nonce));
		header.put("Api-Nonce", nonce);

		return header;
	}
	
	private String getSignature(String s) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
	 
			Mac mac = Mac.getInstance("HmacSHA512");
			mac.init(keySpec);
	
			byte[] macData = mac.doFinal(s.getBytes());
	
			String result = new String(Base64.encodeBase64(new Hex().encode(macData)));

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			
			return "";
		}
	}
	private String getNonce() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	private static String encodeURIString(String s) {
		String result = "";
		try {
			result = URLEncoder.encode(s, "UTF-8")
							.replaceAll("\\+", "%20")
							.replaceAll("\\%21", "!")
							.replaceAll("\\%27", "'")
							.replaceAll("\\%28", "(")
							.replaceAll("\\%29", ")")
							.replaceAll("\\%26", "&")
							.replaceAll("\\%3D", "=")
							.replaceAll("\\%7E", "~");
		} catch (UnsupportedEncodingException e) {
			result = s;
		}
		return result;
	}

	private OrderBookPrice[] json2OrderBookPrice(JsonArray ja) {
		int size = ja.size();
		
		OrderBookPrice[] dpa = new OrderBookPrice[size];
		
		for (int i = 0; i < size; i++) {
			JsonObject jo = (JsonObject)ja.get(i);
			OrderBookPrice dp = new OrderBookPrice();

			dp.price = jo.get("price").getAsDouble();
			dp.amount = jo.get("quantity").getAsDouble();
			dpa[i] = dp;
		}
		
		return dpa;
	}

	@Override
	public double getPrice(String coinType, double amount) {
		String result = HttpUtil.httpGet(marketName, URI_ROOT + URI_TICKER + coinType + "_KRW");
		
//		System.out.println(result);
		return 0;
	}

}
