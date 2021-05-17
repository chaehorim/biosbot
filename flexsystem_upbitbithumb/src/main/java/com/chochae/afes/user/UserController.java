package com.chochae.afes.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chochae.afes.menu.MenuManager;
import com.chochae.afes.menu.dto.MenuInfo;
import com.chochae.afes.service.DbLoader;
import com.chochae.afes.user.dao.UserDAO;
import com.chochae.afes.user.model.LoginForm;
import com.chochae.afes.user.model.UserInfo;

/**
 * Handles requests for the application home page. TEST
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLogin(Model model, LoginForm loginForm) {
		logger.info("Login page");
		model.addAttribute("loginAttribute", loginForm);
		return "login";
	}
	
	@RequestMapping(value = "/login.failed", method = RequestMethod.GET)
	public String showLoginfailed(Model model, LoginForm loginForm) {
		logger.info("Login page");
		model.addAttribute("loginAttribute", loginForm);
		return "login";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(Model model, LoginForm loginform, Locale locale, HttpServletRequest request) throws Exception {
 
		String username = loginform.getUsername();
		String password = loginform.getPassword();
 
		// A simple authentication manager
		if(username != null && password != null){
			String url = "";
			if( UserManager.checkUserAuth(username, password)){
				request.getSession().setAttribute("LOGGEDIN_USER", loginform);
				String userType = UserManager.getUserType(username);
				List<MenuInfo> menuList = MenuManager.getMenuByUser(userType);
				if (menuList.size() > 0) {
					url = menuList.get(0).getUrl();
				}
			}else{
				return "redirect:/";
			}
			return "redirect:" + url;
		}else{
			return "/user/login";
		}	

	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(Model model, LoginForm loginform, Locale locale, HttpServletRequest request) throws Exception {
		
		request.getSession().removeAttribute("LOGGEDIN_USER");
 
		return "redirect:/";

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegister(Locale locale, Model model) {
		logger.debug("[USER REGISTER] starting.", locale);
		
		return "/user/register";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.GET)
	public @ResponseBody String registerProcess(HttpServletRequest request, ModelMap model) {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		
		UserInfo user = new UserInfo(id, name, password, phone, address, 82, email);
		logger.info("[SAVE USER] starting.", user);
		
		
		ApplicationContext context =
        		new ClassPathXmlApplicationContext("Spring-Module.xml");

        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        userDAO.insertUser(user);
        
		return "";
	}
	
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> checkUser(HttpServletRequest request, ModelMap model) {
		
		String id = request.getParameter("id");
		
		logger.info("[CHECK USER] starting.", id);
		
		ApplicationContext context =
        		new ClassPathXmlApplicationContext("Spring-Module.xml");

        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        boolean existYn = userDAO.userExistYn(id);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("existYn", existYn? "Y" : "N");
		return result;
	}
	
	/* FOR ADMIN USER LEVEL CHANGE*/
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String getUserList(Locale locale, Model model) {
		logger.debug("[USER LIST] starting.", locale);
		return "/user/userList";
	}
	
	@RequestMapping(value = "/userDatas", method = RequestMethod.GET)
	public @ResponseBody List<UserInfo> selectUserDatas(HttpServletRequest request, ModelMap model) {
		String id = request.getParameter("userId");
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		List<UserInfo> userList = userDAO.selectUserList(id);
		
		return userList;
	}
	
	@RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
	public @ResponseBody String updateUser(HttpServletRequest request, ModelMap model) {
		
		String id = request.getParameter("userId");
		String userType = request.getParameter("userType");
		
		UserInfo user = new UserInfo();
		user.setUserType(userType);
		user.setUserId(id);
		user.setTradeYn("N");
		if ("OPERATOR".equals(userType)) {
			user.setMinProfit(0.03f);
		} else {
			user.setMinProfit(0f);
		}
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		boolean successYn = userDAO.updateUserByAdmin(user);
		
		return "{}";
	}
	
	/* ADMIN MANAGER -> OPERATOR SELECT */
	@RequestMapping(value = "/operatorList", method = RequestMethod.GET)
	public String getManagerList(Locale locale, Model model) {
		logger.debug("[MANAGER LIST] starting.", locale);
		return "/user/operatorList";
	}
	
	@RequestMapping(value = "/managerDatas", method = RequestMethod.GET)
	public @ResponseBody List<UserInfo> selectManagerDatas(HttpServletRequest request, ModelMap model) {
		String id = request.getParameter("userId");
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		List<UserInfo> userList = userDAO.selectUserListByType("MANAGER");
		
		return userList;
	}
	
	@RequestMapping(value = "/managerOperatorDatas", method = RequestMethod.GET)
	public @ResponseBody List<UserInfo> selectOperatorByManagerDatas(HttpServletRequest request, ModelMap model) {
		String id = request.getParameter("parentId");
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		List<UserInfo> userList = userDAO.selectUserListByParentId(id);
		
		return userList;
	}
	
	@RequestMapping(value = "/operatorDatas", method = RequestMethod.GET)
	public @ResponseBody List<UserInfo> selectOperatorDatas(HttpServletRequest request, ModelMap model) {
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		List<UserInfo> userList = userDAO.selectUserListByType("OPERATOR");
		
		return userList;
	}
	
	@RequestMapping(value = "/updateOperatorManager", method = RequestMethod.POST)
	public @ResponseBody String updateOperatorManager(HttpServletRequest request, ModelMap model) {
		
		String parentId = request.getParameter("parentId");
		String ids = request.getParameter("operators");
		String[] userIds = ids.split(",");
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		boolean successYn = userDAO.updateManagerOperator(parentId, userIds);
		if (successYn) {
			UserManager.editOperator();
		}
		return "{}";
	}
	
	@RequestMapping(value = "/updateManagerAmount", method = RequestMethod.POST)
	public @ResponseBody String updateManagerAmount(HttpServletRequest request, ModelMap model) throws ParseException {
        String datas = request.getParameter("datas");
        List<String> userIdList = new ArrayList<String>();
        List<Float> amountList = new ArrayList<Float>();
        
        JSONParser jsonParser = new JSONParser();
        JSONArray dataArray = (JSONArray) jsonParser.parse(datas);
        for (int i = 0; i < dataArray.size();i ++) {
        	JSONObject data = (JSONObject) dataArray.get(i);
        	userIdList.add(data.get("userId").toString());
        	logger.info(" " + userIdList.get(i));
        	amountList.add(Float.parseFloat(data.get("amount").toString()));
        }
        
		
		UserDAO userDAO = DbLoader.getUserDbConnection();
		boolean successYn = userDAO.updateManagerAmount(userIdList, amountList);
		if (successYn) {
			UserManager.editOperator();
		}
		return "{}";
	}
	
	/* MANAGER -> OPERATOR SELECT */
	@RequestMapping(value = "/operatorAmounts", method = RequestMethod.GET)
	public String getOperatorAmountLists(Locale locale, Model model) {
		logger.debug("[operatorAmounts] starting.", locale);
		// FIXME: GET USERID
		String userId = "banba";
		UserDAO userDAO = DbLoader.getUserDbConnection();
		List<UserInfo> userList = userDAO.selectUserListByType("OPERATOR");
		ObjectMapper mapper = new ObjectMapper();
		try {
			String str = mapper.writeValueAsString(userList);
			model.addAttribute("users", str);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/user/operatorAmounts";
	}
	
}
