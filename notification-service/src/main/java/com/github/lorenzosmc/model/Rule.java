package com.github.lorenzosmc.model;

import java.time.Instant;
import java.util.UUID;

import com.github.lorenzosmc.common.model.BaseEntity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Rule extends BaseEntity {
	private RuleScope scope;
	
	private String contextUuid;
	
	private String taskUuid;
	
	private TopicUrgency minimumUrgency;
	
	private Instant creationDate;
	
	@Embedded
	private UpdateCategory category;
	
	// TODO override equals() and hashCode()

	protected Rule(){}
	
	public Rule(UUID uuid) {
		super(uuid);
	}
	
	public boolean apply(Update update) {
		Resource resource = update.getResource();

		//check applicability of rule
		boolean ruleApplies = false;
		boolean ruleAppliesToContext, ruleAppliesToTask = false;
		if(scope == RuleScope.CONTEXT) {
			ruleAppliesToContext = (contextUuid == resource.getContextUuid());
			ruleApplies = ruleAppliesToContext;
		}else if(scope == RuleScope.TASK) {
			ruleAppliesToContext = (contextUuid == resource.getContextUuid());
			ruleAppliesToTask = (taskUuid.equals(resource.getTaskUuid()));
			ruleApplies = ruleAppliesToContext && ruleAppliesToTask;
		}
		
		//apply rule
		boolean result = false;
		if(ruleApplies) {
			TopicUrgency urgency = resource.getTopicUrgency();
			if(urgency != null)
				result = (urgency.compareTo(minimumUrgency) >= 0);
		}		
		
		return result;
	}

	public RuleScope getScope() {
		//FIXME defensive copy
		return scope;
	}

	public void setScope(RuleScope scope) {
		//FIXME defensive copy
		this.scope = scope;
	}

	public String getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(String contextUuid) {
		this.contextUuid = contextUuid;
	}

	public String getTaskUuid() {
		return taskUuid;
	}

	public void setTaskUuid(String taskUuid) {
		this.taskUuid = taskUuid;
	}

	public TopicUrgency getMinimumUrgency() {
		//FIXME defensive copy
		return minimumUrgency;
	}

	public void setMinimumUrgency(TopicUrgency minimumUrgency) {
		//FIXME defensive copy
		this.minimumUrgency = minimumUrgency;
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
}
