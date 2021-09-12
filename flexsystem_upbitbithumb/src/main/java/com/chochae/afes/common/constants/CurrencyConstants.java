package com.chochae.afes.common.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyConstants {
	private static Map<Integer, String> codeCtyMap = new HashMap<Integer, String>();
	private static Map<String, Integer> ctyCodeMap = new HashMap<String, Integer>();
	private static final Integer[] codes = { 82, 86 , 63};
	private static final String[]  countries = {"Korea", "China", "Philpines"};
	private static final List<Integer> currencyPriority = Arrays.asList(86, 82);
	
	
	public static void init() {
		for (int i = 0; i < codes.length; i ++) {
			int code  = codes[i];
			String country = countries[i];
			codeCtyMap.put(code, country);
			ctyCodeMap.put(country, code);
		}		
	}
	
	public static String getCountry(int code) {
		if (codeCtyMap.size() == 0) {
			init();
		}
		return codeCtyMap.get(code);
	}
	
	public static int getCode(String country) {
		if (ctyCodeMap.size() == 0) {
			init();
		}
		return ctyCodeMap.get(country);
	}
	
	public static boolean calcCurrencyPriority(int code1, int code2) {
		 int pos1 = currencyPriority.indexOf(code1);
		 int pos2 = currencyPriority.indexOf(code2);
		 
		 return pos1 < pos2;
	}

	
	public static void main(String[] args) {
		System.out.println(calcCurrencyPriority(86, 82));
	}
}
