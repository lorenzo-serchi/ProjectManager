package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Task;

public class TaskDaoImpl extends AbstractBaseDao<Task> implements TaskDao {

	public TaskDaoImpl() {
		super(Task.class);
	}


}
