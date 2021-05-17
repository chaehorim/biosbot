package com.chochae.afes.asset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chochae.afes.asset.dao.AssetDto;
import com.chochae.afes.coins.CoinManager;
import com.chochae.afes.coins.model.CoinInfo;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.market.service.MarketManager;
import com.chochae.afes.offer.dto.DealInfo;
import com.chochae.afes.record.MsgCode;
import com.chochae.afes.record.RecordManager;

public class AssetManager {
	private static Map<String, List<AssetDto>>  assetMap = new HashMap<String, List<AssetDto>>();
	 
	public static void checkAsset() {
		List<MarketInfo> marketList = MarketManager.getValidatedMarketList();
		
		for (MarketInfo market : marketList) {
			List<AssetDto> assetList = market.getMarketCon().getAsset();
			if (assetList == null) {
				RecordManager.addRecord(MsgCode.SERVER_ABNORMAL, "Fail to get Asset", market.getMarketAddress(), "");
			}
			assetMap.put(market.getMarketUserName(), arrangeAssets(assetList));
		}
	}
	
	public static List<AssetDto> arrangeAssets(List<AssetDto> wholeList) {
		List<AssetDto> assetList = new ArrayList<AssetDto>();
		List<CoinInfo> coinList = CoinManager.getCoinList();
		
		for (CoinInfo coin : coinList) {
			for (AssetDto asset : wholeList) {
				if (asset.getCoinType().equals(coin.getCoinType())) {
					assetList.add(asset);
					break;
				}
			}
		}
		return assetList;
	}
	
	public static void updateAsset(String marketName, List<AssetDto> assetList) {
		assetMap.put(marketName, arrangeAssets(assetList));
	}
	
	public static Map<String, List<AssetDto>> getAssetMap() {
		return assetMap;
	}
	public static void updateAssumeAsset(DealInfo deal) {
		synchronized(assetMap) {
			String coinType = deal.getCoinType();
			List<AssetDto> fromMarketAssets = assetMap.get(deal.getFromMarket());
			List<AssetDto> toMarketAssets = assetMap.get(deal.getToMarket());
			
			AssetDto fromMarketBuyAsset = getAssetByCoin(fromMarketAssets, coinType);
			fromMarketBuyAsset.setFreeAmount(fromMarketBuyAsset.getFreeAmount() + deal.getCoinAmount());
			
			AssetDto fromMarketBaseAsset = getAssetByCoin(fromMarketAssets, "KRW");
			fromMarketBuyAsset.setFreeAmount(fromMarketBaseAsset.getFreeAmount() - deal.getBaseAmount());
			
			
			AssetDto toMarketSellAsset = getAssetByCoin(toMarketAssets, coinType);
			toMarketSellAsset.setFreeAmount(toMarketSellAsset.getFreeAmount() + deal.getCoinAmount());
			
			AssetDto toMarketBaseAsset = getAssetByCoin(toMarketAssets, "KRW");
			toMarketSellAsset.setFreeAmount(toMarketBaseAsset.getFreeAmount() - deal.getCoinAmount() * deal.getSellPrice());
			
		}
	}
	
	private static AssetDto getAssetByCoin(List<AssetDto> assetList, String coinType) {
		for (AssetDto asset : assetList) {
			if (coinType.equals(asset.getCoinType())) {
				return asset;
			}
		}
		return null;
	}
}
