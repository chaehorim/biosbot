package engine.market.binance;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import engine.dto.MarketPriceDTO;
import engine.dto.OrderDTO;
import engine.dto.OrderStatus;
import engine.market.HttpUtil;
import engine.market.MarketInterface;

public class Binance implements MarketInterface{

	private static Logger logger = LogManager.getLogger(Binance.class);
	
	private static final String URI_ROOT = "https://api.binance.com/api";
	private static final String URI_DEPTH = URI_ROOT + "/v1/depth?symbol={0}&limit=100"; // 0 : currency pair code (LTCBTC)
	private static final String URI_ACCOUNT = URI_ROOT + "/v3/account";
	private static final String URI_SERVER_TIME = URI_ROOT + "/v1/time";
	private static final String URI_ORDER = URI_ROOT + "/v3/order";
	private static final String URI_OPENORDER = URI_ROOT + "/v3/openOrders";
	
	private static final String PARAM_DEFAULT = "recvWindow={0}&timestamp={1}"; // 0 : receive delay	 1 : nonce
	private static final String PARAM_ORDER = "symbol={0}&orderId={1}&newOrderRespType=FULL&timestamp={2}"; // 0 : currency pair code (LTCBTC)	 1 : order id	2 :  : nonce
	private static final String PARAM_ORDER_QUERY = "symbol={0}&orderId={1}&timestamp={2}"; // 0 : currency pair code (LTCBTC)	 1 : order id	2 :  : nonce
	private static final String PARAM_OPENORDER = "timestamp={0}"; // 0 : currency pair code (LTCBTC)	 1 : order id	2 :  : nonce

	private String publicKey;
	private String privateKey;
	
//	{
//	  "lastUpdateId": 1027024,
//	  "bids": [
//	    [
//	      "4.00000000",     // PRICE
//	      "431.00000000",   // QTY
//	      []                // Ignore.
//	    ]
//	  ],
//	  "asks": [
//	    [
//	      "4.00000200",
//	      "12.00000000",
//	      []
//	    ]
//	  ]
//	}

