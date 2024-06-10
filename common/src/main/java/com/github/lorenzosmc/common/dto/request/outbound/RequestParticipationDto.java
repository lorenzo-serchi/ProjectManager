package com.github.lorenzosmc.common.dto.request.outbound;

import java.util.UUID;

public class RequestParticipationDto {
	private UUID uuid;
	
	private UUID requestUuid;
	
	private UUID participantUuid;
	
	private UUID participantWorkgroupUuid;
	
	private Boolean isCreator;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID id) {
		this.uuid = id;
	}

	public UUID getRequest() {
		return requestUuid;
	}

	public void setRequest(UUID requestUuid) {
		this.requestUuid = requestUuid;
	}

	public UUID getParticipantUuid() {
		return participantUuid;
	}

	public void setParticipantUuid(UUID participantUuid) {
		this.participantUuid = participantUuid;
	}

	public UUID getParticipantWorkgroupUuid() {
		return participantWorkgroupUuid;
	}

	public void setParticipantWorkgroupUuid(UUID participantWorkgroupUuid) {
		this.participantWorkgroupUuid = participantWorkgroupUuid;
	}

	public Boolean isCreator() {
		return isCreator;
	}

	public void setIsCreator(Boolean isCreator) {
		this.isCreator = isCreator;
	}
	
}
