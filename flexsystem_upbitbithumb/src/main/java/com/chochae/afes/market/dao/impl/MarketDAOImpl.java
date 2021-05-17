/**
 * 
 */
package com.chochae.afes.market.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chochae.afes.common.constants.CurrencyConstants;
import com.chochae.afes.market.dao.MarketDAO;
import com.chochae.afes.market.modal.MarketInfo;

/**
 * market CRSD
 * @author Isco
 *
 */
public class MarketDAOImpl implements MarketDAO{
	private static final Logger logger = LoggerFactory.getLogger(MarketDAOImpl.class);
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<MarketInfo> selectMaketList() {
		List<MarketInfo> marketList = new ArrayList<MarketInfo>();
		String sql = "SELECT * FROM market";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				MarketInfo market = new MarketInfo();
				market.setMarketId(rs.getString("MARKET_ID"));
				market.setMarketUserName(rs.getString("MARKET_NAME"));
				int currency = rs.getInt("CURRENCY");
				String currencyName = CurrencyConstants.getCountry(currency);
				market.setCurrencyCode(currency);
				market.setCurrencyName(currencyName);
				market.setUrl(rs.getString("URL"));
				market.setAmount(rs.getFloat("AMOUNT"));
				market.setBcCnt(rs.getFloat("BITCOIN_CNT"));
				market.setActiveYn(rs.getString("ACTIVE_YN"));
				market.setFromYn("Y".equals(rs.getString("FROM_YN")) ? true : false);
				market.setToYn("Y".equals(rs.getString("TO_YN")) ? true : false);
				market.setValidateYn(false);
				marketList.add(market);
				logger.info("[MARKET]" + market.toString());
			}
			return marketList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				logger.info(" null yn : rs [" + (rs == null) + "], ps [" + (ps == null) + "]");
				
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}


	@Override
	public MarketInfo selectMaketById(String marketId) {
		MarketInfo market = new MarketInfo();
		String sql = "SELECT * FROM market WHERE MARKET_ID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, marketId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				market.setMarketId(rs.getString("MARKET_ID"));
				market.setMarketUserName(rs.getString("MARKET_NAME"));
				int currency = rs.getInt("CURRENCY");
				String currencyName = CurrencyConstants.getCountry(currency);
				market.setCurrencyCode(currency);
				market.setCurrencyName(currencyName);
				market.setUrl(rs.getString("URL"));
				market.setAmount(rs.getFloat("AMOUNT"));
				market.setBcCnt(rs.getFloat("BITCOIN_CNT"));
				market.setActiveYn(rs.getString("ACTIVE_YN"));
			}
			return market;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean insertMarket(MarketInfo market) {
		
		String sql = "INSERT INTO market(`MARKET_ID`, `MARKET_NAME`,`CURRENCY`,`URL`,`AMOUNT`, `BITCOIN_CNT`,`ACTIVE_YN`, `FROM_YN`, `TO_YN`) " +
					  "VALUES(?, ?, ?, ?, ?, ?, ? , ?, ?)" ; 
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, market.getMarketId());
			ps.setString(i++, market.getMarketUserName());
			ps.setFloat(i++, market.getCurrencyCode());
			ps.setString(i++, market.getUrl());
			ps.setFloat(i++, market.getAmount());
			ps.setFloat(i++, market.getBcCnt());
			ps.setString(i++, market.getActiveYn());
			ps.setString(i++, market.isFromYn()? "Y":"N");
			ps.setString(i++, market.isToYn() ? "Y":"N");
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
	public boolean updateMarket(MarketInfo market) {
		String sql = "UPDATE market SET " +
					 "MARKET_NAME = ?,CURRENCY = ?,  URL = ?,AMOUNT = ?, BITCOIN_CNT = ?,ACTIVE_YN = ? " +
					 "WHERE MARKET_NAME = ? ";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setString(i++, market.getMarketUserName());
			ps.setFloat(i++, market.getCurrencyCode());
			ps.setString(i++, market.getUrl());
			ps.setFloat(i++, market.getAmount());
			ps.setFloat(i++, market.getBcCnt());
			ps.setString(i++, market.getActiveYn());
			ps.setString(i++, market.getMarketUserName());
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
	public boolean deleteMarket(String marketId) {
		String sql = "DELETE FROM market  WHERE MARKET_ID = ? ";
	
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, marketId);
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


}
