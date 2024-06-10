package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class Notification extends BaseEntity {
	private String message;
	
	private boolean hidden;
	
	private boolean viewed;
	
	@ManyToOne(optional = false)
	private Update update;
	
	// TODO override equals() and hashCode()
	
	protected Notification(){}
	
	public Notification(UUID uuid) {
		super(uuid);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public Update getUpdate() {
		return update;
	}

	public void setUpdate(Update update) {
		this.update = update;
	}
}