	public Long getServerTime() {
		
		String result = HttpUtil.httpGet("binance", URI_SERVER_TIME);
		
		if (StringUtils.isEmpty(result)) {
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		return root.get("serverTime").getAsLong();
	}
	

	private HashMap<String, String> getHttpHeader() {
		HashMap<String, String> header = new HashMap<>();
		header.put("X-MBX-APIKEY", this.publicKey);

		return header;
	}
	
	private String getSignature(String s) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(this.privateKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
	 
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

//	{
//	  "symbol": "BTCUSDT",
//	  "orderId": 28,
//	  "clientOrderId": "6gCrw2kRUAF9CvJDGP16IP",
//	  "transactTime": 1507725176595,
//	  "price": "0.00000000",
//	  "origQty": "10.00000000",
//	  "executedQty": "10.00000000",
//	  "status": "FILLED",
//	  "timeInForce": "GTC",
//	  "type": "MARKET",
//	  "side": "SELL",
//	  "fills": [
//	    {
//	      "price": "4000.00000000",
//	      "qty": "1.00000000",
//	      "commission": "4.00000000",
//	      "commissionAsset": "USDT"
//	    }
//	  ]
//	}

	public String getOrderResult(String symbol, String orderId) {
		String param = MessageFormat.format(PARAM_ORDER, symbol, orderId, getNonce());
		
		String signature = getSignature(param);
		String result = HttpUtil.httpGet("binance", URI_ORDER + "?" + param + "&signature=" + signature, getHttpHeader());

		return result;
	}


	@Override
	public boolean validateMarket(String publicKey, String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		
		String param = MessageFormat.format(PARAM_DEFAULT, "5000", getNonce());
		
		String signature = getSignature(param);
		String result = HttpUtil.httpGet("binance", URI_ACCOUNT + "?" + param + "&signature=" + signature, getHttpHeader());
		logger.info(result);
//		System.out.println(result);
		if (StringUtils.isEmpty(result)) {
			System.out.println("INVALID KEY");
			return false;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		boolean canTrade = root.get("canTrade").getAsBoolean();
		if(canTrade) {
			return true;
		}else {
			return false;
		}
	}

	public boolean getAsset() {
		String param = MessageFormat.format(PARAM_DEFAULT, "5000", getNonce());
		
		String signature = getSignature(param);
		String result = HttpUtil.httpGet("binance", URI_ACCOUNT + "?" + param + "&signature=" + signature, getHttpHeader());

//		System.out.println(result);
		if (StringUtils.isEmpty(result)) {
			return false;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();

		JsonArray balances = (JsonArray)root.get("balances");
		
		return true;
	}
	
	@Override
	public MarketPriceDTO getMarketPrice(String coinType, double amount) throws Exception {
		MarketPriceDTO price = new MarketPriceDTO();
		String result = HttpUtil.httpGet("binance", MessageFormat.format(URI_DEPTH, coinType));
		
		if (StringUtils.isEmpty(result)) {
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		JsonArray bidsArry = root.getAsJsonArray("bids");
		JsonArray asksArr = root.getAsJsonArray("asks");
		
		double counts = 0;
		for (int i = 0,size = bidsArry.size(); i < size; i ++) {
			JsonArray arr = (JsonArray)bidsArry.get(i);
			price.setSellPrice(arr.get(0).getAsDouble());
			counts += arr.get(1).getAsDouble();
			if (counts > amount) {
				break;
			}
		}
		
		for (int i = 0, size = asksArr.size(); i < size; i ++) {
			JsonArray arr = (JsonArray)asksArr.get(i);
			price.setBuyPrice(arr.get(0).getAsDouble());
			counts += arr.get(1).getAsDouble();
			if (counts > amount) {
				break;
			}
		}
		price.setPrice();
		return price;
	}

	@Override
	public OrderDTO marketBuy(double quantity, double price, String coinType) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("symbol", coinType);
		param.put("side", "BUY");
		param.put("type", "MARKET");
		param.put("quantity", Double.toString(quantity));
		OrderDTO order = new OrderDTO();
		order.setCoinType(coinType);
		return placeOrder(param, order);
	}


	@Override
	public OrderDTO marketSell(double quantity, double price, String coinType) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("symbol", coinType);
		param.put("side", "SELL");
		param.put("type", "MARKET");
		param.put("quantity",  Double.toString(quantity));
		OrderDTO order = new OrderDTO();
		order.setCoinType(coinType);
		return placeOrder(param, order);
	}

	@Override
	public OrderDTO buyOrder(double quantity, double price, String coinType) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("symbol", coinType);
		param.put("side", "BUY");
		param.put("type", "LIMIT");
		param.put("timeInForce", "GTC"); 	
		param.put("quantity", Double.toString(quantity));
		param.put("price", String.format("%.8f", price));	
		OrderDTO order = new OrderDTO();
		order.setCoinType(coinType);
		return placeOrder(param, order);
	}

	@Override
	public OrderDTO sellOrder(double quantity, double price, String coinType) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("symbol", coinType);
		param.put("side", "SELL");
		param.put("type", "LIMIT");
		param.put("timeInForce", "GTC"); 	
		param.put("quantity", Double.toString(quantity));
		param.put("price", String.format("%.8f", price));	
		OrderDTO order = new OrderDTO();
		order.setCoinType(coinType);
		return placeOrder(param, order);
	}

	private OrderDTO placeOrder(HashMap<String, String> param, OrderDTO order) {
		
		param.put("newOrderRespType", "FULL");
		param.put("timestamp", getNonce());
		
		String signature = getSignature(HttpUtil.mapToRequestString(param));
		param.put("signature", signature);
		
		String result = HttpUtil.httpPost("binance", URI_ORDER, getHttpHeader(), param, ContentType.APPLICATION_FORM_URLENCODED);
		
//		System.out.println(result);
		if (StringUtils.isEmpty(result)) {
			return null;
		}
		
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		String status = root.get("status").getAsString();
		order.setOrderId(root.get("orderId").getAsString());
		
		if (!"FILLED".equals(status)) {
			order.setStatus(OrderStatus.RUNNING);
			return order;
		}
		
		order.setStatus(OrderStatus.DONE);
		
		JsonArray fills = (JsonArray)root.get("fills");
		int size = fills.size();
		
		double feeSum = 0d;
		double quantitySum = 0d;
		double amountSum = 0;
		
		double qty;
		for (int i = 0; i < size; i++) {
			JsonObject jo = (JsonObject)fills.get(i);
			feeSum += jo.get("commission").getAsDouble();
			qty = jo.get("qty").getAsDouble();
			quantitySum += qty;
			amountSum += jo.get("price").getAsDouble() * qty;
		}
		
		order.setPrice(amountSum / quantitySum);
		order.setAmount(quantitySum);
//		orderResult.fee = feeSum;
		return order;
	}
	
	@Override
	public void orderResult(OrderDTO order) throws Exception {
		String param = MessageFormat.format(PARAM_OPENORDER, order.getCoinType(), getNonce());
//		System.out.println(param);
		String signature = getSignature(param);
		String result = HttpUtil.httpGet("binance", URI_OPENORDER + "?" + param + "&signature=" + signature, getHttpHeader());
		
//		System.out.println(result);
		JsonParser parser = new JsonParser();
		JsonObject root = parser.parse(result).getAsJsonObject();
		
		String status = root.get("status").getAsString();
		
		if ("FILLED".equals(status)) {
			order.setStatus(OrderStatus.DONE);
		}
	}

	@Override
	public boolean cancelOrder(String orderId, String coinType, String orderType) throws Exception {
		return false;
	}
	
	@Override
	public List<OrderDTO> getOpenOrders() throws Exception {
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		
		String param = MessageFormat.format(PARAM_OPENORDER,  getNonce());
		String signature = getSignature(param);
		String result = HttpUtil.httpGet("binance", URI_OPENORDER + "?" + param + "&signature=" + signature, getHttpHeader());
//		System.out.println(result);
		JsonParser parser = new JsonParser();
		JsonArray orders = parser.parse(result).getAsJsonArray();
		
		for (int i = 0, size = orders.size(); i < size; i ++) {
			JsonObject obj = orders.get(i).getAsJsonObject();
			OrderDTO order = new OrderDTO();
			order.setOrderId(obj.get("orderId").getAsString());
			order.setPrice(obj.get("price").getAsDouble());
			order.setOrderType(obj.get("side").getAsString());
			order.setAmount(obj.get("origQty").getAsDouble());
			order.setDate(obj.get("time").getAsString());
			order.setCoinType(obj.get("symbol").getAsString());
			orderList.add(order);
		}
		return orderList;
	}
	
	public static void main(String[] args) {
		double quantity = 0.52354;
		double val = Math.round(quantity * 100.0) / 100.0;
		String tmp = Double.toString(val);
		System.out.println(tmp);
	}



}
