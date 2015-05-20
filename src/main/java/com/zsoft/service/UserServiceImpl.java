package com.zsoft.service;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.zsoft.dao.entities.User;
import com.zsoft.exception.ServiceException;

public class UserServiceImpl implements IUserService {

	private Dao<User, Integer> userDao;
	
	public void setUserDao(Dao<User, Integer> userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByUsername(String username) {
		try {
			QueryBuilder<User, Integer> builder = userDao.queryBuilder();
			builder.where().eq(User.USERNAME, username);
			return userDao.queryForFirst(builder.prepare());
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer saveUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void editUser(User user) {
		throw new UnsupportedOperationException();
	}

}
