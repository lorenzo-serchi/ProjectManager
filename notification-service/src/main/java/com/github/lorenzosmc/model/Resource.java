package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;

import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Resource extends BaseEntity {
	private String contextUuid;
	
	private String taskUuid;
	
	private TopicUrgency topicUrgency;

	// TODO override equals() and hashCode()
	
	protected Resource(){}
	
	public Resource(UUID uuid) {
		super(uuid);
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

	public TopicUrgency getTopicUrgency() {
		//FIXME defensive copy
		return topicUrgency;
	}

	public void setTopicUrgency(TopicUrgency topicUrgency) {
		//FIXME defensive copy
		this.topicUrgency = topicUrgency;
	}

}
