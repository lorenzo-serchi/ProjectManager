package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.Instant;
import java.util.*;

@Entity
public class Faq extends BaseEntity {
	private String question;

	private String answer;

	private Instant creationDate;
	
	private Instant lastModified;

	private boolean visible;

	private boolean posted;

	@ManyToMany(mappedBy = Context_.FAQS)
	private Set<Context> contexts;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<TagAssignment> tagAssignments;

	@OneToMany
	private List<Project> citedProjects;

	@OneToMany
	private List<Task> citedTasks;

	@OneToMany
	private List<File> citedResources;

	@OneToMany
	private List<Topic> citedTopics;

	@OneToMany
	private List<Message> citedMessages;

	protected Faq(){}
	
	public Faq(UUID uuid) {
		super(uuid);
		Instant currentInstant = Instant.now();
		creationDate = currentInstant;
		lastModified = currentInstant;
		contexts = new HashSet<>();
		tagAssignments = new HashSet<>();
		citedProjects = new ArrayList<>();
		citedTasks = new ArrayList<>();
		citedResources = new ArrayList<>();
		citedTopics = new ArrayList<>();
		citedMessages = new ArrayList<>();
	}

	public void addContext(Context context) {
		contexts.add(context);
	}

	public boolean removeContext(Context context) {
		return contexts.remove(context);
	}

	public void addTagAssignment(TagAssignment assignment) {
		tagAssignments.add(assignment);
	}

	public boolean removeTagAssignment(TagAssignment assignment) {
		return tagAssignments.remove(assignment);
	}
	
	public void addCitedProject(Project project) {
		citedProjects.add(project);
	}

	public boolean removeCitedProject(Project project) {
		return citedProjects.remove(project);
	}

	public void addCitedTask(Task task) {
		citedTasks.add(task);
	}

	public boolean removeCitedTask(Task task) {
		return citedTasks.remove(task);
	}

	public void addCitedResource(File citedResource) {
		citedResources.add(citedResource);
	}

	public boolean removeCitedResource(File citedResource) {
		return citedResources.remove(citedResource);
	}

	public void addCitedTopic(Topic topic) {
		citedTopics.add(topic);
	}

	public boolean removeCitedTopic(Topic topic) {
		return citedTopics.remove(topic);
	}

	public void addCitedMessage(Message message) {
		citedMessages.add(message);
	}

	public boolean removeCitedMessage(Message message) {
		return citedMessages.remove(message);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public Instant getLastModified() {
		return lastModified;
	}

	public void setLastModified(Instant lastModified) {
		this.lastModified = lastModified;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isPosted() {
		return posted;
	}

	public void setPosted(boolean posted) {
		this.posted = posted;
	}

	public Set<Context> getContexts() {
		return contexts;
	}

	public void setContexts(Set<Context> contexts) {
		this.contexts = contexts;
	}

	public Set<TagAssignment> getTagAssignments() {
		return tagAssignments;
	}

	public void setTagAssignments(Set<TagAssignment> tagAssignments) {
		this.tagAssignments = tagAssignments;
	}

	public List<Project> getCitedProjects() {
		return citedProjects;
	}

	public void setCitedProjects(List<Project> citedProjects) {
		this.citedProjects = citedProjects;
	}

	public List<Task> getCitedTasks() {
		return citedTasks;
	}

	public void setCitedTasks(List<Task> citedTasks) {
		this.citedTasks = citedTasks;
	}

	public List<File> getCitedResources() {
		return citedResources;
	}

	public void setCitedResources(List<File> citedResources) {
		this.citedResources = citedResources;
	}

	public List<Topic> getCitedTopics() {
		return citedTopics;
	}

	public void setCitedTopics(List<Topic> citedTopics) {
		this.citedTopics = citedTopics;
	}

	public List<Message> getCitedMessages() {
		return citedMessages;
	}

	public void setCitedMessages(List<Message> citedMessages) {
		this.citedMessages = citedMessages;
	}

}
