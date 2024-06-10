package com.github.lorenzosmc.common.dao;

import com.github.lorenzosmc.common.model.BaseEntity;
import java.util.List;
import java.util.UUID;

public interface BaseDao<T extends BaseEntity>{
	public void save(T entity);
	
	public T findById(Long id);

	public T findByUuid(UUID uuid);
	
	public List<T> findAll();
	
	public void update(T entity);
	
	public void deleteById(Long id);
	
	public void delete(T entity);
}
