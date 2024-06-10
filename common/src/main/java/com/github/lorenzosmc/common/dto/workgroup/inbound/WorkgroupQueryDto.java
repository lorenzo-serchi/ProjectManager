package com.github.lorenzosmc.common.dto.workgroup.inbound;

import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupStatusDto;
import jakarta.ws.rs.QueryParam;

import java.time.Instant;
import java.util.UUID;

public class WorkgroupQueryDto {
	@QueryParam("contextUuid")
	UUID contextUuid;

	@QueryParam("creatorUuid")
	UUID creatorUuid;
	
	@QueryParam("createdBefore")
	Instant createdBefore;

	@QueryParam("createdAfter")
	Instant createdAfter;

	@QueryParam("memberUuid")
	UUID memberUuid;

	@QueryParam("assignedTaskUuid")
	UUID assignedTaskUuid;

	@QueryParam("assignedBefore")
	Instant assignedBefore;

	@QueryParam("assignedAfter")
	Instant assignedAfter;
	
	@QueryParam("verified")
	Boolean verified;

	@QueryParam("hidden")
	Boolean hidden;

	@QueryParam("publishingConsent")
	Boolean publishingConsent;

	@QueryParam("status")
	WorkgroupStatusDto status;
	
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

	public Instant getCreatedBefore() {
		return createdBefore;
	}

	public void setCreatedBefore(Instant createdBefore) {
		this.createdBefore = createdBefore;
	}

	public Instant getCreatedAfter() {
		return createdAfter;
	}

	public void setCreatedAfter(Instant createdAfter) {
		this.createdAfter = createdAfter;
	}

	public UUID getMemberUuid() {
		return memberUuid;
	}

	public void setMemberUuid(UUID memberUuid) {
		this.memberUuid = memberUuid;
	}

	public UUID getAssignedTaskUuid() {
		return assignedTaskUuid;
	}

	public void setAssignedTaskUuid(UUID assignedTaskUuid) {
		this.assignedTaskUuid = assignedTaskUuid;
	}

	public Instant getAssignedBefore() {
		return assignedBefore;
	}

	public void setAssignedBefore(Instant assignedBefore) {
		this.assignedBefore = assignedBefore;
	}

	public Instant getAssignedAfter() {
		return assignedAfter;
	}

	public void setAssignedAfter(Instant assignedAfter) {
		this.assignedAfter = assignedAfter;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean visible) {
		this.hidden = visible;
	}

	public Boolean getPublishingConsent() {
		return publishingConsent;
	}

	public void setPublishingConsent(Boolean publishingConsent) {
		this.publishingConsent = publishingConsent;
	}

	public WorkgroupStatusDto getStatus() {
		return status;
	}

	public void setStatus(WorkgroupStatusDto status) {
		this.status = status;
	}

}
