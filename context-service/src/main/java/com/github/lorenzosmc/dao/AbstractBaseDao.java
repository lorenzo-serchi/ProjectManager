package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.common.dao.BaseDao;
import com.github.lorenzosmc.common.model.BaseEntity;
import com.github.lorenzosmc.common.model.BaseEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.UUID;

public abstract class AbstractBaseDao<T extends BaseEntity> implements BaseDao<T> {
	private final Class<T> entityClass;

	@PersistenceContext(unitName = "CONTEXT_SERVICE_MYSQL")
	protected EntityManager entityManager;

	public AbstractBaseDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public final void save(T entity) {
		if (entity.getId() != null)
			entityManager.merge(entity);
		else
			entityManager.persist(entity);
	}

	@Override
	public final T findById(Long id) {
		return entityManager.find(entityClass, id);
	}
	
	@Override
	public final T findByUuid(UUID uuid) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get(BaseEntity_.uuid), uuid));

		T entity = null;
		try {
			entity = entityManager.createQuery(criteriaQuery).getSingleResult();
		}catch(NoResultException e) {}
		
		return entity;
	}

	@Override
	public final List<T> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	@Override
	public final void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public final void deleteById(Long id) {
		T entity = findById(id);
		delete(entity);
	}

	@Override
	public final void delete(T entity) {
		T managedEntity = entityManager.contains(entity) ? entity : entityManager.merge(entity);

		entityManager.remove(managedEntity);
	}
}
