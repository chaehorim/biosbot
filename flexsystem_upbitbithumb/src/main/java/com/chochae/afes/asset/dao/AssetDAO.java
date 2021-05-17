package com.chochae.afes.asset.dao;

import java.util.List;

public interface AssetDAO {
	public List<AssetDto> selectAssetList(String statDate);
	
	public void insertAssetList(List<AssetDto> assetList);
}
