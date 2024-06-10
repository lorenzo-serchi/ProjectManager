package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Membership;

public class MembershipDaoImpl extends AbstractBaseDao<Membership> implements MembershipDao {

	public MembershipDaoImpl() {
		super(Membership.class);
	}

}
