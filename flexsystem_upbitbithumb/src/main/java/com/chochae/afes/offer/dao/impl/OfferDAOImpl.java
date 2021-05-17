package com.chochae.afes.offer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.chochae.afes.offer.dao.OfferDAO;
import com.chochae.afes.offer.dto.DealInfo;
import com.chochae.afes.offer.dto.OfferInfo;
import com.chochae.afes.offer.dto.OrderDetailInfo;
import com.chochae.afes.offer.dto.OrderInfo;
import com.chochae.afes.offer.dto.StatData;

public class OfferDAOImpl implements OfferDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}




	@Override
	public List<OrderInfo> getOrderList() {
		return null;
	}

	@Override
	public boolean truncateTable() {
		String sql = "TRUNCATE offer";
		String sql2 = "TRUNCATE order";
		String sql3 = "TRUNCATE order_detail";
	
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			
			ps2 = conn.prepareStatement(sql2);
			ps2.executeQuery();
			
			ps3 = conn.prepareStatement(sql3);
			ps3.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					ps.close();
					ps2.close();
					ps3.close();
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return false;
	}

	@Override
	public List<StatData> selectStatData(String tableName, String colName, String userId, long startTime, long endTime) {
		List<StatData> statList = new ArrayList<StatData>();
		
		String sql = "SELECT " + tableName + " FROM USER WHERE TIME > ? AND TIME < ? AND USER_ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, startTime);
			ps.setLong(2, endTime);
			ps.setString(3, userId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				StatData stat = new StatData();
				stat.setTime(rs.getLong("TIME"));
				stat.setValue(rs.getFloat(colName));
				statList.add(stat);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return statList;
	}




	@Override
	public boolean insertOrderList(List<OrderInfo> orderList) {
		return false;
	}


	@Override
	public boolean insertDeal(DealInfo deal) {
		String sql = "INSERT INTO deal(`STATDATE`,`FROM_MARKET`,`TO_MARKET`,`COINTYPE`,`BET_PROFIT`,`PROFIT`,`BET_AMOUNT`,`SEARCH_BUY_PRICE`,`SEARCH_SELL_PRICE`,`BUY_PRICE`,`SELL_PRICE`,`BASE_AMOUNT`,`COIN_AMOUNT`,`SUCCESS_YN`) VALUES" +
				"(?, ?, ?, ?, ?, ? , ? ,?, ? ,?, ?,?, ?, ?)";
		Connection conn = null;
		System.out.println("GO" + deal);
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, deal.getStatDate());
			ps.setString(i++, deal.getFromMarket());
			ps.setString(i++, deal.getToMarket());
			ps.setString(i++, deal.getCoinType());
			ps.setDouble(i++, deal.getBetProfit());
			ps.setDouble(i++, deal.getProfit());
			ps.setDouble(i++, deal.getBetAmount());
			ps.setDouble(i++, deal.getSearchBuyPrice());
			ps.setDouble(i++, deal.getSearchSellPrice());
			ps.setDouble(i++, deal.getBuyPrice());
			ps.setDouble(i++, deal.getSellPrice());
			ps.setDouble(i++, deal.getBaseAmount());
			ps.setDouble(i++, deal.getCoinAmount());
			ps.setString(i++, deal.isFinYn() ? "Y" : "N");
			System.out.println(deal + " " +  deal.getBaseAmount());
			ps.executeUpdate();
			ps.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return true;
	}


	@Override
	public List<DealInfo> getDealList(String fromTime, String toTime, String fromTable, String toTable) {
		return null;
	}
	
}
