package com.chochae.afes.coins.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.chochae.afes.coins.dao.CoinDAO;
import com.chochae.afes.coins.model.CoinInfo;
import com.chochae.afes.coins.model.CoinOptionInfo;
import com.chochae.afes.user.model.UserInfo;

public class CoinDAOImpl implements CoinDAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<CoinOptionInfo> selectCoinOption() {
		List<CoinOptionInfo> coinOptionList = new ArrayList<CoinOptionInfo>();
		String sql = "SELECT * FROM coinoption";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CoinOptionInfo option = new CoinOptionInfo();
				option.setFromMarket(rs.getString("FROMMARKET"));
				option.setToMarket(rs.getString("TOMARKET"));
				option.setCoinType(rs.getString("COINTYPE"));
				option.setBetAmount(rs.getDouble("BETAMOUNT"));
				option.setBetProfit(rs.getDouble("BETPROFIT"));
				option.setActiveYn(rs.getBoolean("ACTIVE_YN"));
				coinOptionList.add(option);
			}
			return coinOptionList;
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
	public boolean updateCoinOptions(List<CoinOptionInfo> optionList) {
		for (CoinOptionInfo option :  optionList) {
	String sql = "UPDATE coinoption SET  BETAMOUNT = ? AND BETPROFIT = ? AND ACTIVE_YN = ? WHERE FROMMARKET = ? AND TOMARKET = ? AND COINTYPE = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
				int cnt = 1;
				ps.setDouble(cnt++, option.getBetAmount());
				ps.setDouble(cnt++, option.getBetProfit());
				ps.setString(cnt++, option.isActiveYn()?"Y":"N");
				ps.setString(cnt++, option.getFromMarket());
				ps.setString(cnt++, option.getToMarket());
				ps.setString(cnt++, option.getCoinType());
				ps.addBatch();
				System.out.println(option);
				ps.executeUpdate();
//			ps.executeBatch();
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {}
			}
		}
		}
		return true;
	}

	@Override
	public List<CoinInfo> selectCoin() {
		List<CoinInfo> coinList = new ArrayList<CoinInfo>();
		String sql = "SELECT * FROM coin";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CoinInfo coin = new CoinInfo();
				coin.setCoinName(rs.getString("COINNAME"));
				coin.setCoinType(rs.getString("COINTYPE"));
				coin.setPointLength(rs.getInt("POINTLENGTH"));
				coin.setBaseCoin("Y".equals(rs.getString("BASECOIN_YN"))? true: false);
				coin.setUseYn("Y".equals(rs.getString("USE_YN"))? true: false);
				coinList.add(coin);
			}
			return coinList;
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

}
