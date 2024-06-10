package com.github.lorenzosmc.common.dto.request.outbound;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class RequestDto {
	private UUID uuid;
	
	private RequestTypeDto type;
	
	private UUID contextUuid;

	private List<RequestParticipationDto> participants;

	private Instant creationDate;

	private RequestStatusDto status;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID id) {
		this.uuid = id;
	}

	public RequestTypeDto getType() {
		return type;
	}

	public void setType(RequestTypeDto type) {
		this.type = type;
	}

	public UUID getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(UUID contextUuid) {
		this.contextUuid = contextUuid;
	}

	public List<RequestParticipationDto> getParticipants() {
		return participants;
	}

	public void setParticipants(List<RequestParticipationDto> participants) {
		this.participants = participants;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public RequestStatusDto getStatus() {
		return status;
	}

	public void setStatus(RequestStatusDto status) {
		this.status = status;
	}
	
}
