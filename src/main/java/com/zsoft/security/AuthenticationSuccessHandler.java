package com.zsoft.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.zsoft.constants.Constants;
import com.zsoft.dao.entities.User;
import com.zsoft.service.IUserService;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException { 
		try {
			HttpSession session = request.getSession();
			String username = request.getParameter(User.USERNAME);
			User user = userService.getUserByUsername(username);
			session.setAttribute(Constants.USER_SESSION_KEY, user);
			
			String clientType = request.getParameter(Constants.CLIENT_TYPE_KEY);
			
			if (Constants.MOBILE_CLIENT.equals(clientType)) {
				response.sendRedirect(Constants.DEFAULT_MOBILE_ACTION);
			} else {
				response.sendRedirect(Constants.DEFAULT_WEB_ACTION);
			}
			
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return;
		}
	}
	
}
