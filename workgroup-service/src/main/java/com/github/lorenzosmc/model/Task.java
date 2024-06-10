package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class Task extends BaseEntity {
	@OneToOne
	private Workgroup workgroup;

	// TODO override equals() and hashCode()

	protected Task() {}
	
	public Task(UUID uuid) {
		super(uuid);
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

}
