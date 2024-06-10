package com.github.lorenzosmc.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;

//FIXME how to deal with multi-level hierarchies? e.g. Publisher -> Resource -> Folder/File
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publisher extends BaseEntity {
	@ManyToMany(targetEntity = User.class)
	private Set<Subscriber> subscribers = new HashSet<>();
	
	// TODO override equals() and hashCode()

	protected Publisher(){}
	
	public Publisher(UUID uuid) {
		super(uuid);
	}
	
	public boolean attatch(Subscriber subscriber){
		return subscribers.add(subscriber);
	}
	
	public boolean detatch(Subscriber subscriber) {
		if(subscribers.contains(subscriber))
			return subscribers.remove(subscriber);
			
		return false;
	}
	
	protected void notifySubscribers(Update update) {
		for(Subscriber subscriber : subscribers) 
			subscriber.update(update);
	}
}
