package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.common.dao.BaseDao;
import com.github.lorenzosmc.model.TagAssignment;

public class TagAssignmentDaoImpl extends AbstractBaseDao<TagAssignment> implements BaseDao<TagAssignment> {

	public TagAssignmentDaoImpl() {
		super(TagAssignment.class);
	}
}
