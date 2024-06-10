package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Workgroup extends BaseEntity {
	private boolean verified;
	
	private boolean hidden;
	
	private boolean publishingConsent;

	@Enumerated(EnumType.STRING)
	@NotNull
	private WorkgroupStatus status;
	
	private Instant assignmentDate;
	
	@Min(value = 0, message = "progress must be greater than or equal to {value}")
	@Max(value = 100, message = "progress must be less than or equal to {value}")
	private int progress;
	
	@NotNull(message = "creation date must not be null")
	private Instant creationDate;
	
	@OneToOne
	private Task task;

	@OneToOne
	private Appointment exam;
	
	@ManyToOne(optional = false)
	private Context context;

	@ManyToOne(optional = false)
	private Student creator;
	
	@OneToMany(mappedBy = Membership_.WORKGROUP, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Membership> memberships  = new HashSet<>();
	
	//TODO override equals() and hashCode()
	
	protected Workgroup() {}
	
	public Workgroup(UUID uuid) {
		super(uuid);
		status = WorkgroupStatus.NOT_WORKING;
		creationDate = Instant.now();
	}
	
	public boolean addMembership (Membership membership) {
	    return memberships.add(membership);
	}
		
	public boolean removeMembership (Membership membership) {
		return memberships.remove(membership);
	}
	
	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isPublishingConsent() {
		return publishingConsent;
	}

	public void setPublishingConsent(boolean publishingConsent) {
		this.publishingConsent = publishingConsent;
	}

	public WorkgroupStatus getStatus() {
		//FIXME defensive-copy
		return status;
	}

	public void setStatus(WorkgroupStatus status) {
		//FIXME defensive-copy
		this.status = status;
	}

	public Instant getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Instant assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Appointment getExam() {
		return exam;
	}

	public void setExam(Appointment exam) {
		this.exam = exam;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Student getCreator() {
		return creator;
	}

	public void setCreator(Student creator) {
		this.creator = creator;
	}

	public Set<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<Membership> memberships) {
		this.memberships = memberships;
	}
}
