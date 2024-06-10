package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Membership;
import com.github.lorenzosmc.model.Membership_;
import com.github.lorenzosmc.model.Workgroup;
import com.github.lorenzosmc.model.Workgroup_;
import jakarta.persistence.criteria.*;

import java.util.List;
import java.util.UUID;

public class WorkgroupDaoImpl extends AbstractBaseDao<Workgroup> implements WorkgroupDao {

	public WorkgroupDaoImpl() {
		super(Workgroup.class);
	}

	public List<Workgroup> findAll(WorkgroupQuery query) {
		if (query == null) 
			return findAll();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Workgroup> criteriaQuery = criteriaBuilder.createQuery(Workgroup.class);
		Root<Workgroup> workgroup = criteriaQuery.from(Workgroup.class);
		Join<Workgroup, Membership> memberships = workgroup.join(Workgroup_.memberships);
		criteriaQuery.select(workgroup);
		
		Predicate predicate = criteriaBuilder.conjunction();
		Predicate tmp;
		if(query.getCreator() != null) {
			tmp = criteriaBuilder.equal(workgroup.get(Workgroup_.creator), query.getCreator());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getContext() != null) {
			tmp = criteriaBuilder.equal(workgroup.get(Workgroup_.context), query.getContext());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getCreatedBefore() != null) {
			tmp = criteriaBuilder.lessThan(workgroup.get(Workgroup_.creationDate), query.getCreatedBefore());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getCreatedAfter() != null) {
			tmp = criteriaBuilder.greaterThan(workgroup.get(Workgroup_.creationDate), query.getCreatedAfter());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getMember() != null) {
			tmp = criteriaBuilder.equal(memberships.get(Membership_.member), query.getMember());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getAssignedTask() != null) {
			tmp = criteriaBuilder.equal(workgroup.get(Workgroup_.task), query.getAssignedTask());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getAssignedBefore() != null) {
			tmp = criteriaBuilder.lessThan(workgroup.get(Workgroup_.assignmentDate), query.getAssignedBefore());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getAssignedAfter() != null) {
			tmp = criteriaBuilder.greaterThan(workgroup.get(Workgroup_.assignmentDate), query.getAssignedAfter());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getVerified() != null) {
			tmp = criteriaBuilder.equal(workgroup.get(Workgroup_.verified), query.getVerified());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getHidden() != null) {
			tmp = criteriaBuilder.equal(workgroup.get(Workgroup_.hidden), query.getHidden());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getPublishingConsent() != null) {
			tmp = criteriaBuilder.equal(workgroup.get(Workgroup_.publishingConsent), query.getPublishingConsent());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		if(query.getStatus() != null) {
			tmp = criteriaBuilder.equal(workgroup.get(Workgroup_.publishingConsent), query.getStatus());
			predicate = criteriaBuilder.and(predicate, tmp);
		}
		
		criteriaQuery.where(predicate);
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
		
		

	@Override
	public List<Membership> findAllParticipations(UUID workgroupUuid) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Membership> criteriaQuery = criteriaBuilder.createQuery(Membership.class);
		Root<Workgroup> workgroup = criteriaQuery.from(Workgroup.class);
		Join<Workgroup, Membership> memberships = workgroup.join(Workgroup_.memberships);
		
		criteriaQuery.select(memberships);
		criteriaQuery.where(criteriaBuilder.equal(workgroup.get(Workgroup_.uuid), workgroupUuid));
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
