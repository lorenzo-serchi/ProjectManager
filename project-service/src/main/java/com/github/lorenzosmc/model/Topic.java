package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Topic extends BaseEntity implements Taggable, Discussable{
	@NotBlank(message = "title cannot be blank")
	private String title;

	@NotBlank(message = "content cannot be blank")
	private String content;

	private boolean locked;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "urgency cannot be null")
	private TopicUrgency urgency;

	@PastOrPresent(message = "creation date must be a past or present date")
	@NotNull
	private Instant creationDate;

	@ManyToOne(optional = false)
	private Participant creator;

	@Embedded
	@NotNull
	private Discussion discussion;

	@Embedded
	@NotNull
	private TagManager tagManager;

	@OneToMany(mappedBy = Job_.ORIGIN)
	private List<Job> jobs;

	// TODO override equals() and hashCode()
	
	protected Topic(){}

	// FIXME Builder pattern...
	public Topic(UUID uuid, String title, String content,
//				 boolean locked, TopicUrgency urgency, Set<Tag> tags,// FIXME Builder... (optional params -> ModelFactory/l8r)
				 Participant creator) {
		super(uuid);
		this.title = title;
		this.content = content;
//		this.locked = locked; // FIXME Builder... (optional -> ModelFactory/l8r)
//		this.urgency = urgency; // FIXME Builder... (optional -> ModelFactory/l8r)
		this.creator = creator;
		this.tagManager = new TagManager(TagType.TOPIC); // FIXME Builder... (optional: might or might not specify tags at creation... -> l8r)
		this.jobs = new ArrayList<>();
	}

	public boolean addJob(Job job){
		return jobs.add(job);
	}

	public boolean removeJob(Job job){
		return jobs.remove(job);
	}

	@Override
	public boolean addTag(Tag tag, Participant assigner){
		return tagManager.addTag(tag, assigner);
	}

	@Override
	public boolean removeTag(Tag tag){
		return tagManager.removeTag(tag);
	}

	@Override
	public boolean addMessage(Message message){
		return discussion.addMessage(message);
	}

	@Override
	public boolean removeMessage(Message message){
		return discussion.removeMessage(message);
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public TopicUrgency getUrgency() {
		return TopicUrgency.valueOf(urgency.name());
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public Participant getCreator() {
		return creator;
	}

	@Override
	public Discussion getDiscussion() {
		return new Discussion(discussion);
	}

	@Override
	public Set<Tag> getTags() {
		return tagManager.getTags();
	}

	@Override
	public Set<TagAssignment> getTagAssignments(){
		return tagManager.getTagAssignments();
	}

	public List<Job> getJobs() {
		return List.copyOf(jobs);
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = new ArrayList<>(jobs);
	}
}
