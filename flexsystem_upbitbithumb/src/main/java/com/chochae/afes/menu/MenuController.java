package com.chochae.afes.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chochae.afes.menu.dto.MenuInfo;
import com.chochae.afes.user.UserManager;
import com.chochae.afes.user.model.LoginForm;
@Controller
public class MenuController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	public @ResponseBody List<MenuInfo> getMenuList(HttpServletRequest request, ModelMap model) {
		LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
		String userId = userData.getUsername();
		
		String userType = UserManager.getUserType(userId);
		List<MenuInfo> menuList = MenuManager.getMenuByUser(userType);
		
		return menuList;
	}
}
