package com.github.lorenzosmc.common.dto.project.outbound;

import java.util.Set;
import java.util.UUID;

public class TaskDto {
	private UUID uuid;
	
	private String name;
	
	private String statement;
	
	private UUID contextUuid;
	
	private UUID creatorUuid;
	
	private Long creationDate;
	
	private Boolean visible;
	
	private String status;
	
	//FIXME move to gateway DTO (info is in workgroup service)
//	private int progress;
	
	private Set<TaskTagAssignmentDto> tagAssignments;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public UUID getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(UUID contextUuid) {
		this.contextUuid = contextUuid;
	}

	public UUID getCreatorUuid() {
		return creatorUuid;
	}

	public void setCreatorUuid(UUID creatorUuid) {
		this.creatorUuid = creatorUuid;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public int getProgress() {
//		return progress;
//	}
//
//	public void setProgress(int progress) {
//		this.progress = progress;
//	}

	public Set<TaskTagAssignmentDto> getTagAssignments() {
		return tagAssignments;
	}

	public void setTagAssignments(Set<TaskTagAssignmentDto> tagAssignments) {
		this.tagAssignments = tagAssignments;
	}
}
