package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Project;

public class ProjectDaoImpl extends AbstractBaseDao<Project> implements ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}
}
