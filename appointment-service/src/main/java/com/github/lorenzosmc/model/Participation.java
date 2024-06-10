package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Participation extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private Role role;

	private boolean mandatory;

	@ManyToOne(optional = false)
	private Appointment appointment;

	@ManyToOne(optional = false)
	private User participant;

	// TODO override equals() and hashCode()

	protected Participation(){}
	
	public Participation(UUID uuid) {
		super(uuid);
	}

	public Role getRole() {
		//FIXME defensive copy
		return role;
	}

	public void setRole(Role role) {
		//FIXME defensive copy
		this.role = role;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}
}
