package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Context extends BaseEntity {
	@OneToMany(mappedBy = Appointment_.CONTEXT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Appointment> appointments;
	
	// TODO override equals() and hashCode()

	protected Context(){}
	
	public Context(UUID uuid) {
		super(uuid);
		appointments = new HashSet<>();
	}

	public boolean addAppointment(Appointment appointment) {
		return appointments.add(appointment);
	}
	
	public boolean removeAppointment(Appointment appointment) {
		return appointments.remove(appointment);
	}
	
	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

}
