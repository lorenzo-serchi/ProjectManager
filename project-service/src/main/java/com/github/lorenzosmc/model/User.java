package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class User extends BaseEntity {
	@NotBlank(message = "full name cannot be blank")
	private String fullName;
	
	@OneToMany(mappedBy = Project_.CREATOR, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Project> createdProjects;

	@OneToMany(mappedBy = Participant_.PARTICIPANT_INFO, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Participant> participations;

	// TODO override equals() and hashCode()

	protected User() {}

	public User(UUID uuid, String fullName) {
		super(uuid);
		this.fullName = fullName;
		this.createdProjects = new HashSet<>();
		this.participations = new HashSet<>();
	}

	public boolean addCreatedProject(Project project) {
		return createdProjects.add(project);
	}

	public boolean removeCreatedProject(Project project) {
		return createdProjects.remove(project);
	}

	public boolean addParticipation(Participant participant){
		return participations.add(participant);
	}

	public boolean removeParticipation(Participant participant){
		return participations.remove(participant);
	}

	public String getFullName() {
		return fullName;
	}

	// FIXME need to add setFullName() for when FullName is changed in user service... (need to establish if this is a
	// real possibility)

	public Set<Project> getCreatedProjects() {
		return Set.copyOf(createdProjects);
	}

	public void setCreatedProjects(Set<Project> createdProjects) {
		this.createdProjects = new HashSet<>(createdProjects);
	}

	public Set<Participant> getParticipations() {
		return Set.copyOf(participations);
	}

	public void setParticipations(Set<Participant> participations) {
		this.participations = new HashSet<>(participations);
	}
}
