package com.github.lorenzosmc.model;


import com.github.lorenzosmc.common.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class User extends BaseEntity {
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Inbox inbox;

	//TODO override equals() and hashCode()
	
	protected User(){}
	
	public User(UUID uuid) {
		super(uuid);
		inbox = ModelFactory.createInbox();
	}
	
	public Inbox getInbox() {
		//FIXME defensive-copy
		return inbox;
	}
}
