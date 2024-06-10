package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User extends BaseEntity {
	private String fullName;

	@OneToMany(mappedBy = Participation_.PARTICIPANT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Participation> participations;

	@OneToMany(mappedBy = Context_.CREATOR, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Context> createdContexts;

	// TODO override equals() and hashCode()

	protected User(){}
	
	public User(UUID uuid) {
		super(uuid);
		participations = new ArrayList<>();
		createdContexts = new ArrayList<>();
	}

	public boolean addParticipation (Participation participation) {
		return participations.add(participation);
	}
		
	public boolean removeParticipation (Participation participation) {
		return participations.remove(participation);
	}
	
	public boolean addCreatedContext (Context context) {
		return createdContexts.add(context);
	}
		
	public boolean removeCreatedContext  (Context context) {
		return createdContexts.remove(context);
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public List<Context> getCreatedContexts() {
		return createdContexts;
	}

	public void setCreatedContexts(List<Context> createdContexts) {
		this.createdContexts = createdContexts;
	}

	
}
