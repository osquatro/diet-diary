package com.zsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zsoft.service.IUserService;


@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {
		return new ModelAndView("login");
	}
}
