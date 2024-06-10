package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Student extends BaseEntity {
	@OneToMany(mappedBy = Workgroup_.CREATOR, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Workgroup> createdWorkgroups = new HashSet<>();

	@OneToMany(mappedBy = Membership_.MEMBER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Membership> memberships = new HashSet<>();

	// TODO override equals() and hashCode()

	protected Student() {}

	public Student(UUID uuid) {
		super(uuid);
	}

	public boolean addCreatedWorkgroup (Workgroup workgroup) {
	    return createdWorkgroups.add(workgroup);
	}
		
	public boolean removeCreatedWorkgroup (Workgroup workgroup) {
		return createdWorkgroups.remove(workgroup);
	}
	
	public boolean addMembership (Membership membership) {
	    return memberships.add(membership);
	}
		
	public boolean removeMembership (Membership membership) {
		return memberships.remove(membership);
	}
	
	public Set<Workgroup> getCreatedWorkgroups() {
		return createdWorkgroups;
	}

	public void setCreatedWorkgroups(Set<Workgroup> createdWorkgroups) {
		this.createdWorkgroups = createdWorkgroups;
	}

	public Set<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<Membership> memberships) {
		this.memberships = memberships;
	}

}
