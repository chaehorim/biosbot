package com.afes.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyValueData {
	private Logger logger = LogManager.getLogger(KeyValueData.class.getName());
	
	public static Map<String, String> getData(){
		Map<String, String> map = new HashMap<>();
		map.put("1", "abc");
		map.put("2", "def");
		return map;
	}
	

}
