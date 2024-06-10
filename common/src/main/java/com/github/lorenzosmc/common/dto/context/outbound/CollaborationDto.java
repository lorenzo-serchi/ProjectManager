package com.github.lorenzosmc.common.dto.context.outbound;

import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;

import java.time.Instant;
import java.util.UUID;

public class CollaborationDto {
	private UUID uuid;

	private UUID collaboratorUuid;
	
	private UUID contextUuid;
	
	private Instant startDate;
	
	private TaskDto task;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getCollaboratorUuid() {
		return collaboratorUuid;
	}

	public void setCollaboratorUuid(UUID collaboratorUuid) {
		this.collaboratorUuid = contextUuid;
	}

	public UUID getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(UUID contextUuid) {
		this.contextUuid = contextUuid;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public TaskDto getTask() {
		return task;
	}

	public void setTask(TaskDto task) {
		this.task = task;
	}
}
