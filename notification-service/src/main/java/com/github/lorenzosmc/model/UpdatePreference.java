package com.github.lorenzosmc.model;

import java.time.Instant;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.github.lorenzosmc.common.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class UpdatePreference extends BaseEntity {
	boolean enabled;
	
	private Instant creationDate;
	
	@Embedded
	UpdateCategory category;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Rule> userRules;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Rule> contextRules;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Rule> taskRules;
	
	//TODO override equals() and hashCode()

	protected UpdatePreference(){}
	
	public UpdatePreference(UUID uuid) {
		super(uuid);
	}
	
	public boolean apply(Update update) {
		boolean result = false;
		
		if(!taskRules.isEmpty()) 
			result = applyRules(taskRules, update);
		
		if(result == false && !contextRules.isEmpty()) 
			result = applyRules(contextRules, update);
			
		if(result == false && !userRules.isEmpty()) 
			result = applyRules(userRules, update);
		
		return result;
	}

	private boolean applyRules(Set<Rule> rules, Update update) {
		boolean result = false;

		Iterator<Rule> iterator = rules.iterator();
		while(iterator.hasNext() && result == false) {
			Rule rule = iterator.next();
			result = result || rule.apply(update);
		}
		
		return result;
	}
		
	public void addRule(Rule rule) throws IllegalRuleException {
		UpdateCategory ruleCategory = rule.getCategory();
		if(!category.equals(ruleCategory))
			throw new IllegalRuleException(
					"cannot add rule for update of category (" + ruleCategory.toString() + ")"
					+ " to update preference for update of category (" + category.toString() + ")");
		
		if(rule.getScope() == RuleScope.USER)
			userRules.add(rule);
		else if(rule.getScope() == RuleScope.CONTEXT)
			contextRules.add(rule);
		else if(rule.getScope() == RuleScope.TASK)
			taskRules.add(rule);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public UpdateCategory getCategory() {
		//FIXME defensive-copy
		return category;
	}

	public void setCategory(UpdateCategory category) {
		//FIXME defensive-copy
		this.category = category;
	}

	public Set<Rule> getContextRules() {
		return contextRules;
	}

	public void setContextRules(Set<Rule> contextRules) {
		this.contextRules = contextRules;
	}

	public Set<Rule> getTaskRules() {
		return taskRules;
	}

	public void setTaskRules(Set<Rule> taskRules) {
		this.taskRules = taskRules;
	}

	public Set<Rule> getUserRules() {
		return userRules;
	}

	public void setUserRules(Set<Rule> userRules) {
		this.userRules = userRules;
	}

}
