package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Participation extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private Role role;

	private int maxCollaborations;

	private Instant joinDate;

	@ManyToOne(optional = false)
	private User participant;

	@ManyToOne(optional = false)
	private Context context;

	@OneToMany(mappedBy = Collaboration_.COLLABORATOR, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Collaboration> collaborations;

	protected Participation() {}

	public Participation(UUID uuid) {
		super(uuid);
		joinDate = Instant.now();
		collaborations = new HashSet<>();
	}

	public void addCollaboration (Collaboration collaboration) {
		collaborations.add(collaboration);
	}

	public boolean removeCollaboration (Collaboration collaboration) {
		return collaborations.remove(collaboration);
	}

	public Role getRole() {
		// FIXME defensive copy
		return role;
	}

	public void setRole(Role role) {
		// FIXME defensive copy
		this.role = role;
	}

	public int getMaxCollaborations() {
		return maxCollaborations;
	}

	public void setMaxCollaborations(int maxCollaborations) {
		// TODO check >= 0
		this.maxCollaborations = maxCollaborations;
	}

	public Instant getJoinDate() {
		return joinDate;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Set<Collaboration> getCollaborations() {
		return collaborations;
	}

	public void setCollaborations(Set<Collaboration> collaborations) {
		this.collaborations = collaborations;
	}
}
