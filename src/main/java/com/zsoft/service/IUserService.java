package com.zsoft.service;

import com.zsoft.dao.entities.User;

public interface IUserService {

	public User getUserByUsername(String username);
	
	public Integer saveUser(User user);
	
	public void editUser(User user);
	
}
