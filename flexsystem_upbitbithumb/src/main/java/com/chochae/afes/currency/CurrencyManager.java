package com.chochae.afes.currency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chochae.afes.currency.dao.CurrencyDAO;
import com.chochae.afes.currency.model.CurrencyInfo;
import com.chochae.afes.service.DbLoader;

@Service
public class CurrencyManager {
	private static List<CurrencyInfo>               currencyList 			= new ArrayList<CurrencyInfo>();                // 환율 정보들
	
	public static void initCurrency() {
		CurrencyDAO curr = DbLoader.getCurrencyDBConnection();
		currencyList = curr.getCurrencyList();
	}
	
	// currency
	public static void setCurrency(CurrencyInfo newCur) {
		for (CurrencyInfo cur : currencyList) {
			if(cur.getFromCode() == newCur.getFromCode() 
					&& cur.getToCode() == newCur.getToCode()) {
				cur.setTime(System.currentTimeMillis());
				cur.setValue(newCur.getValue());
				return;
			}
		}
		currencyList.add(newCur);
	}

	public static List<CurrencyInfo> getCurrencyList() {
		return Collections.unmodifiableList(currencyList);
	}
	
	public static Float getCurrencyValue (int from, int to) {
		for (CurrencyInfo cur : currencyList) {
			if (cur.getFromCode() == from && cur.getToCode() == to) {
				return cur.getValue();
			}
		}
		return null;
	}
}
