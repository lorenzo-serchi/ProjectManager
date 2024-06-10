package com.github.lorenzosmc.common.dto.appointment.outbound;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class AppointmentDto {
	private String uuid;
	
	private String type;

	private List<ParticipationDto> participants;
	
	private String contextUuid;

	private String creatorUuid;
	
	private Instant creationDate;
	
	private Instant startDate;
	
	private Duration duration;
	
	private String location;
	
	private String status;	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ParticipationDto> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipationDto> participants) {
		this.participants = participants;
	}

	public String getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(String contextId) {
		this.contextUuid = contextId;
	}

	public String getCreatorUuid() {
		return creatorUuid;
	}

	public void setCreatorUuid(String creatorId) {
		this.creatorUuid = creatorId;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
