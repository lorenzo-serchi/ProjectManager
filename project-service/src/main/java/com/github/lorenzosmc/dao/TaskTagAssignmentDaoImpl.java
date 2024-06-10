package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Tag;

public class TaskTagAssignmentDaoImpl extends AbstractBaseDao<Tag> implements TaskTagAssignmentDao {

	public TaskTagAssignmentDaoImpl() {
		super(Tag.class);
	}
}
