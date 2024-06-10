package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//TODO add context preferences (allowed actions)
@Entity
public class Context extends BaseEntity {
	private String name;
	
	private String description;

	private boolean locked;

	private boolean hidden;

	private String studentPasswordHash;

	private String collaboratorPasswordHash;

	private Instant creationDate;

	@ManyToOne(optional = false)
	private User creator;

	@OneToMany(mappedBy = Participation_.CONTEXT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Participation> participations;

	@OneToMany(mappedBy = Collaboration_.CONTEXT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Collaboration> collaborations;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Task> tasks;

	@OneToMany(mappedBy = Tag_.CONTEXT, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Tag> tags;

	@ManyToMany(cascade = CascadeType.REMOVE)
	private Set<Faq> faqs;

	protected Context() {}

	public Context(UUID uuid) {
		super(uuid);
		creationDate = Instant.now();
		participations = new HashSet<>();
		collaborations = new HashSet<>();
		tasks = new HashSet<>();
		tags = new HashSet<>();
		faqs = new HashSet<>();
	}

	public boolean addParticipation(Participation participation) {
		return participations.add(participation);
	}

	public boolean removeParticipation(Participation participation) {
		return participations.remove(participation);
	}

	public boolean addCollaboration(Collaboration collaboration) {
		return collaborations.add(collaboration);
	}

	public boolean removeCollaboration(Collaboration collaboration) {
		return collaborations.remove(collaboration);
	}

	public boolean addTask(Task task) {
		return tasks.add(task);
	}

	public boolean removeTask(Task task) {
		return tasks.remove(task);
	}

	public boolean addTag(Tag tag) {
		return tags.add(tag);
	}
	
	public boolean removeTag(Tag tag) {
		return tags.remove(tag);
	}

	public boolean addFaq(Faq faq) {
		return faqs.add(faq);
	}

	public boolean removeFaq(Faq faq) {
		return faqs.remove(faq);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getStudentPasswordHash() {
		return studentPasswordHash;
	}

	public void setStudentPasswordHash(String passwordHash) {
		this.studentPasswordHash = passwordHash;
	}

	public String getCollaboratorPasswordHash() {
		return collaboratorPasswordHash;
	}

	public void setCollaboratorPasswordHash(String passwordHash) {
		this.collaboratorPasswordHash= passwordHash;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

	public Set<Collaboration> getCollaborations() {
		return collaborations;
	}

	public void setCollaborations(Set<Collaboration> collaborations) {
		this.collaborations = collaborations;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Faq> getFaqs() {
		return faqs;
	}

	public void setFaqs(Set<Faq> faqs) {
		this.faqs = faqs;
	}
}
