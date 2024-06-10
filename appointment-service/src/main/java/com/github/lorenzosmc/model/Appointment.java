package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Appointment extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private AppointmentType type;

	private Instant startDate;

	private Duration duration;

	private String location;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	private int examScore;

	private boolean examPassed;

	private String meetingTopic;

	private String meetingTopicDetailed;

	private Instant creationDate;

	@ManyToOne
	private Workgroup workgroup;
	
	@ManyToOne(optional = false)
	private User creator;

	@ManyToOne(optional = false)
	private Context context;

	@OneToMany(mappedBy = Participation_.APPOINTMENT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Participation> participants;

	// TODO override equals() and hashCode()

	protected Appointment(){}
	
	public Appointment(UUID uuid) {
		super(uuid);
		creationDate = Instant.now();
		participants= new HashSet<>();
	}

	public boolean isScheduled() {
		return status == AppointmentStatus.SCHEDULED;
	}

	public boolean isAvailable() {
		return status == AppointmentStatus.AVAILABLE;
	}

	public boolean isConcluded() {
		return status == AppointmentStatus.CONCLUDED;
	}
	
	public boolean schedule() {
		//TODO implement
		return false;
	}
	
	public boolean cancel() {
		//TODO implement
		return false;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public boolean setStartDate(Instant date) {
		if (isAvailable()) {
			this.startDate = date;
			return true;
		}
		return false;
	}

	public String getLocation() {
		return location;
	}

	public boolean setLocation(String location) {
		if (isAvailable()) {
			this.location = location;
			return true;
		}
		return false;
	}

	public boolean addParticipant(Participation participant) {
		return participants.add(participant);
	}

	public boolean removeParticipant(Participation participant) {
		return participants.remove(participant);
	}

	public AppointmentType getType() {
		//FIXME defensive copy
		return type;
	}

	public void setType(AppointmentType type) {
		//FIXME defensive copy
		this.type = type;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public AppointmentStatus getStatus() {
		//FIXME defensive copy
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		//FIXME defensive copy
		this.status = status;
	}

	public int getExamScore() {
		return examScore;
	}

	public void setExamScore(int examScore) {
		this.examScore = examScore;
	}

	public boolean isExamPassed() {
		return examPassed;
	}

	public void setExamPassed(boolean examPassed) {
		this.examPassed = examPassed;
	}

	public String getMeetingTopic() {
		return meetingTopic;
	}

	public void setMeetingTopic(String meetingTopic) {
		this.meetingTopic = meetingTopic;
	}

	public String getMeetingTopicDetailed() {
		return meetingTopicDetailed;
	}

	public void setMeetingTopicDetailed(String meetingTopicDetailed) {
		this.meetingTopicDetailed = meetingTopicDetailed;
	}

	public Instant getCreationDate() {
		return creationDate;
	}
	
	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Set<Participation> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participation> participants) {
		this.participants = participants;
	}
}
