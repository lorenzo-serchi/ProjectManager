package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Participant;

public class ParticipationDaoImpl extends AbstractBaseDao<Participant> implements ParticipationDao {

	public ParticipationDaoImpl() {
		super(Participant.class);
	}

}
