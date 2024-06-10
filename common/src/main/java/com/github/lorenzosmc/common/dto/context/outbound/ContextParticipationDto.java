package com.github.lorenzosmc.common.dto.context.outbound;

public class ContextParticipationDto {
	private String uuid;
	
	private Long joinDate;
	
	private String participantUuid;
	
	private String contextUuid;
	
	private int maxCollaborations;

	public Long getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Long joinDate) {
		this.joinDate = joinDate;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getParticipantUuid() {
		return participantUuid;
	}

	public void setParticipantUuid(String participantUuid) {
		this.participantUuid = participantUuid;
	}

	public String getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(String contextUuid) {
		this.contextUuid = contextUuid;
	}

	public int getMaxCollaborations() {
		return maxCollaborations;
	}

	public void setMaxCollaborations(int maxCollaborations) {
		this.maxCollaborations = maxCollaborations;
	}
}
