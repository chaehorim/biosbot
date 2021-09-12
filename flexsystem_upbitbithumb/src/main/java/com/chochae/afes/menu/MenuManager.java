package com.chochae.afes.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chochae.afes.menu.dto.MenuInfo;

@Service
public class MenuManager {
	private static Map<String, List<MenuInfo>> menuMap = new HashMap<String, List<MenuInfo>>();
	
	public static void initMenu() {
		List<MenuInfo> operatorList = new ArrayList<MenuInfo>();
		operatorList.add(new MenuInfo("offlist", "거래 처리 페이지", "/dealList.do"));
//		operatorList.add(new MenuInfo("offlist", "오퍼 관리", "/offerList.do"));
		operatorList.add(new MenuInfo("assetSnapshot", "자산 기록", "/assetSnapshot.do"));
		menuMap.put("OPERATOR", operatorList);
		
		List<MenuInfo> managerList = new ArrayList<MenuInfo>();
		
		managerList.add(new MenuInfo("currency", "환율 관리", "/currency.do"));
//		managerList.add(new MenuInfo("operatorAmount", "오퍼 관리", "/operatorAmounts.do"));
		managerList.add(new MenuInfo("marketList", "거래소 설정", "/markets.do"));
		managerList.add(new MenuInfo("idlist", "거래소 ID 설정", "/idmanage.do"));
//		managerList.add(new MenuInfo("userList", "사용자 관리", "/userList.do"));
//		managerList.add(new MenuInfo("operatorList", "오퍼레이터 배정", "/operatorList.do"));
//		managerList.add(new MenuInfo("properties", "속성 설정", "/properties.do"));
//		managerList.add(new MenuInfo("assetManage", "자원분배", "/assetManage.do"));
		
		menuMap.put("MANAGER", managerList);
		
		
		List<MenuInfo> adminList = new ArrayList<MenuInfo>();
		
		menuMap.put("ADMIN", managerList);
	}

	public static List<MenuInfo> getMenuByUser(String userType) {
		List<MenuInfo> menuList = menuMap.get(userType);
		return Collections.unmodifiableList(menuList);
	}
}
