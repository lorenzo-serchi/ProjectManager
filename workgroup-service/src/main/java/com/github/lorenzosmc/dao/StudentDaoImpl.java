package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Student;

public class StudentDaoImpl extends AbstractBaseDao<Student> implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}

}
