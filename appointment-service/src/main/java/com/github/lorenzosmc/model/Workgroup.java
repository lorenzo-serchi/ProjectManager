package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Workgroup extends BaseEntity {
	protected Workgroup(){}
	
	public Workgroup(UUID uuid) {
		super(uuid);
	}

}
