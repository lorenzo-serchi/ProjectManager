package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.common.dao.BaseDao;
import com.github.lorenzosmc.model.Participation;

public class ParticipationDaoImpl extends AbstractBaseDao<Participation> implements BaseDao<Participation> {

	public ParticipationDaoImpl() {
		super(Participation.class);
	}
}
