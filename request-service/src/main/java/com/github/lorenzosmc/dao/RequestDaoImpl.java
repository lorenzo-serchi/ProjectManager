package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Request;

public class RequestDaoImpl extends AbstractBaseDao<Request> implements RequestDao {

	public RequestDaoImpl() {
		super(Request.class);
	}

}
