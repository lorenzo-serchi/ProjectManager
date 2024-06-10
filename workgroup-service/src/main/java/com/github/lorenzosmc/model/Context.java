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
	@OneToMany(mappedBy = Workgroup_.CONTEXT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Workgroup> workgroups = new HashSet<>();
	
	// TODO override equals() and hashCode()

	protected Context() {}
	
	public Context(UUID uuid) {
		super(uuid);
	}

	public boolean addWorkgroup (Workgroup workgroup) {
	    return workgroups.add(workgroup);
	}
		
	public boolean removeWorkgroup (Workgroup workgroup) {
		return workgroups.remove(workgroup);
	}
	
	public Set<Workgroup> getWorkgroups() {
		return workgroups;
	}

	public void setWorkgroups(Set<Workgroup> workgroups) {
		this.workgroups = workgroups;
	}

}
