package com.chochae.afes.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NumberUtil {
	private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);
	public static float getFloatFromJSON(Object obj) {
		
		String type = obj.getClass().getName();
		if (type.equals("java.lang.String")) {
			return Float.parseFloat((String)obj);
		} else if (type.equals("java.lang.Double")) {
			return  Float.parseFloat(String.valueOf((Double) obj));
		} else if (type.equals("java.lang.Float")) {
			return  (Float)obj;
		} else if (type.equals("java.lang.Long")) {
			return  (float) (((Long) obj).longValue());
		} else {
			logger.error("NUMBER UTIL : INVALID TYPE IS : " + type);
			throw new NumberFormatException();
		}
		
	}
	
	public static float getFloor(float num) {
		return (float)Math.floor(num * 100) / 100;
	}
	
	public static float getRound(float num) {
		double x = num * 100;
		double ret = Math.round(x) / 100.0;
		Float val = (float)ret;
		return val.floatValue();
	}
	
	public static double floatToDouble(float a) {
		Float f = new Float(a);
        Double d = new Double(f.toString());
        return d;
	}
	
	public static double doubleToFloat(double a, int scale) {
		BigDecimal bd = new BigDecimal(a);
		bd.setScale(scale, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static float addFloat(float a, float b) {
		BigDecimal s1 = new BigDecimal(Float.toString(a));
		BigDecimal s2 = new BigDecimal(Float.toString(b));
		
		return s1.add(s2).floatValue();
	}
	
	public static float subFloat(float a, float b) {
		BigDecimal s1 = new BigDecimal(Float.toString(a));
		BigDecimal s2 = new BigDecimal(Float.toString(b));
		
		return s1.subtract(s2).floatValue();
	}
	
	public static float multiFloat(float a, float b) {
		BigDecimal s1 = new BigDecimal(Float.toString(a));
		BigDecimal s2 = new BigDecimal(Float.toString(b));
		
		return s1.multiply(s2).floatValue();
	}
	
	public static float divideFloat(float a, float b) {
		BigDecimal s1 = new BigDecimal(Float.toString(a));
		BigDecimal s2 = new BigDecimal(Float.toString(b));
		
		return s1.divide(s2).floatValue();
	}
	
	public static void main(String[] args) {
		Long a = (long) 10;
		Object b = a;
		Long d = (Long)a;
		float c = (float) (((Long) d).longValue());
		System.out.println(c);
		
		System.out.println(floatToDouble((float) 6809.6));
		System.out.println(doubleToFloat(6809.6, 2));
		
		float x = 6809.6f;
		float y = 1.1f;
		System.out.println(subFloat(x, y)+ " " + (x - y));
		
		Double d1 = 2.4;
		Double d2 = 0.8;
		System.out.println(d1 / d2);   
 
		
	}
	
}
