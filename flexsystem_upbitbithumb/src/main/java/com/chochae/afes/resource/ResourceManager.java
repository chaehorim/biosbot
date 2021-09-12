package com.chochae.afes.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chochae.afes.common.utils.NumberUtil;
import com.chochae.afes.market.company.MarketClassFactory;
import com.chochae.afes.market.modal.IdInfo;
import com.chochae.afes.market.modal.MarketInfo;
import com.chochae.afes.market.service.IdManager;
import com.chochae.afes.market.service.MarketManager;
import com.chochae.afes.market.service.MarketServiceInterface;
import com.chochae.afes.offer.dto.OrderDetailInfo;
import com.chochae.afes.offer.dto.OrderInfo;
import com.chochae.afes.resource.dto.AssetInfo;
import com.chochae.afes.resource.dto.FluctuationBitCoin;
import com.chochae.afes.resource.dto.TransferDTO;
import com.chochae.afes.resource.dto.UserAssetInfo;
import com.chochae.afes.user.UserManager;
import com.chochae.afes.user.model.UserInfo;
import com.chochae.afes.user.model.UserSettingInfo;

@Service
public class ResourceManager {
	private static final Logger logger = LoggerFactory.getLogger(ResourceManager.class);
	
	private static final float MIN_TRANSFER_BITCOIN = 0.001f;
	
	
    private static Map<Integer, AssetInfo>      assetMap	= new HashMap<Integer, AssetInfo>();
//    private static AssetInfo					totAsset 	= new AssetInfo();												// total bitcoin count.
    /* for resource managing*/
    private static Map<String, List<UserAssetInfo>> userAssetsMap = new HashMap<String, List<UserAssetInfo>>();          // marketId, userAsset
    /* default total resource*/
    private static Map<String, AssetInfo>       defaultAssetMap = new HashMap<String, AssetInfo>();						// default asset 
    
    public static void removeAssets(int locale) {
    	assetMap.remove(locale);
    }
    
    public static void resetMarketAsset(MarketInfo market) {
    	List<UserInfo> operatorList = UserManager.getOperatorList();
    	resetMarketUserAsset(market, operatorList);
    	//addDefaulAsset(market);
    }
    
    public static void resetAllMarketAsset() {
    }
    
    private static void resetMarketUserAsset(MarketInfo market, List<UserInfo> userList) {
//    	MarketServiceInterface marketClass = MarketClassFactory.getMarketClass(market.getMarketId());
//    	UserAssetInfo userAsset = marketClass.getRemainAsset();
//    	float remainAmount = userAsset.getRemainAmount();
//    	float remainBitCoin = userAsset.getRemainBcCnt();
//    	logger.info("[RESET MARKETS] -> [" + market.getMarketId() + "], amount:[" + remainAmount + "], coin:[" + remainBitCoin + "]");
//    	List<UserAssetInfo> assetList = new ArrayList<UserAssetInfo>();
//    	for (UserInfo user : userList) {
//    		float perc = user.getOperatorPercentage() / 100;
//    		UserAssetInfo asset = new UserAssetInfo(user.getUserId(), market.getMarketId(), 
//    				(float) (Math.floor(remainAmount * perc * 100)/100), (float) (Math.floor(remainBitCoin * perc * 100)/100));   // 있는 값 소수점 버리기
//    		assetList.add(asset);
//    	}
//    	userAssetsMap.put(market.getMarketId(), assetList);
    }
    
    public static List<UserAssetInfo> getUserAssets(String userId) {
    	List<UserAssetInfo> assetList = new ArrayList<UserAssetInfo>();
    	List<IdInfo> idList = IdManager.getDefaultMarketId();
    	synchronized(userAssetsMap) {
    		Set<String> marketSet = userAssetsMap.keySet();
    		for (String marketId: marketSet) {
    			List<UserAssetInfo> userAssetList = userAssetsMap.get(marketId);
    			for (IdInfo id : idList) {
    				if (id.getMarketId().equals(marketId)) {
		    			for (UserAssetInfo userAsset : userAssetList) {
		    				if (userId.equals(userAsset.getUserId())) {
    							assetList.add(userAsset);	
    						}
    					}
    				}
    			}
    		}
    	}
    	return Collections.unmodifiableList(assetList);
    }
    
    
    public static UserAssetInfo getUserAssetByMarket(String userId, String marketId) {
    	synchronized(userAssetsMap) {
			List<UserAssetInfo> userAssetList = userAssetsMap.get(marketId);
			for (UserAssetInfo userAsset : userAssetList) {
				if (userId.equals(userAsset.getUserId())) {
					return userAsset;
				}
			}
    	}
    	return null;
    }
    
    // call by Thread2 checking orders.
    public static void distUserAsset(String marketId, String userId, float amount, float bcCnt) {
    	synchronized(userAssetsMap) {
			List<UserAssetInfo> userAssetList = userAssetsMap.get(marketId);
			for (UserAssetInfo userAsset : userAssetList) {
				if (userId.equals(userAsset.getUserId())) {
					logger.info("[distUserAsset] before : amount :[" + amount + "], bccnt : [" + bcCnt + "], " + userAsset);
					userAsset.setRemainAmount(NumberUtil.getRound(userAsset.getRemainAmount() + amount));
					userAsset.setRemainBcCnt(NumberUtil.getRound(userAsset.getRemainBcCnt() + bcCnt));
					logger.info("[distUserAsset] amount :[" + amount + "], bccnt : [" + bcCnt + "], " + userAsset);
				}
			}
    	}
    }
    
