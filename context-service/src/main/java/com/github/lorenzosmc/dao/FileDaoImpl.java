package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.File;

public class FileDaoImpl extends AbstractBaseDao<File> implements FileDao {

	public FileDaoImpl() {
		super(File.class);
	}
}
