package com.chochae.afes.common.utils;

import java.text.SimpleDateFormat;

public class TimeDateUtil {
	public static String getStatDate(long timestamp) {
		String ret = new SimpleDateFormat("yyyyMMddHHmmss").format(timestamp);
		return ret;
	}
	
	public static String getStatDay(long timestamp) {
		String ret = new SimpleDateFormat("yyyyMMdd").format(timestamp);
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(getStatDate(System.currentTimeMillis()));
	}
}
