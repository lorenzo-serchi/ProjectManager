package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class User extends BaseEntity {
	@OneToMany(mappedBy = Participation_.PARTICIPANT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Participation> participations;

	@OneToMany(mappedBy = Appointment_.CREATOR, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Appointment> createdAppointments;

	// TODO override equals() and hashCode()

	protected User(){}
	
	public User(UUID uuid) {
		super(uuid);
		participations = new HashSet<>();
		createdAppointments = new HashSet<>();
	}

	public boolean addParticipation(Participation participation) {
		return participations.add(participation);
	}
		
	public boolean removeParticipation(Participation participation) {
		return participations.remove(participation);
	}
	
	public boolean addCreatedAppointment(Appointment appointment) {
		return createdAppointments.add(appointment);
	}
		
	public boolean removeCreatedAppointment(Appointment appointment) {
		return createdAppointments.remove(appointment);
	}
	
	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

	public Set<Appointment> getCreatedAppointments() {
		return createdAppointments;
	}

	public void setCreatedAppointments(Set<Appointment> createdAppointments) {
		this.createdAppointments = createdAppointments;
	}

}
