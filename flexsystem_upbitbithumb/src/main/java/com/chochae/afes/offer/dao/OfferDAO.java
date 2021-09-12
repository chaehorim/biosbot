package com.chochae.afes.offer.dao;

import java.util.List;

import com.chochae.afes.offer.dto.DealInfo;
import com.chochae.afes.offer.dto.OfferInfo;
import com.chochae.afes.offer.dto.OrderInfo;
import com.chochae.afes.offer.dto.StatData;

public interface OfferDAO {
	public List<StatData> selectStatData(String tableName, String colName, String userId, long startTime, long endTime);
	
	public boolean insertOrderList(List<OrderInfo> orderList);
	
	public List<OrderInfo> getOrderList();
	
	public boolean insertDeal(DealInfo deal);
	
	public List<DealInfo> getDealList(String fromTime, String toTime, String fromTable, String toTable);
	
	public boolean truncateTable();
}
