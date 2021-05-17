package com.chochae.afes.menu.dto;

public class MenuInfo {
	
	private String		menuId;
	private String		menuName;
	private String		url;
	
	public MenuInfo(String menuId, String menuName, String url) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.url = url;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "MenuInfo [menuId=" + menuId + ", menuName=" + menuName + ", url=" + url + "]";
	}
	
}
