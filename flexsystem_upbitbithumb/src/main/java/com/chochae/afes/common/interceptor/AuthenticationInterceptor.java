package com.chochae.afes.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chochae.afes.user.model.LoginForm;

public class AuthenticationInterceptor implements HandlerInterceptor  {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
 
		// Avoid a redirect loop for some urls
		if( !request.getRequestURI().equals("/login.do") && !request.getRequestURI().equals("/register.do")
				 && !request.getRequestURI().equals("/saveUser.do"))
		  {
			  LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
		   if(userData == null)
		   {
		    response.sendRedirect("/login.do");
		    return false;
		   }   
		  }
		  return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
