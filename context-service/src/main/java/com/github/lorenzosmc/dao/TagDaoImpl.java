package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Tag;

public class TagDaoImpl extends AbstractBaseDao<Tag> implements TagDao {

	public TagDaoImpl() {
		super(Tag.class);
	}
}
