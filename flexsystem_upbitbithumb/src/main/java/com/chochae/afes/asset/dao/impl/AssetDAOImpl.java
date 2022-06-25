package com.chochae.afes.asset.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.chochae.afes.asset.dao.AssetDAO;
import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.coins.model.CoinOptionInfo;

public class AssetDAOImpl implements AssetDAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<AssetDto> selectAssetList(String statDate) {
		List<AssetDto> assetList = new ArrayList<AssetDto>();
		String sql = "SELECT SUM(AMOUNT) as AMOUNT,  COINTYPE FROM asset where statDate = ? GROUP BY COINTYPE";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				AssetDto asset = new AssetDto();
				asset.setStatDate(statDate);
				asset.setCoinType(rs.getString("COINTYPE"));
				asset.setFreeAmount(rs.getDouble("AMOUNT"));
				assetList.add(asset);
			}
			return assetList;
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
	public void insertAssetList(List<AssetDto> assetList) {
		String sql = "INSERT INTO `asset`(`STATDATE`,`COINTYPE`,`AMOUNT`,`MARKETNAME`) VALUES" +
				"(?, ?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for (AssetDto asset : assetList) {
				
				int i = 1;
				ps.setString(i++, asset.getStatDate());
				ps.setString(i++, asset.getCoinType());
				ps.setDouble(i++, asset.getFreeAmount());
				ps.setString(i++, asset.getMarketName());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return ;
		} finally {
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
