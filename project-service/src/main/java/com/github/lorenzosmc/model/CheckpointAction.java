package com.github.lorenzosmc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class CheckpointAction {
    @Enumerated(EnumType.STRING)
    @NotNull
    private CheckpointActionType type;

    @ManyToOne(targetEntity = AbstractResourceImpl.class, optional = false)
    private Resource parentResource;

    @ManyToOne(targetEntity = AbstractResourceImpl.class, optional = false)
    private Resource resource;

    @ManyToOne(optional = false)
    private Checkpoint origin;

    protected CheckpointAction() { }

    public static CheckpointAction newRemoveAction(Resource resource) {
        CheckpointAction action = new CheckpointAction();
        action.type = CheckpointActionType.REMOVE;
        action.resource = resource;
        return action;
    }

    public static CheckpointAction newCreateAction(Resource parentResource, Resource resource){
        CheckpointAction action = new CheckpointAction();
        action.type = CheckpointActionType.CREATE;
        action.parentResource = parentResource;
        action.resource = resource;
        return action;
    }

    public CheckpointAction(CheckpointAction other) {
        if (other == null)
            throw new IllegalArgumentException("argument cannot be null");

        this.type = CheckpointActionType.valueOf(other.type.name());
        this.parentResource = other.parentResource;
        this.resource = other.resource;
    }

    public boolean overlaps(CheckpointAction other) {
        return switch (type) {
            case REMOVE -> {
                switch (other.type) {
                    case REMOVE -> {
                        yield resource.containsOrEquals(other.resource);
                    }
                    case CREATE -> {
                        yield resource.containsOrEquals(other.parentResource);
                    }
                    default -> {
                        throw new IllegalArgumentException("unexpected enum value: " + other.type.toString());
                    }
                }
            }
            default -> {
                throw new IllegalArgumentException("unexpected enum value: " + type.toString());
            }
        };
    }

    public boolean apply() throws UnsupportedOperationByResourceException {
        return switch (type) {
            case CREATE -> {
                if (parentResource.addResource(resource)) {
                    resource.setParentResource(parentResource);
                    yield true;
                }
                yield false;
            }
            case REMOVE -> {
                if (parentResource.removeResource(resource)) {
                    resource.setParentResource(null);
                    yield true;
                }
                yield false;
            }
            default -> {
                throw new IllegalArgumentException("unexpected action type: " + type.toString());
            }
        };
    }

    public boolean unapply() throws UnsupportedOperationByResourceException {
        if (type == CheckpointActionType.CREATE) {
            if (parentResource.removeResource(resource)) {
                resource.setParentResource(null);
                return true;
            }
            return false;
        } else if (type == CheckpointActionType.REMOVE) {
            if (parentResource.addResource(resource)) {
                resource.setParentResource(parentResource);
                return true;
            }
            return false;
        }
        return false;
    }

    public CheckpointActionType getType() {
        return CheckpointActionType.valueOf(type.name());
    }

    public Resource getParentResource() {
        return parentResource;
    }

    public Resource getResource() {
        return resource;
    }
}
