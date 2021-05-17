package com.chochae.afes.market.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chochae.afes.common.dto.ReturnMsg;
import com.chochae.afes.market.modal.IdInfo;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.resource.ResourceManager;


public class IdManager {

	private static List<IdInfo>     idList = new ArrayList<IdInfo>();
	
	public static List<IdInfo> getIdList() {
		List<IdInfo> dataList = new ArrayList<IdInfo>();
		for (IdInfo id : idList) {
			dataList.add(new IdInfo(id.getUserId(), id.getDesc(), id.getCurrencyCode(), id.getMarketId(), id.isDefaultYn()));
		}
		return dataList;
	}
	
	public static ReturnMsg addMarketId(IdInfo id) {
		ReturnMsg rg = new ReturnMsg();
		
		String marketId = id.getMarketId();
//		MarketServiceInterface marketClass = MarketClassFactory.create(marketId);
//		boolean successYn = marketClass.validateMarket(id.getPublicKey(), id.getPrivateKey());
		boolean successYn = true;
		rg.setSuccesYn(successYn);
		
		if (successYn) {
			idList.add(id);
//			MarketClassFactory.addIdClass(id.getUserId(), marketClass);
		}
		
		return rg;
	}
	
	public static void deleteId(String userId) {
		for (IdInfo id : idList) {
			if (id.getUserId().equals(userId)) {
				idList.remove(id);
//				MarketClassFactory.removeIdClass(userId);
				break;
			}
		}
	}
	
	public static void setDefault(String userId, boolean setYn) {
		for (IdInfo id : idList) {
			if (id.getUserId().equals(userId)) {
				id.setDefaultYn(setYn);
			}
		}
	}
	
	public static List<IdInfo> getDefaultMarketId() {
		List<IdInfo> dataList = new ArrayList<IdInfo>();
		for (IdInfo id : idList) {
			if (id.isDefaultYn()) {
				dataList.add(id);
			}
		}
		return dataList;
	}
}
