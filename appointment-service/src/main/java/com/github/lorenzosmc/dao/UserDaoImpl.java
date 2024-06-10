package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.User;

public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

}
