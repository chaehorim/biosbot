package engine.common;

public class CommonVariables {

	public static final long DEAL_RUNNING_TIME = 3000;
	
	public static double BASIC_TRADE_UNIT = 0.4;
	public static String BASIC_TRADE_COIN = "ETHBTC";
	public static final double BASIC_PROFIT = 0.005;
	
	public static void init(String coinType) {
		if ("XRPBTC".equals(coinType)) {
			BASIC_TRADE_COIN = "XRPBTC";
			BASIC_TRADE_UNIT = 250.0;
		} else if ("ETHBTC".equals(coinType)) {
			BASIC_TRADE_COIN = "ETHBTC";
			BASIC_TRADE_UNIT = 0.4;
		}
	}
}
