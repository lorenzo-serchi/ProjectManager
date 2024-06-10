package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Topic;

public class TopicDaoImpl extends AbstractBaseDao<Topic> implements TopicDao {

	public TopicDaoImpl() {
		super(Topic.class);
	}

}
