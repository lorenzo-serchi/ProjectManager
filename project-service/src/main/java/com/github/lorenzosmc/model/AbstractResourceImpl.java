package com.github.lorenzosmc.model;

import com.github.lorenzosmc.model.tagobserver.AbstractObservableImpl;
import com.github.lorenzosmc.model.tagobserver.UpdateAction;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
public abstract class AbstractResourceImpl extends AbstractObservableImpl implements Resource{
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Positive(message = "size must be strictly greater than zero")
    private Integer sizeInBytes;

    @OneToOne
    private Checkpoint origin;

    @ManyToOne
    private Resource parentResource;

    @Embedded
    @NotNull
    private TagManager tagManager;

    @Embedded
    @NotNull
    private Discussion discussion;

    protected AbstractResourceImpl() {
    }

    public AbstractResourceImpl(UUID uuid, String name){
        super(uuid);
        this.name = name;
        this.sizeInBytes = 0;
        this.tagManager = new TagManager(TagType.RESOURCE);
        this.discussion = new Discussion();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSizeInBytes() {
        return sizeInBytes;
    }

    protected void setSizeInBytes(int sizeInBytes){ this.sizeInBytes = sizeInBytes;}

    @Override
    public Participant getCreator() {
        return origin == null ? null : origin.getCreator();
    }

    public Checkpoint getOrigin() {
        return origin;
    }

    public void setOrigin(Checkpoint origin){
        this.origin = origin;
    }

    @Override
    public Instant getCreationDate() {
        return origin == null ? null : origin.getCreationDate();
    }

    @Override
    public Resource getParentResource() {
        return parentResource;
    }

    @Override
    public void setParentResource(Resource resource) {
        if (resource instanceof Folder)
            parentResource = (Folder) resource;
        else
            throw new IllegalArgumentException("parent resource must be a folder");
    }

    @Override
    public boolean addTag(Tag tag, Participant assigner) {
        if(tagManager.addTag(tag, assigner)) {
            notifyObservers(tag, assigner, UpdateAction.ADDED);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeTag(Tag tag) {
        if(tagManager.removeTag(tag)){
            notifyObservers(tag, null, UpdateAction.REMOVED);
            return true;
        }
        return false;
    }

    @Override
    public Set<Tag> getTags() {
        return tagManager.getTags();
    }

    @Override
    public Set<TagAssignment> getTagAssignments(){ return tagManager.getTagAssignments();}

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
