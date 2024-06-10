package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
public class Tag extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private TagTarget tagTarget;

	@Enumerated(EnumType.STRING)
	private TagType type;

	private String name;

	private Instant creationDate;
	
	private Instant lastModified;

	@ManyToOne(optional = false)
	private Context context;

	@ManyToOne
	private Tag parentTag;
	
	@OneToMany(mappedBy = Tag_.PARENT_TAG, cascade = CascadeType.REMOVE)
	private Set<Tag> childrenTags;
	
	protected Tag(){}
	
	public Tag(UUID uuid) {
		super(uuid);
		Instant currentInstant = Instant.now();
		creationDate = currentInstant;
		lastModified = currentInstant;
	}

	public boolean addChildTag(Tag tag, Tag targetTag) {
		// TODO implement
		// TODO set parent of Tag here
		return false;
	}
	
	public boolean removeChildTag(Tag tag) {
		// TODO implement
		// TODO set parent of Tag here
		return false;
	}
	
	public TagTarget getTagTarget() {
		//FIXME defensive copy
		return tagTarget;
	}

	public void setTagTarget(TagTarget tagTarget) {
		//FIXME defensive copy
		this.tagTarget = tagTarget;
	}

	public TagType getType() {
		//FIXME defensive copy
		return type;
	}

	public void setType(TagType type) {
		//FIXME defensive copy
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Tag getParentTag() {
		return parentTag;
	}

	public void setParentTag(Tag parentTag) {
		this.parentTag = parentTag;
	}
	
	public Set<Tag> getChildrenTags() {
		return childrenTags;
	}

	public void setChildrenTags(Set<Tag> childrenTags) {
		this.childrenTags = childrenTags;
	}
}
