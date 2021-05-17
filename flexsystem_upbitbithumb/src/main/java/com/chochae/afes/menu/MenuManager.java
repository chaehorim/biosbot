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
		operatorList.add(new MenuInfo("offlist", "�ŷ� ó�� ������", "/dealList.do"));
//		operatorList.add(new MenuInfo("offlist", "���� ����", "/offerList.do"));
		operatorList.add(new MenuInfo("assetSnapshot", "�ڻ� ���", "/assetSnapshot.do"));
		menuMap.put("OPERATOR", operatorList);
		
		List<MenuInfo> managerList = new ArrayList<MenuInfo>();
		
		managerList.add(new MenuInfo("currency", "ȯ�� ����", "/currency.do"));
//		managerList.add(new MenuInfo("operatorAmount", "���� ����", "/operatorAmounts.do"));
		managerList.add(new MenuInfo("marketList", "�ŷ��� ����", "/markets.do"));
		managerList.add(new MenuInfo("idlist", "�ŷ��� ID ����", "/idmanage.do"));
//		managerList.add(new MenuInfo("userList", "����� ����", "/userList.do"));
//		managerList.add(new MenuInfo("operatorList", "���۷����� ����", "/operatorList.do"));
//		managerList.add(new MenuInfo("properties", "�Ӽ� ����", "/properties.do"));
//		managerList.add(new MenuInfo("assetManage", "�ڿ��й�", "/assetManage.do"));
		
		menuMap.put("MANAGER", managerList);
		
		
		List<MenuInfo> adminList = new ArrayList<MenuInfo>();
		
		menuMap.put("ADMIN", managerList);
	}

	public static List<MenuInfo> getMenuByUser(String userType) {
		List<MenuInfo> menuList = menuMap.get(userType);
		return Collections.unmodifiableList(menuList);
	}
}
