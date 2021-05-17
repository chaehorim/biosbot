package com.chochae.afes.properties.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chochae.afes.properties.dao.PropertiesDAO;
import com.chochae.afes.properties.modal.PropertyInfo;

public class PropertiesDAOImpl implements PropertiesDAO{
	private static final Logger logger = LoggerFactory.getLogger(PropertiesDAOImpl.class);
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public PropertyInfo selectPrperties() {
		PropertyInfo prop = new PropertyInfo();
		String sql = "SELECT * FROM properties ";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				prop.setPeriod(rs.getInt("RUN_PERIOD"));
				prop.setBidBitcoin(rs.getFloat("BID_BITCOIN"));
				prop.setMinMargin(rs.getFloat("MIN_MARGIN"));
				prop.setMinBuyMargin(rs.getFloat("MIN_BUY_MARGIN"));
				prop.setMinSellMargin(rs.getFloat("MIN_SELL_MARGIN"));
				prop.setOrderConfirmPeriod(rs.getInt("ORDER_CONFIRM_PERIOD"));
				prop.setOrderChangeCnt(rs.getInt("ORDER_CHANGE_CNT"));
				prop.setOrderChangeWaitCnt(rs.getInt("ORDER_CHANGE_WAIT_CNT"));
			}
			logger.info("[PROPERTIES]" + prop);
			return prop;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	@Override
	public boolean insertProperties(PropertyInfo prop) throws Exception {
		String insertSql = "INSERT INTO `properties`(`RUN_PERIOD`,`BID_BITCOIN`,`MIN_MARGIN`,`MIN_BUY_MARGIN`,`MIN_SELL_MARGIN`,`ORDER_CONFIRM_PERIOD`,`ORDER_CHANGE_CNT`,`ORDER_CHANGE_WAIT_CNT`,`ACTIVE_YN`) VALUES" +
				"(?, ?, ?, ?, ?, ? , ? ,?)";
		String updateSql = "UPDATE properties SET `RUN_PERIOD` = ?, `BID_BITCOIN` = ?, `MIN_MARGIN` = ?,`MIN_BUY_MARGIN` = ?,`MIN_SELL_MARGIN` = ?,`ORDER_CONFIRM_PERIOD` = ?,`ORDER_CHANGE_CNT` = ?,`ORDER_CHANGE_WAIT_CNT` = ?,`ACTIVE_YN` = ?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = null;
			
			ps = conn.prepareStatement("SELECT COUNT(*) AS COUNT FROM properties");
			ResultSet rs = ps.executeQuery();
			
			int rowNum = 0;
			while (rs.next()) {
				rowNum = rs.getInt("COUNT");
			}
			ps.close();
			
			int i = 1;
			if (rowNum == 0) {
				PreparedStatement ps1 = conn.prepareStatement(insertSql);
				ps1.setInt(i++, prop.getPeriod());
				ps1.setFloat(i++, prop.getBidBitcoin());
				ps1.setFloat(i++, prop.getMinMargin());
				ps1.setFloat(i++, prop.getMinBuyMargin());
				ps1.setFloat(i++, prop.getMinSellMargin());
				ps1.setInt(i++, prop.getOrderConfirmPeriod());
				ps1.setInt(i++, prop.getOrderChangeCnt());
				ps1.setInt(i++, prop.getOrderChangeWaitCnt());
				ps1.setString(i++, "Y");
				ps1.executeUpdate();
				ps1.close();
			}  else {
				PreparedStatement ps1 = conn.prepareStatement(updateSql);
				ps1.setInt(i++, prop.getPeriod());
				ps1.setFloat(i++, prop.getBidBitcoin());
				ps1.setFloat(i++, prop.getMinMargin());
				ps1.setFloat(i++, prop.getMinBuyMargin());
				ps1.setFloat(i++, prop.getMinSellMargin());
				ps1.setInt(i++, prop.getOrderConfirmPeriod());
				ps1.setInt(i++, prop.getOrderChangeCnt());
				ps1.setInt(i++, prop.getOrderChangeWaitCnt());
				ps1.setString(i++, "Y");
				ps1.executeUpdate();
				ps1.close();
			}
		
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
