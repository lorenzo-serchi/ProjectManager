package com.github.lorenzosmc;

import com.github.lorenzosmc.common.model.AbstractModelFactory;
import com.github.lorenzosmc.model.*;

import java.util.Set;
import java.util.UUID;

public class ModelFactory extends AbstractModelFactory {
	private ModelFactory() {};

	public static Checkpoint newCheckpoint(String message, Participant creator) {
		return new Checkpoint(generateUuid(), message, creator);
	}

	public static File newFile(String name, byte[] data, String mediaType) {
		return new File(generateUuid(), name, data, mediaType);
	}

	public static Folder newFolder(Resource parent, String name) {
		return new Folder(generateUuid(), name);
	}

	public static Job newJob(String title, String description, Participant creator, Set<Participant> participants){
		return new Job(generateUuid(), title, description, creator, participants);
	}

	public static Message newMessage(String text, Participant creator) {
		return new Message(generateUuid(), text, creator);
	}

	public static Milestone newMilestone(String name, String description, String summary, Participant creator){
		return new Milestone(generateUuid(), name, description, summary, creator);
	}

	public static Participant newParticipant(Task task, Role role, User user){
		return new Participant(generateUuid(), user, task, role);
	}

	public static Project newProject(String title, String statement, User creator) {
		return new Project(generateUuid(), title, statement, creator);
	}

	public static Tag newTag(UUID uuid, TagType type, String name){
		return new Tag(uuid, type, name);
	}

	public static Task newTask(UUID contextUuid, String name, String statement, User creator) {
		Folder rootFolder = new Folder(generateUuid(), "root");
		return new Task(generateUuid(), contextUuid, name, statement, rootFolder, creator);
	}

	public static TaskProgressReview newTaskProgressReview(String message, Participant requestor, Participant reviewer) {
		return new TaskProgressReview(generateUuid(), message, requestor, reviewer);
	}

	public static Topic newTopic(String title, String content, Participant creator) {
		return new Topic(generateUuid(), title, content, creator);
	}
	
	public static User newUser(UUID uuid, String fullName) {
		return new User(uuid, fullName);
	}
}
