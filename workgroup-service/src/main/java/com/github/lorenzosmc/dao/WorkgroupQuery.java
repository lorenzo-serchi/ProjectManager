package com.github.lorenzosmc.dao;

import com.github.lorenzosmc.model.Context;
import com.github.lorenzosmc.model.Student;
import com.github.lorenzosmc.model.Task;
import com.github.lorenzosmc.model.WorkgroupStatus;

import java.time.Instant;

public class WorkgroupQuery {
	Context context;
	
	Student creator;

	Instant createdBefore;
	
	Instant createdAfter;

	Student member;

	Task assignedTask;

	Instant assignedBefore;
	
	Instant assignedAfter;
	
	Boolean verified;

	Boolean hidden;

	Boolean publishingConsent;

	WorkgroupStatus status;
	
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

	public Instant getCreatedBefore() {
		return createdBefore;
	}

	public void setCreatedBefore(Instant createdBefore) {
		this.createdBefore = createdBefore;
	}

	public Instant getCreatedAfter() {
		return createdAfter;
	}

	public void setCreatedAfter(Instant createdAfter) {
		this.createdAfter = createdAfter;
	}

	public Student getMember() {
		return member;
	}

	public void setMember(Student participant) {
		this.member = participant;
	}

	public Task getAssignedTask() {
		return assignedTask;
	}

	public void setAssignedTask(Task assignedTask) {
		this.assignedTask = assignedTask;
	}

	public Instant getAssignedBefore() {
		return assignedBefore;
	}

	public void setAssignedBefore(Instant assignedBefore) {
		this.assignedBefore = assignedBefore;
	}

	public Instant getAssignedAfter() {
		return assignedAfter;
	}

	public void setAssignedAfter(Instant assignedAfter) {
		this.assignedAfter = assignedAfter;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean visible) {
		this.hidden = visible;
	}

	public Boolean getPublishingConsent() {
		return publishingConsent;
	}

	public void setPublishingConsent(Boolean publishingConsent) {
		this.publishingConsent = publishingConsent;
	}

	public WorkgroupStatus getStatus() {
		return status;
	}

	public void setStatus(WorkgroupStatus status) {
		this.status = status;
	}
}
