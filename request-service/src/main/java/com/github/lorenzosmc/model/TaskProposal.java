package com.github.lorenzosmc.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Embeddable
public class TaskProposal {
	private boolean withCollaborators;

	@NotNull
	private UUID proposedTaskUuid;
	
	// TODO override equals() and hashCode()

	public boolean isWithCollaborators() {
		return withCollaborators;
	}

	public void setWithCollaborators(boolean withCollaborators) {
		this.withCollaborators = withCollaborators;
	}

	public UUID getProposedTaskUuid() {
		return proposedTaskUuid;
	}

	public void setProposedTaskUuid(UUID proposedTaskUuid) {
		this.proposedTaskUuid = proposedTaskUuid;
	}

}
