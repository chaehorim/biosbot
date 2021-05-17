/**
 * 
 */
package com.chochae.afes.common.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author banba
 *
 */
public class CommonUtil {
	public static float getJsonBidVal(JSONArray bidArr, String cntName, String priceName, float bidCnt) {
		float tmpCnt =  bidCnt;
		float sum = 0;
		float val = 0;
		for(int i=0;i<bidArr.size();i++){
			JSONObject bidVal = (JSONObject) bidArr.get(i);
			System.out.println(bidVal);
			float cnt  = Float.parseFloat((String) bidVal.get(cntName));
			float price = Float.parseFloat((String) bidVal.get(priceName));
			if (tmpCnt <= cnt) {
				sum += tmpCnt * price; 
				tmpCnt = 0;
				break;
			} else {
				sum += cnt * price;
				tmpCnt = tmpCnt - cnt; 
			}
		}
		// orderbook 값보다 큰 case
		if (tmpCnt > 0) {
			return 0;
		}
		if (sum > 0)
			val = NumberUtil.getFloor(sum / bidCnt);
		return val;
	}
	
	public static void main(String[] args) {
	}
	
}
