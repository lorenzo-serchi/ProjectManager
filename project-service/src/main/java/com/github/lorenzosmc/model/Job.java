package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.*;

@Entity
public class Job extends BaseEntity implements Discussable{
    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "status cannot be null")
    private JobStatus status;

    @ManyToOne(optional = false)
    private Participant creator;

    @PastOrPresent(message = "creation date must be a past or present date")
    @NotNull
    private Instant creationDate = Instant.now();

    @Embedded
    private Discussion discussion;

    @ManyToOne
    private Topic origin;

    @OneToMany(mappedBy = Checkpoint_.ORIGIN, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Checkpoint> checkpoints;

    @ManyToMany
//    FIXME: issue with Lazy Loading (move to getter)
//    @Size(min = 1)
    private Set<Participant> participants;

    protected Job(){}

    public Job(UUID uuid, String title, String description, Participant creator, Set<Participant> participants){
        super(uuid);
        this.title = title;
        this.description = description;
        this.status = JobStatus.ACTIVE;
        this.creator = creator;
        this.creationDate = Instant.now();
        this.discussion = new Discussion();
        this.checkpoints = new ArrayList<>(); // FIXME better with LinkedHashSet<>
        this.participants = new HashSet<>(participants);

        // TODO move to Jakarta Bean Validation
        if(this.participants.isEmpty())
            throw new IllegalArgumentException("participants cannot be empty");
    }

    //TODO override equals() and hashCode()

    public boolean addCheckpoint(Checkpoint checkpoint){
        return checkpoints.add(checkpoint);
    }

    public boolean removeCheckpoint(Checkpoint checkpoint){
        return checkpoints.remove(checkpoint);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public JobStatus getStatus() {
        return JobStatus.valueOf(status.name());
    }

    public void setStatus(JobStatus status) {
        this.status = JobStatus.valueOf(status.name());
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Participant getCreator() {
        return creator;
    }

    public void setCreator(Participant creator) {
        this.creator = creator;
    }

    public Topic getOrigin() {
        return origin;
    }

    public void setOrigin(Topic origin) {
        this.origin = origin;
    }

    public List<Checkpoint> getCheckpoints() {
        return List.copyOf(checkpoints);
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = new ArrayList<>(checkpoints);
    }

    public Set<Participant> getParticipants() {
        return Set.copyOf(participants);
    }

    @Override
    public boolean addMessage(Message message) {
        return discussion.addMessage(message);
    }

    @Override
    public boolean removeMessage(Message message) {
        return discussion.removeMessage(message);
    }

    @Override
    public Discussion getDiscussion() {
        return new Discussion(discussion);
    }
}
