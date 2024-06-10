package com.github.lorenzosmc.common.dto.workgroup.outbound;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class WorkgroupDto {
	private UUID uuid;

	private Instant creationDate;

	private UUID creatorUuid;

	private UUID contextUuid;

	private Boolean isVerified;

	private Boolean isHidden;

	private WorkgroupStatusDto status;

	private UUID taskUuid;

	private List<MembershipDto> participants;
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public UUID getCreatorUuid() {
		return creatorUuid;
	}

	public void setCreatorUuid(UUID creatorUuid) {
		this.creatorUuid = creatorUuid;
	}

	public UUID getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(UUID contextUuid) {
		this.contextUuid = contextUuid;
	}

	public Boolean isVerified() {
		return isVerified;
	}

	public void setVerified(Boolean verified) {
		this.isVerified = verified;
	}

	public Boolean isHidden() {
		return isHidden;
	}

	public void setHidden(Boolean visible) {
		this.isHidden = visible;
	}

	public WorkgroupStatusDto getStatus() {
		return status;
	}

	public void setStatus(WorkgroupStatusDto status) {
		this.status = status;
	}

	public UUID getTaskUuid() {
		return taskUuid;
	}

	public void setTaskUuid(UUID taskUuid) {
		this.taskUuid = taskUuid;
	}

	public List<MembershipDto> getParticipants() {
		return participants;
	}

	public void setParticipants(List<MembershipDto> participants) {
		this.participants = participants;
	}
}
