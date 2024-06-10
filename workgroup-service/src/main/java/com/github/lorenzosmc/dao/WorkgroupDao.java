package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.common.dao.BaseDao;
import com.github.lorenzosmc.model.Membership;
import com.github.lorenzosmc.model.Workgroup;

import java.util.List;
import java.util.UUID;

public interface WorkgroupDao extends BaseDao<Workgroup> {
	public List<Membership> findAllParticipations(UUID workgroupUuid);
	public List<Workgroup> findAll(WorkgroupQuery query);
	//TODO continue... depends on what Service Layer needs.
}