    public static void setUserAssetByOrder(OrderInfo order) {
    	List<OrderDetailInfo> detailList = order.getOrderDetailList();
    	String userId = order.getUserId();
    	
    	for (OrderDetailInfo det : detailList) {
    		String marketId = det.getMarketId();
    		int type = det.getTradeType();
    		float amount = det.getAmount();
    		float bcCnt = det.getBitcoinCnt();
    		if (type == 1) {  // buy
    			distUserAsset(marketId, userId, amount * -1, bcCnt);
    		} else if (type == 2) {
    			distUserAsset(marketId, userId, amount, bcCnt * -1);
    		} else {
    			logger.error("TYPE IS INVALID. MARKET DETAIL : [" + det.toString() + "]");
    		}
    	}
    }
    
    public static AssetInfo getMarketAsset(String marketId) {
//    	MarketServiceInterface marketClass = MarketClassFactory.getMarketClass(marketId);
//		float amount = marketClass.getRemainAmount();
//		float bitcoiCnt = marketClass.getRemainBitCoin();
		AssetInfo asset = new AssetInfo();
//		asset.setAmounts(amount);
//		asset.setBitCnt(bitcoiCnt);
		return asset;
    }
    
    /* transfer to default asset. only run by manager ui reset asset*/
    public static boolean transferTodefaultAsset() {
    	for( Entry<String, AssetInfo> elem : defaultAssetMap.entrySet() ){
            String marketId = elem.getKey();
            AssetInfo defaultAsset = elem.getValue();
        }
    	return true;
    }
    
    public static boolean transferBitCoin(String fromMarket, String toMarket, float bitCoinCnt) {
//    	MarketServiceInterface marketClass = MarketClassFactory.getMarketClass(fromMarket);
//    	MarketInfo toMarketInfo = MarketManager.getMarketById(toMarket);
//    	MarketInfo fromMarketInfo = MarketManager.getMarketById(fromMarket);
//    	logger.info("TEST FOR SENDING. " + toMarketInfo.getMarketAddress() +  bitCoinCnt + toMarketInfo.getTransferPassword());
//    	//boolean successYn = marketClass.transferBitCoin(toMarketInfo.getMarketAddress(), bitCoinCnt, fromMarketInfo.getTransferPassword());
    	boolean successYn = true;
    	return successYn;
    }
    
    public static List<TransferDTO> transferAll() {
    	//test
    	List<FluctuationBitCoin> markets = new ArrayList<FluctuationBitCoin>();
    	List<TransferDTO> transferList = getTransferList(markets);
    	
    	return transferList;
    }
    
    public static void main(String[] args) {
    	List<FluctuationBitCoin> markets = new ArrayList<FluctuationBitCoin>();
    	markets.add(new FluctuationBitCoin("A", 150.001f));
    	markets.add(new FluctuationBitCoin("B", 10));
    	markets.add(new FluctuationBitCoin("C", 30));
    	markets.add(new FluctuationBitCoin("D", -120));
    	markets.add(new FluctuationBitCoin("E", -60));
    	List<TransferDTO> transferList = getTransferList(markets);
    	
    	
    	for (TransferDTO transfer : transferList) {
    		System.out.println(transfer);
    	}
    	
    	float a = 8.36f;
    	float b = -0.5f;
    	System.out.println((float)Math.floor(a * 0.88 * 100) / 100);
    	float c = a - 0.5f;

    		
    	System.out.println(NumberUtil.getRound(a+b) + " " + c + " ");
    	
    	float xx = 21474836;
    	float amount = 5375000;
    	float res = NumberUtil.getRound(xx + amount);
    	float zz = xx + amount;
    	System.out.println(xx+amount + " " + res + "    " + zz + " " +  NumberUtil.getRound(zz) );
    }
    
    private static List<TransferDTO> getTransferList(List<FluctuationBitCoin> markets) {
    	List<TransferDTO> transferList = new ArrayList<TransferDTO>();
    	List<FluctuationBitCoin> fromMarkets = new ArrayList<FluctuationBitCoin>();
    	List<FluctuationBitCoin> toMarkets = new ArrayList<FluctuationBitCoin>();
    	
    	for (FluctuationBitCoin market : markets) {
    		if (market.getBitcoin() > 0) {
    			fromMarkets.add(market);
    		} else if (market.getBitcoin() < 0) {
    			toMarkets.add(market);
    		}
    	}
    	
    	for (FluctuationBitCoin fromMarket : fromMarkets) {
    		float fromBitcoin = fromMarket.getBitcoin(); 
    		System.out.println(fromBitcoin);
    		for (FluctuationBitCoin toMarket : toMarkets) {
    			if (fromBitcoin < MIN_TRANSFER_BITCOIN) {
    				break;
    			} 
    			float toBitcoin = toMarket.getBitcoin();
    			if (toBitcoin * -1 < MIN_TRANSFER_BITCOIN) {
    				continue;
    			}
    			TransferDTO transfer = new TransferDTO();
    			transfer.setFromMarket(fromMarket.getMarketId());
    			transfer.setToMarket(toMarket.getMarketId());
    			
    			float gap = fromBitcoin + toBitcoin;
    			if (gap > 0) {
    				transfer.setBitcoin(toBitcoin * -1);
    				fromMarket.setBitcoin(gap);
    				fromBitcoin = gap;
    				toMarket.setBitcoin(0);
    			} else {
    				transfer.setBitcoin(fromBitcoin);
    				toMarket.setBitcoin(gap);
    				fromMarket.setBitcoin(0);
    			}
    			transferList.add(transfer);
    		}
    	}
    	
    	return transferList; 
    }
}
