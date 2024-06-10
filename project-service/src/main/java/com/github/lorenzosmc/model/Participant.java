package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Participant extends BaseEntity {
	@ManyToOne(optional = false)
	private User participantInfo;

	@ManyToOne(optional = false)
	private Task task;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "role cannot be null")
	private Role role;

	@PastOrPresent(message = "join date must be a past or present date")
	@NotNull
	private Instant joinDate;

	@ManyToMany(mappedBy = Job_.PARTICIPANTS)
	private Set<Job> jobs;

	// TODO override equals() and hashCode()

	protected Participant() {}
	
	public Participant(UUID uuid, User user, Task task, Role role) {
		super(uuid);
		this.participantInfo = user;
		this.task = task;
		this.role = Role.valueOf(role.name());
		this.joinDate = Instant.now();
		this.jobs = new HashSet<>();
	}

	public boolean addJob(Job job){
		return jobs.add(job);
	}

	public boolean removeJob(Job job){
		return jobs.remove(job);
	}

	public String getFullName() {
		return participantInfo.getFullName();
	}

	public Task getTask() {
		return task;
	}

	public Role getRole() {
		return Role.valueOf(role.name());
	}

	public Instant getJoinDate() {
		return joinDate;
	}

	public Set<Job> getJobs() {
		return Set.copyOf(jobs);
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = new HashSet<>(jobs);
	}
}
