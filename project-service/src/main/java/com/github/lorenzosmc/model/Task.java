package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.*;

@Entity
public class Task extends BaseEntity implements Taggable{
	@NotBlank(message = "name cannot be blank")
	private String name;

	@NotBlank(message = "statement cannot be blank")
	private String statement;

	private boolean visible;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "status cannot be null")
	TaskStatus status;

	@NotNull(message = "context uuid cannot be null")
	private UUID contextUuid;

	@ManyToOne(optional = false)
	private User creator;

	@PastOrPresent(message = "creation date must be a past or present date")
	@NotNull
	private Instant creationDate;

	@PastOrPresent(message = "last update date must be a past or present date")
	private Instant lastUpdateDate;

	@Positive(message = "latest checkpoint number must not be negative or zero")
	private int latestCheckpointNumber;
	
	private UUID workgroupUuid;

	@Embedded
	private TaskPreferenceManager preferenceManager;

	@ManyToOne
	private Project project;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Topic> topics;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Checkpoint> notApprovedCheckpoints;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Checkpoint> approvedCheckpoints;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Milestone> milestones;

	@OneToOne(optional = false, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Folder rootFolder;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<TaskProgressReview> reviews;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Participant> participants;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Job> jobs;

	@Embedded
	@NotNull
	private TagManager tagManager;

	protected Task() {}

	public Task(UUID uuid, UUID contextUuid,  String name, String statement, Folder rootFolder, User creator) {
		super(uuid);
		this.name = name;
		this.statement = statement;
		this.visible = true;
		this.status = TaskStatus.DRAFTED; // FIXME could make sense to immediately be UNASSIGNED (Builder?)
		this.contextUuid = contextUuid;
		this.creator = creator;
		this.creationDate = Instant.now();
		this.lastUpdateDate = this.creationDate;
		this.latestCheckpointNumber = 1;
		this.preferenceManager = new TaskPreferenceManager();
		this.topics = new ArrayList<>(); // FIXME use LinkedHashSet<> (order & no duplicates)
		this.notApprovedCheckpoints = new ArrayList<>(); // FIXME use LinkedHashSet<> (order & no duplicates)
		this.approvedCheckpoints = new ArrayList<>(); // FIXME use LinkedHashSet<> (order & no duplicates)
		this.milestones = new ArrayList<>(); // FIXME use LinkedHashSet<> (order & no duplicates)
		this.rootFolder = rootFolder;
		this.reviews = new ArrayList<>(); // FIXME use LinkedHashSet<> (order & no duplicates)
		this.participants = new HashSet<>();
		this.jobs = new HashSet<>();
		this.tagManager = new TagManager(TagType.TASK); // FIXME tags might be optionally specified at creation (ModelFactory)
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public boolean setPreference(String preference, boolean value){
		return preferenceManager.setPreference(preference, value);
	}

	public boolean addTopic(Topic topic) {
		return topics.add(topic);
	}

	public boolean removeTopic(Topic topic) {
		return topics.remove(topic);
	}

	// FIXME unsure...
	// FIXME make sure this is right...
	public boolean addCheckpoint(Checkpoint checkpoint) {
		if(checkpoint == null)
			throw new IllegalArgumentException("checkpoint cannot be null");

		if(checkpoint.isApproved())
			throw new IllegalArgumentException("checkpoint cannot be approved");

		if(isValidCheckpoint(checkpoint) &&
				!notApprovedCheckpoints.contains(checkpoint)){ // FIXME no need with LinkedHashSet<> (order AND no duplicates)
			int nextCheckpointNumber = latestCheckpointNumber++;
			checkpoint.setCheckpointNumber(nextCheckpointNumber);
			return notApprovedCheckpoints.add(checkpoint);
		}
		return false;
	}

	// FIXME align with changes...
	// FIXME make sure this is right...
	private boolean isValidCheckpoint(Checkpoint checkpoint){
		return rootFolder.containsAll(checkpoint.getRequiredResources())
				&& rootFolder.containsNone(checkpoint.getCreatedResources())
				&& !checkpoint.getRemovedResources().contains(rootFolder); // don't allow root deletion
	}

	public boolean removeCheckpoint(Checkpoint checkpoint) {
		if (checkpoint == null || checkpoint.isApproved())
			return false;

		if (!notApprovedCheckpoints.isEmpty()){
			Checkpoint lastNotApprovedCheckpoint = notApprovedCheckpoints.get(notApprovedCheckpoints.size() - 1);
			if (checkpoint.equals(lastNotApprovedCheckpoint)) {
				latestCheckpointNumber--;
				notApprovedCheckpoints.remove(checkpoint);
				return true;
			}else
				throw new IllegalArgumentException("cannot remove checkpoint: must be last not approved checkpoint");
		}
		return false;
	}

	// FIXME make sure this is right...
	// FIXME should probably remove every other checkpoint (from notApprovedCheckpoints) that overlaps in some way? (as
	// 		 it would be dependant on a previous version -> can't be approved)
	public boolean approveCheckpoint(Checkpoint checkpoint) throws UnsupportedOperationByResourceException {
		if (checkpoint == null)
			throw new IllegalArgumentException("checkpoint cannot be null");

		Checkpoint firstNotApprovedCheckpoint = notApprovedCheckpoints.get(0);
		if(!checkpoint.equals(firstNotApprovedCheckpoint))
			throw new UnsupportedOperationException("cannot approve checkpoint: must be first not approved checkpoint");

		// FIXME what if apply fails
		return checkpoint.apply() && approvedCheckpoints.add(checkpoint);
	}

	// FIXME should there be functionality to revertCheckpoint() (i.e. go from approved -> notApproved)

	// TODO getResource(Checkpoint checkpoint) should calculate the resource tree (hmm... perhaps store a cached
	//   	lastApprovedCheckpoint (int), and see if that is closer or the empty resource tree is - could be useful
	//		since Checkpoints only store CHANGES

	public boolean addMilestone(Milestone milestone){
		return milestones.add(milestone);
	}

	public boolean removeMilestone(Milestone milestone){
		return milestones.remove(milestone);
	}

	public boolean addReview(TaskProgressReview review) {
		return reviews.add(review);
	}

	public boolean removeReview(TaskProgressReview review) {
		return reviews.remove(review);
	}

	public boolean addParticipant (Participant participant) {
		return participants.add(participant);
	}

	public boolean removeParticipant (Participant participant) {
		return participants.remove(participant);
	}

	public boolean addJob(Job job){
		return jobs.add(job);
	}

	public boolean removeJob(Job job){
		return jobs.remove(job);
	}

	@Override
	public boolean addTag(Tag tag, Participant assigner) {
		return tagManager.addTag(tag, assigner);
	}

	@Override
	public boolean removeTag(Tag tag) {
		return tagManager.removeTag(tag);
	}

	public String getName() {
		return name;
	}

	public String getStatement() {
		return statement;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public TaskStatus getStatus() {
		return TaskStatus.valueOf(status.name());
	}

	public void setStatus(TaskStatus status) {
		this.status = TaskStatus.valueOf(status.name());
	}

	public UUID getContextUuid() {
		return contextUuid;
	}

	public User getCreator() {
		return creator;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public Instant getLastUpdateDate() {
		return lastUpdateDate;
	}

	public UUID getWorkgroupUuid() {
		return workgroupUuid;
	}

	public void setWorkgroupUuid(UUID workgroupUuid) {
		this.workgroupUuid = workgroupUuid;
	}

	public Map<String, Boolean> getPreferences() {
		return preferenceManager.getPreferences();
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Topic> getTopics() {
		return List.copyOf(topics);
	}

	public List<Checkpoint> getNotApprovedCheckpoints() {
		return List.copyOf(notApprovedCheckpoints);
	}

	public List<Checkpoint> getApprovedCheckpoints() {
		return List.copyOf(approvedCheckpoints);
	}

	public List<Milestone> getMilestones(){
		return List.copyOf(milestones);
	}

	public Folder getRootFolder() {
		// FIXME defensive-copy (or find a way to create an immutable Resource/Folder)
		return rootFolder;
	}

	public List<TaskProgressReview> getReviews() {
		return List.copyOf(reviews);
	}

	@Override
	public Set<Tag> getTags() {
		return tagManager.getTags();
	}

	public Set<TagAssignment> getTagAssignments(){return tagManager.getTagAssignments();}

	public Set<Participant> getParticipants() {
		return Set.copyOf(participants);
	}

	public Set<Job> getJobs(){
		return Set.copyOf(jobs);
	}
}
