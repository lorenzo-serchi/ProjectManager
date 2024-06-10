package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.AbstractResourceImpl;

public class ResourceDaoImpl extends AbstractBaseDao<AbstractResourceImpl> implements ResourceDao {

	public ResourceDaoImpl() {
		super(AbstractResourceImpl.class);
	}
}
