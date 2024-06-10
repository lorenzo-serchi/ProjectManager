package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Checkpoint extends BaseEntity {
    @Positive(message = "checkpoint number cannot be negative or zero")
    private Integer checkpointNumber;

    @NotBlank(message = "message cannot be blank")
    private String message;

    @ManyToOne(optional = false)
    private Participant creator;

    @PastOrPresent(message = "join date must be a past or present date")
    @NotNull
    private Instant creationDate;

    private boolean approved;

    @ManyToOne
    private Participant approvedBy;

    @PastOrPresent(message = "approval date must be a past or present date")
    private Instant approvalDate;

    @ElementCollection
    private Set<CheckpointAction> removeActions;

    @ElementCollection
    private Set<CheckpointAction> createActions;

    @ManyToMany(targetEntity = AbstractResourceImpl.class)
    private Set<Resource> requiredResources;

    @OneToMany(targetEntity = AbstractResourceImpl.class, mappedBy = AbstractResourceImpl_.ORIGIN)
    private Set<Resource> createdResources;

    @OneToMany(targetEntity = AbstractResourceImpl.class)
    private Set<Resource> removedResources;

    @ManyToOne
    private Job origin;

    protected Checkpoint() {
    }

    public Checkpoint(UUID uuid, String message, Participant creator) {
        super(uuid);
        this.message = message;
        this.creator = creator;
        this.creationDate = Instant.now();
        this.removeActions = new HashSet<>();
        this.createActions = new HashSet<>();
        this.requiredResources = new HashSet<>();
        this.createdResources = new HashSet<>();
        this.removedResources = new HashSet<>();
    }

    public boolean apply() throws UnsupportedOperationByResourceException {
        boolean result = true;

        for (CheckpointAction removeAction : removeActions)
            result &= removeAction.apply();

        for (CheckpointAction createAction : createActions)
            result &= createAction.apply();

        return result;
    }

    public boolean unapply() throws UnsupportedOperationByResourceException {
        boolean result = true;

        for (CheckpointAction createAction : createActions)
            result &= createAction.unapply();

        for (CheckpointAction removeAction : removeActions)
            result &= removeAction.unapply();

        return result;
    }

    public boolean createResource(Resource parent, Resource resource) {
        return addAction(CheckpointAction.newCreateAction(parent, resource));
    }

    public boolean removeResource(Resource resource) {
        return addAction(CheckpointAction.newRemoveAction(resource));
    }

    public boolean replaceResource(Resource oldResource, Resource newResourceParent, Resource newResource) throws UnsupportedOperationByResourceException {
        if (oldResource == null)
            throw new IllegalArgumentException("old resource cannot be null");
        if (newResourceParent == null)
            throw new IllegalArgumentException("new resource's parent resource cannot be null");
        if (newResource == null)
            throw new IllegalArgumentException("new resource cannot be null");

        CheckpointAction remove = CheckpointAction.newRemoveAction(oldResource);
        CheckpointAction create = CheckpointAction.newCreateAction(newResourceParent, newResource);
        newResource.setPreviousRevision(oldResource);
        if(addAction(remove)) {
            if (addAction(create))
                return true;
            else
                removeAction(remove);
        }
        return false;
    }

    private boolean addAction(CheckpointAction checkpointAction) {
        if (checkpointAction == null)
            throw new IllegalArgumentException("action cannot be null");

        CheckpointAction action = new CheckpointAction(checkpointAction);
        if (!overlaps(checkpointAction)) {
            switch (action.getType()) {
                case REMOVE -> {
                    addRequiredResources(action);
                    removedResources.add(action.getResource());
                    removeActions.add(action);
                    return true;
                }
                case CREATE -> {
                    addRequiredResources(action);
                    createdResources.add(action.getResource());
                    createActions.add(action);
                    return true;
                }
                default -> {
                    throw new IllegalArgumentException("unexpected action type: " + action.getType());
                }
            }
        }
        return false;
    }

    private boolean overlaps(CheckpointAction action) {
        return switch (action.getType()) {
            case REMOVE -> {
                for (CheckpointAction removeAction : removeActions)
                    if (removeAction.overlaps(action))
                        yield false;
                yield true;
            }
            case CREATE -> {
                for (CheckpointAction createAction : createActions)
                    if (createAction.overlaps(action))
                        yield false;
                yield true;
            }
            default -> {
                throw new IllegalArgumentException("unexpected action type: " + action.getType().toString());
            }
        };
    }

    private boolean addRequiredResources(CheckpointAction action) {
        return switch (action.getType()) {
            case REMOVE -> {
                yield requiredResources.add(action.getResource());
            }
            case CREATE -> {
                yield requiredResources.add(action.getParentResource());
            }
            default -> {
                throw new IllegalArgumentException("unexpected action type: " + action.getType());
            }
        };
    }

    private boolean removeAction(CheckpointAction checkpointAction) {
        if (checkpointAction == null)
            return false;

        CheckpointAction action = new CheckpointAction(checkpointAction);
        return switch (action.getType()) {
            case REMOVE -> {
                if (removeActions.remove(action)) {
                    removedResources.remove(action.getResource());
                    removeRequiredResources(action);
                    yield true;
                }
                yield false;
            }
            case CREATE -> {
                if (createActions.remove(action)) {
                    createdResources.remove(action.getResource());
                    removeRequiredResources(action);
                    yield true;
                }
                yield false;
            }
            default -> {
                throw new IllegalArgumentException("unexpected action type: " + action.getType().toString());
            }
        };
    }

    private boolean removeRequiredResources(CheckpointAction action) {
        return switch (action.getType()) {
            case REMOVE -> {
                yield requiredResources.remove(action.getResource());
            }
            case CREATE -> {
                yield requiredResources.remove(action.getParentResource());
            }
            default -> {
                throw new IllegalArgumentException("unexpected action type: " + action.getType());
            }
        };
    }

    public boolean approve(Participant approver) {
        if (this.approved == false) {
            this.approved = true;
            this.approvedBy = approver;
            this.approvalDate = Instant.now();
            return true;
        }
        return false;
    }

    public boolean unApprove() {
        if (this.approved == true) {
            this.approved = false;
            this.approvedBy = null;
            this.approvalDate = null;
            return true;
        }
        return false;
    }

    public Set<Resource> getCreatedResources() {
        return Set.copyOf(createdResources);
    }

    public Set<Resource> getRemovedResources() {
        return Set.copyOf(removedResources);
    }

    public int getCheckpointNumber() {
        return checkpointNumber;
    }

    public void setCheckpointNumber(int checkpointNumber) {
        this.checkpointNumber = checkpointNumber;
    }

    public String getMessage() {
        return message;
    }

    public Participant getCreator() {
        return creator;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public Participant getApprovedBy() {
        return approvedBy;
    }

    public Instant getApprovalDate() {
        return approvalDate;
    }

    public Set<CheckpointAction> getRemoveActions() {
        return Set.copyOf(removeActions);
    }

    public Set<CheckpointAction> getCreateActions() {
        return Set.copyOf(createActions);
    }

    public Set<Resource> getRequiredResources() {
        return Set.copyOf(requiredResources);
    }

    public Job getOrigin() {
        return origin;
    }

    public void setOrigin(Job job){
        this.origin = job;
    }
}
