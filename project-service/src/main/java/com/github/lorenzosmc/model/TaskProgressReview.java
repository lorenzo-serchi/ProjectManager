package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.UUID;

@Entity
public class TaskProgressReview extends BaseEntity {
	@NotBlank(message = "message cannot be blank")
	private String message;

	@ManyToOne
	private Participant requestor;
	
	@ManyToOne
	private Participant reviewer;

	@PastOrPresent(message = "creation date must be a past or present date")
	@NotNull
	private Instant creationDate;

	//TODO override equals() and hashCode()

	protected TaskProgressReview(){}
	
	public TaskProgressReview(UUID uuid, String message, Participant requestor, Participant reviewer) {
		super(uuid);
		this.message = message;
		this.requestor = requestor;
		this.reviewer = reviewer;
		this.creationDate = Instant.now();
	}

	public String getMessage() {
		return message;
	}

	public Participant getRequestor() {
		return requestor;
	}

	public Participant getReviewer() {
		return reviewer;
	}

	public Instant getCreationDate() {
		return creationDate;
	}
}
