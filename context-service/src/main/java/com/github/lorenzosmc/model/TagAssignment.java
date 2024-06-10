package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.Instant;
import java.util.UUID;

@Entity
public class TagAssignment extends BaseEntity {
	private Instant assignmentDate;

	@ManyToOne(optional = false)
	private Tag tag;

	@ManyToOne(optional = false)
	private User assigner;

	protected TagAssignment() {}

	public TagAssignment(UUID uuid) {
		super(uuid);
		assignmentDate = Instant.now();
	}

	public Instant getAssignmentDate() {
		return assignmentDate;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public User getAssigner() {
		return assigner;
	}

	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}
}
