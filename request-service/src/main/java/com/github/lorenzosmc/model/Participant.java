package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Participant extends BaseEntity {
	@NotNull
	private UUID userUuid;

	@NotNull
	private UUID participantWorkgroupUuid;

	@ManyToOne(optional = false)
	private Request request;

	private boolean creator;

	// TODO override equals() and hashCode()

	protected Participant() {}

	public Participant(UUID uuid) {
		super(uuid);
	}

	public UUID getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(UUID userUuid) {
		this.userUuid = userUuid;
	}

	public UUID getParticipantWorkgroupUuid() { return participantWorkgroupUuid; }

	public void setParticipantWorkgroupUuid(UUID participantWorkgroupUuid) {
		this.participantWorkgroupUuid = participantWorkgroupUuid;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public boolean isCreator() {
		return creator;
	}

	public void setCreator(boolean creator) {
		this.creator = creator;
	}

}
