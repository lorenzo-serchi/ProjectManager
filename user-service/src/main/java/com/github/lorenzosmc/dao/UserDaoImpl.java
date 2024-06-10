package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.User;
import com.github.lorenzosmc.model.User_;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	//FIXME Criteria and Metamodel API 
	@Override
	public User findByUsername(String username) {
		User userEntity;

		// FIXME exception handling for the numerous exceptions this code can produce
		try {
			userEntity = entityManager
					.createQuery("SELECT u" + " FROM User u" + " WHERE u.username = :username", User.class)
					.setParameter("username", username)
					.getSingleResult();
		} catch (NoResultException e) {
			userEntity = null;
		}

		return userEntity;
	}

	public List<User> findAll(UserQuery query) {
		if (query == null) 
			return findAll();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> user = criteriaQuery.from(User.class);
		criteriaQuery.select(user);
		
		Predicate predicate = criteriaBuilder.conjunction();
		Predicate tmp;
		if(query.getName() != null) {
			tmp = criteriaBuilder.equal(user.get(User_.name), query.getName());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getSurname() != null) {
			tmp = criteriaBuilder.equal(user.get(User_.surname), query.getSurname());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getVerified() != null) {
			tmp = criteriaBuilder.equal(user.get(User_.verified), query.getVerified());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getCreatedBefore() != null) {
			tmp = criteriaBuilder.lessThan(user.get(User_.creationDate), query.getCreatedBefore());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getCreatedAfter() != null) {
			tmp = criteriaBuilder.greaterThan(user.get(User_.creationDate), query.getCreatedAfter());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getRole() != null) {
			tmp = criteriaBuilder.equal(user.get(User_.role), query.getRole());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		criteriaQuery.where(predicate);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	
	
}