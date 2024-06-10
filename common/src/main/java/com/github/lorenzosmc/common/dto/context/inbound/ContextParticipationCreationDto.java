package com.github.lorenzosmc.common.dto.context.inbound;

public class ContextParticipationCreationDto {
	private String participantUuid;
	
	private Integer maxCollaborations;
	
	private String password;

	public String getParticipantUuid() {
		return participantUuid;
	}

	public void setParticipantUuid(String participantUuid) {
		this.participantUuid = participantUuid;
	}

	public Integer getMaxCollaborations() {
		return maxCollaborations;
	}

	public void setMaxCollaborations(Integer maxCollaborations) {
		this.maxCollaborations = maxCollaborations;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
