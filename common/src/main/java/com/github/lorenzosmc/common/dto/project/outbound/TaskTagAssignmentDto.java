package com.github.lorenzosmc.common.dto.project.outbound;

import java.util.UUID;

public class TaskTagAssignmentDto {
	private UUID tagUuid;

	private String tagName;
	
	private Long assignmentDate;
	
	private UUID assignerUuid;

	public UUID getTagUuid() {
		return tagUuid;
	}

	public void setTagUuid(UUID tagUuid) {
		this.tagUuid = tagUuid;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Long getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Long assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public UUID getAssignerUuid() {
		return assignerUuid;
	}

	public void setAssignerUuid(UUID assignerUuid) {
		this.assignerUuid = assignerUuid;
	}
}
