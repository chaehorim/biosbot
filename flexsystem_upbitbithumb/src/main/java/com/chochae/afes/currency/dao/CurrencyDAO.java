package com.chochae.afes.currency.dao;

import java.util.List;

import com.chochae.afes.currency.model.CurrencyInfo;

public interface CurrencyDAO {
	
	public List<CurrencyInfo> getCurrencyList();
	
	public boolean	insertCurrency(CurrencyInfo currency);
	
	public boolean updateCurrency(CurrencyInfo currency);
	
	public boolean deleteCurrency(int fromCode, int toCode);
}
