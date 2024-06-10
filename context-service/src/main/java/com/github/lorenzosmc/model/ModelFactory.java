package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.AbstractModelFactory;

import java.util.Set;
import java.util.UUID;

public class ModelFactory extends AbstractModelFactory {
	private ModelFactory() {
	};

	public static Collaboration createCollaboration(Context context, Participation collaborator, Task task) {
		if (context == null)
			throw new IllegalArgumentException("context cannot be NULL");

		if (collaborator == null)
			throw new IllegalArgumentException("collaborator cannot be NULL");

		if (task == null)
			throw new IllegalArgumentException("task cannot be NULL");
		
		Collaboration collaboration = new Collaboration(generateUuid());
		collaboration.setActive(true);
		collaboration.setContext(context);
		collaboration.setCollaborator(collaborator);
		collaboration.setTask(task);

		return collaboration;
	}

	public static Context createContext(User creator) {
		if (creator == null)
			throw new IllegalArgumentException("creator cannot be NULL");
		
		Context context = new Context(generateUuid());
		context.setCreator(creator);
		context.setLocked(false);
		context.setHidden(false);

		return context;
	}

	public static Faq createFaq(Set<Context> contexts) {
		if (contexts == null)
			throw new IllegalArgumentException("contexts cannot be NULL");

		if (contexts.isEmpty())
			throw new IllegalArgumentException("contexts cannot be empty");

		Faq faq = new Faq(generateUuid());
		faq.setVisible(true);
		faq.setPosted(false);
		faq.setContexts(contexts);

		return faq;
	}

	public static TagAssignment createTagAssignment(User assigner, Faq faq, Tag tag) {
		if (assigner == null)
			throw new IllegalArgumentException("assigner cannot be NULL");
		
		if (faq == null)
			throw new IllegalArgumentException("faq cannot be NULL");

		if (tag == null)
			throw new IllegalArgumentException("tag cannot be NULL");

		TagAssignment assignment = new TagAssignment(generateUuid());
		assignment.setAssigner(assigner);
		assignment.setTag(tag);

		return assignment;
	}

	public static File createFile(UUID uuid) { return new File(uuid); }

	public static Message createMessage(UUID uuid) {
		return new Message(uuid);
	}

	public static Participation createParticipation(Context context, User participant, Role role) {
		if (context == null)
			throw new IllegalArgumentException("context cannot be NULL");

		if (participant == null)
			throw new IllegalArgumentException("participant cannot be NULL");
		
		if (role == null)
			throw new IllegalArgumentException("role cannot be NULL");
		
		
		Participation participation = new Participation(generateUuid());
		participation.setRole(role);
		participation.setMaxCollaborations(0);
		participation.setContext(context);
		participation.setParticipant(participant);

		return participation;
	}

	public static Project createProject(UUID uuid) {
		return new Project(uuid);
	}

	public static Tag createTag(Context context, TagTarget type) {
		if (context == null)
			throw new IllegalArgumentException("context cannot be NULL");
		
		if (type == null)
			throw new IllegalArgumentException("tag type cannot be NULL");
		
		Tag tag = new Tag(generateUuid());
		tag.setTagTarget(type);

		return tag;
	}
	
	public static Task createTask(UUID uuid) {
		return new Task(uuid);
	}
	
	public static Topic createTopic(UUID uuid) {
		return new Topic(uuid);
	}
	
	public static User createUser(UUID uuid) {
		return new User(uuid);
	}
}
