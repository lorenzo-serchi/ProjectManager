package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Publisher;

public class PublisherDaoImpl extends AbstractBaseDao<Publisher> implements PublisherDao {

	public PublisherDaoImpl() {
		super( Publisher.class);
	}
}
