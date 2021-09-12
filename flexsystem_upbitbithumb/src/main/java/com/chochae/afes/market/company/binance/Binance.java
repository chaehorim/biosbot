package com.chochae.afes.market.company.binance;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.market.company.HttpUtil;
import com.chochae.afes.market.company.MarketInterface;
import com.chochae.afes.market.company.dto.OrderBook;
import com.chochae.afes.market.company.dto.OrderBookPrice;
import com.chochae.afes.market.service.dto.TradeResultInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Binance implements MarketInterface {

	private static final String URI_ROOT = "https://api.binance.com/api";
	private static final String URI_DEPTH = URI_ROOT + "/v1/depth?symbol={0}&limit=100"; // 0 : currency pair code (LTCBTC)
	private static final String URI_ACCOUNT = URI_ROOT + "/v3/account";
	private static final String URI_SERVER_TIME = URI_ROOT + "/v1/time";
	private static final String URI_ORDER = URI_ROOT + "/v3/order";
	
	private static final String PARAM_DEFAULT = "recvWindow={0}&timestamp={1}"; // 0 : receive delay	 1 : nonce
	private static final String PARAM_ORDER = "symbol={0}&orderId={1}&newOrderRespType=FULL&timestamp={2}"; // 0 : currency pair code (LTCBTC)	 1 : order id	2 :  : nonce

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
	public double getPrice(String coinType, double amount) {
		return 0;
	}

	@Override
	public OrderBook getOrderBook(String coinType, String baseCoin) {
		OrderBook orderbook = new OrderBook();
		String result = HttpUtil.httpGet("binance", MessageFormat.format(URI_DEPTH, translate(coinType) + translate(baseCoin)));
		
		if (StringUtils.isEmpty(result)) {
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		orderbook.bids = json2OrderBookPrice(root.get("bids").getAsJsonArray());
		orderbook.asks = json2OrderBookPrice(root.get("asks").getAsJsonArray());
		return orderbook;
	}

	@Override
	public List<AssetDto> getAsset() {
		List<AssetDto>  assetList = new ArrayList<AssetDto>();
		String param = MessageFormat.format(PARAM_DEFAULT, "5000", getNonce());
		
		String signature = getSignature(param);
		String result = HttpUtil.httpGet("binance", URI_ACCOUNT + "?" + param + "&signature=" + signature, getHttpHeader());

		if (StringUtils.isEmpty(result)) {
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();

		JsonArray balances = (JsonArray)root.get("balances");
		
		for (int i = 0; i < balances.size(); i++) {
			AssetDto asset = new AssetDto();
			
			JsonObject jo = (JsonObject)balances.get(i);
			
			asset.setCoinType(jo.get("asset").getAsString());
			asset.setFreeAmount(jo.get("free").getAsDouble());
			asset.setTotalAmount(jo.get("locked").getAsDouble() + asset.getFreeAmount());
			assetList.add(asset);
		}
		
		return assetList;
	}

	@Override
	public TradeResultInfo marketBuy(String coinType, double amount, double price, String baseCoin) throws Exception {
		return null;
	}

	@Override
	public TradeResultInfo marketSell(String coinType, double amount, double price, String baseCoin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Long getServerTime() {
		
		String result = HttpUtil.httpGet("binance", URI_SERVER_TIME);
		
		if (StringUtils.isEmpty(result)) {
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		return root.get("serverTime").getAsLong();
	}
	
	public String translate(String currencyCode) {
		String localCode = "";
		
		if (!StringUtils.isEmpty(currencyCode)) {
			
			if ("BCH".equals(currencyCode.toLowerCase())) {
				localCode = "BCHABC";
			} else if ("BSV".equals(currencyCode.toLowerCase())) {
				localCode = "BCHSV";
			} else {
				localCode = currencyCode.toUpperCase();
			}
		}
		return localCode;
	}
	private OrderBookPrice[] json2OrderBookPrice(JsonArray ja) {
		int size = ja.size();
		
		OrderBookPrice[] dpa = new OrderBookPrice[size];
		
		for (int i = 0; i < size; i++) {
			JsonArray jo = ja.get(i).getAsJsonArray();
			OrderBookPrice dp = new OrderBookPrice();

			dp.price = jo.get(0).getAsDouble();
			dp.amount = jo.get(1).getAsDouble();
			dpa[i] = dp;
		}
		
		return dpa;
	}
	
	private String getSignature(String s) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
	 
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(keySpec);
	
			byte[] macData = mac.doFinal(s.getBytes("UTF-8"));
			String result = Hex.encodeHexString(macData);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			
			return "";
		}
	}
	private String getNonce() {
		return String.valueOf(new Date().getTime());
	}
	
	private HashMap<String, String> getHttpHeader() {
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("X-MBX-APIKEY", publicKey);

		return header;
	}
}
