package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Appointment extends BaseEntity {

	protected Appointment() {}
	
	public Appointment(UUID uuid) {
		super(uuid);
	}
}
