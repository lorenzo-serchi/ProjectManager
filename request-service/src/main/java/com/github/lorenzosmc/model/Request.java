package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Request extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@NotNull
	private RequestType type;

	private String message;

	@Enumerated(EnumType.STRING)
	@NotNull
	private RequestStatus status;

	private String reasonOfDenial;

	@NotNull
	private Instant creationDate;

	@NotNull
	private UUID contextUuid;

	private UUID checkpointUUID;

	@OneToMany(mappedBy = Participant_.REQUEST, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Participant> participants = new HashSet<>();

	@Embedded
	@AttributeOverride(name = MeetingProposal_.WITH_COLLABORATORS, column = @Column(name = "meeting_with_collaborators"))
	private MeetingProposal meetingProposal;

	@Embedded
	@AttributeOverride(name = TaskProposal_.WITH_COLLABORATORS, column = @Column(name = "task_with_collaborators"))
	private TaskProposal taskProposal;

	// TODO override equals() and hashCode()

	protected Request() {}

	public Request(UUID uuid) {
		super(uuid);
		creationDate = Instant.now();
	}
	
	public boolean addParticipation (Participant participant) {
	    return participants.add(participant);
	}
		
	public boolean removeParticipation (Participant participant) {
		return participants.remove(participant);
	}

	public RequestType getType() {
		//FIXME defensive-copy
		return type;
	}

	public void setType(RequestType type) {
		//FIXME defensive-copy
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RequestStatus getStatus() {
		//FIXME defensive-copy
		return status;
	}

	public void setStatus(RequestStatus status) {
		//FIXME defensive-copy
		this.status = status;
	}

	public String getReasonOfDenial() {
		return reasonOfDenial;
	}

	public void setReasonOfDenial(String reasonOfDenial) {
		this.reasonOfDenial = reasonOfDenial;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public UUID getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(UUID contextUuid) {
		this.contextUuid = contextUuid;
	}

	public UUID getCheckpointUUID() {
		return checkpointUUID;
	}

	public void setCheckpointUUID(UUID checkpointUUID) {
		this.checkpointUUID = checkpointUUID;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public MeetingProposal getMeetingProposal() {
		return meetingProposal;
	}

	public void setMeetingProposal(MeetingProposal meetingProposal) {
		this.meetingProposal = meetingProposal;
	}

	public TaskProposal getTaskProposal() {
		return taskProposal;
	}

	public void setTaskProposal(TaskProposal taskProposal) {
		this.taskProposal = taskProposal;
	}
}
