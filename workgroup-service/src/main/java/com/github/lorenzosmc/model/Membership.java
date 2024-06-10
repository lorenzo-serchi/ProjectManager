package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Membership extends BaseEntity {
	private boolean leader;

	private boolean publishingConsent;
	
	@PastOrPresent(message = "joinDate must be a past or present date")
	@NotNull
	private Instant joinDate;
	
	@ManyToOne(optional = false)
	private Student member;

	@ManyToOne(optional = false)
	private Workgroup workgroup;

	// TODO override equals() and hashCode()

	protected Membership() {}
	
	public Membership(UUID uuid) {
		super(uuid);
		joinDate = Instant.now();
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean isLeader) {
		this.leader = isLeader;
	}

	public Student getMember() {
		return member;
	}

	public void setMember(Student participant) {
		this.member = participant;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public boolean getPublishingConsent() {
		return publishingConsent;
	}

	public void setPublishingConsent(boolean publishingConsent) {
		this.publishingConsent = publishingConsent;
	}

	public Instant getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Instant joinDate) {
		this.joinDate = joinDate;
	}
}
