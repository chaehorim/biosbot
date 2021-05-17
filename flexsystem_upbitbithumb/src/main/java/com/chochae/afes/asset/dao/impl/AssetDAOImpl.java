package com.chochae.afes.asset.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import com.chochae.afes.asset.dao.AssetDAO;
import com.chochae.afes.asset.dao.AssetDto;

public class AssetDAOImpl implements AssetDAO{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<AssetDto> selectAssetList(String statDate) {
		return null;
	}

	@Override
	public void insertAssetList(List<AssetDto> assetList) {
		
	}
}
