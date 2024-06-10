package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.common.dao.BaseDao;
import com.github.lorenzosmc.model.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
	//TODO continue... depends on what Service Layer needs.
	public User findByUsername(String username);
	public List<User> findAll(UserQuery query);
}
