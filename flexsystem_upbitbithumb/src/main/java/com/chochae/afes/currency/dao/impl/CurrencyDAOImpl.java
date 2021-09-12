package com.chochae.afes.currency.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.chochae.afes.currency.dao.CurrencyDAO;
import com.chochae.afes.currency.model.CurrencyInfo;

public class CurrencyDAOImpl implements CurrencyDAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<CurrencyInfo> getCurrencyList() {
		List<CurrencyInfo> currencyList = new ArrayList<CurrencyInfo>();
		String sql = "SELECT * FROM currency";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int fromCode = rs.getInt("FROM_COUNTRY_CODE");
				int toCode = rs.getInt("TO_COUNTRY_CODE");
				float value = rs.getFloat("VALUE");
				long time = rs.getLong("TIME");
				CurrencyInfo currency = new CurrencyInfo(fromCode, toCode, value, time);
				currencyList.add(currency);
			}
			rs.close();
			ps.close();
			return currencyList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public boolean insertCurrency(CurrencyInfo currency) {
		String sql = "INSERT INTO currency (FROM_COUNTRY_CODE, TO_COUNTRY_CODE,VALUE,TIME) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setInt(i++, currency.getFromCode());
			ps.setInt(i++, currency.getToCode());
			ps.setFloat(i++, currency.getValue());
			ps.setLong(i++, System.currentTimeMillis());
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
	public boolean updateCurrency(CurrencyInfo currency) {
		String sql = "UPDATE currency SET " +
				"	VALUE = ?, TIME = ? WHERE FROM_COUNTRY_CODE =? AND TO_COUNTRY_CODE = ? ";

		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			ps.setFloat(i++, currency.getValue());
			ps.setLong(i++, System.currentTimeMillis());
			ps.setInt(i++, currency.getFromCode());
			ps.setInt(i++, currency.getToCode());
			
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
	public boolean deleteCurrency(int fromCode, int toCode) {
		String sql = "DELETE FROM currency WHERE FROM_COUNTRY_CODE =? AND TO_COUNTRY_CODE = ? ";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, fromCode);
			ps.setInt(2, toCode);
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
