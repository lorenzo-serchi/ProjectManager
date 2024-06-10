package com.github.lorenzosmc.model;

import java.time.Instant;
import java.util.UUID;

import com.github.lorenzosmc.common.model.BaseEntity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Update extends BaseEntity {
	private String updateMessage;
	
	private Instant creationDate;

	@Embedded
	UpdateCategory category;
	
	@ManyToOne(optional = false)
	private Resource resource;
	
	//TODO override equals() and hashCode()

	protected Update(){}
	
	public Update(UUID uuid) {
		super(uuid);
	}

	public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public UpdateCategory getCategory() {
		//FIXME defensive-copy
		return category;
	}

	public void setCategory(UpdateCategory category) {
		//FIXME defensive-copy
		this.category = category;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
