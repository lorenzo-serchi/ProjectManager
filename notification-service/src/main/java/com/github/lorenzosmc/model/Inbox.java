package com.github.lorenzosmc.model;

import java.util.*;

import com.github.lorenzosmc.common.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Inbox extends BaseEntity implements Subscriber {
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Notification> notifications;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Map<UpdateCategory, UpdatePreference> updatePreferences;
	
	// TODO override equals() and hashCode()
	
	protected Inbox(){}
	
	public Inbox(UUID uuid) {
		super(uuid);
		notifications = new HashSet<>();
		updatePreferences = new HashMap<>();
	}

	@Override
	public void update(Update update) {
		if(filterByPreferences(update)) {
			Notification notification = ModelFactory.createNotification();
			notification.setUpdate(update);
			
			notifications.add(notification);
		}
	}
	
	public boolean addRule(Rule rule) {
		UpdateCategory ruleCategory = rule.getCategory();

		boolean success = true;
		boolean preferenceMissing = false;
		try {
			UpdatePreference updatePreference = updatePreferences.get(ruleCategory);
			
			if(updatePreference == null) {
				updatePreference = ModelFactory.createUpdatePreference(ruleCategory);
				updatePreferences.put(ruleCategory, updatePreference);
			}

			updatePreference.addRule(rule);
		} catch (IllegalRuleException e) { 
			if(preferenceMissing) 
				updatePreferences.remove(ruleCategory);
			success = false; 
		}
		
		return success;
	}
	
	private boolean filterByPreferences(Update update) {
		boolean result = false;
		UpdatePreference updatePreference = updatePreferences.get(update.getCategory());
		
		if(updatePreference != null && updatePreference.isEnabled()) 
			result = updatePreference.apply(update);
		
		return result;
	}
	
	public Set<Notification> getNotifications() {
		//FIXME defensive-copy
		return notifications;
	}
}
		
	
