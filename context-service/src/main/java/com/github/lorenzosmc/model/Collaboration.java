package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Collaboration extends BaseEntity {
	Instant startDate;
	
	Instant endDate;
	
	boolean active;

	// FIXME why is this here?
	String notes;

	@ManyToOne(optional = false)
	Participation collaborator;

	@ManyToOne(optional = false)
	Context context;

	@ManyToOne(optional = false)
	Task task;

	protected Collaboration(){}
	
	public Collaboration(UUID uuid) {
		super(uuid);
		startDate = Instant.now();
	}

	public Instant getStartDate() {
		return startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Participation getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Participation collaborator) {
		this.collaborator = collaborator;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
