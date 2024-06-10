package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.AbstractModelFactory;

import java.util.UUID;

public class ModelFactory extends AbstractModelFactory {
	private ModelFactory() {};

	public static Topic createTopic() {
		return new Topic(generateUuid());
	}
	
	public static Update createUpdate() {
		return new Update(generateUuid());
	}
	
	public static Resource createResource(UUID uuid) {
		return new Resource(uuid);
	}

	public static Inbox createInbox() {
		return new Inbox(generateUuid());
	}

	public static Notification createNotification() {
		Notification notification = new Notification(generateUuid());
		notification.setMessage("");
		notification.setHidden(false);
		notification.setViewed(false);
		
		return notification;
	}

	public static UpdatePreference createUpdatePreference(UpdateCategory category) {
		if(category == null)
			throw new IllegalArgumentException("category cannot be NULL");
		
		UpdatePreference updatePreference = new UpdatePreference(generateUuid());
		updatePreference.setEnabled(false);
		updatePreference.setCategory(category);
		
		return updatePreference;
	}

	public static Rule createUserRule(UpdateCategory category) {
		Rule rule = createRule(category, RuleScope.USER);
		
		return rule;
	}
	
	public static Rule createContextRule(UpdateCategory category, String contextUuid) {
		if(contextUuid == null)
			throw new IllegalArgumentException("contextUuid cannot be NULL");
		
		Rule rule = createRule(category, RuleScope.CONTEXT);
		rule.setContextUuid(contextUuid);

		return rule;
	}
	
	public static Rule createTaskRule(UpdateCategory category, String contextUuid, String taskUuid) {
		if(taskUuid == null)
			throw new IllegalArgumentException("taskUuid cannot be NULL");
		
		if(contextUuid == null)
			throw new IllegalArgumentException("contextUuid cannot be NULL");
		
		Rule rule = createRule(category, RuleScope.TASK);
		rule.setContextUuid(contextUuid);
		rule.setTaskUuid(contextUuid);
		
		return rule;
	}
	
	private static Rule createRule(UpdateCategory category, RuleScope scope) {
		if(category == null)
			throw new IllegalArgumentException("category cannot be NULL");
		
		Rule rule = new Rule(generateUuid());
		rule.setCategory(category);
		rule.setScope(scope);
		rule.setMinimumUrgency(TopicUrgency.LOW);
		
		return rule;
	}
}
