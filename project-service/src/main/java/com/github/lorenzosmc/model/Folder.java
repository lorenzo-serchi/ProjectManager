package com.github.lorenzosmc.model;

import com.github.lorenzosmc.model.tagobserver.Observer;
import com.github.lorenzosmc.model.tagobserver.UpdateAction;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Entity
public class Folder extends AbstractResourceImpl implements Observer{
	@OneToMany(targetEntity = AbstractResourceImpl.class, mappedBy = AbstractResourceImpl_.PARENT_RESOURCE,
			   cascade = CascadeType.REMOVE)
	private Set<Resource> childResources;

	//TODO override equals() and hashCode()

	protected Folder(){}
	
	public Folder(UUID uuid, String name) {
		super(uuid, name);
		this.childResources = new HashSet<>();
	}

	@Override
	public boolean addResource(Resource resource) throws UnsupportedOperationByResourceException {
		if(childResources.add(resource)) {
			resource.attach(this);
			setSizeInBytes(getSizeInBytes() + resource.getSizeInBytes());
			return true;
		}
		return false;
	}

	@Override
	public boolean removeResource(Resource resource) throws UnsupportedOperationByResourceException {
		if(childResources.remove(resource)){
			resource.detach(this);
			setSizeInBytes(getSizeInBytes() - resource.getSizeInBytes());
			return true;
		}
		return false;
	}

	@Override
	public Set<Resource> getChildResources() {
		return Set.copyOf(childResources);
	}

	@Override
	public final boolean contains(Resource resource) {
		if(resource == null)
			return false;

		boolean isPresent = childResources.contains(resource);
		if (!isPresent) {
			Iterator<Resource> childResourcesIterator = childResources.iterator();
			do {
				Resource nextResource = childResourcesIterator.next();
				if (nextResource instanceof Folder)
					isPresent = ((Folder) nextResource).contains(resource);
			} while (!isPresent && childResourcesIterator.hasNext());
		}
		return isPresent;
	}

	@Override
	public final boolean containsOrEquals(Resource resource){
		if(equals(resource)) // TODO document this (method depends on equals(): FBC)
			return true;
		return contains(resource);
	}

	@Override
	public final boolean containsAll(Set<Resource> resources){
		if(resources == null)
			throw new IllegalArgumentException("resources cannot be null");

		for(Resource resource : resources)
			if(!contains(resource))
				return false;
		return true;
	}

	@Override
	public final boolean containsNone(Set<Resource> resources){
		if(resources == null)
			throw new IllegalArgumentException("resources cannot be null");

		for(Resource resource : resources)
			if(contains(resource))
				return false;
		return true;
	}

	@Override
	public boolean addTag(Tag tag, Participant assigner){
		boolean atLeastOneAdded = false || super.addTag(tag, assigner);
		for(Resource childResource : childResources)
			atLeastOneAdded |= childResource.addTag(tag, assigner);
		return atLeastOneAdded;
	}

	@Override
	public boolean removeTag(Tag tag) {
		boolean atLeastOneRemoved = false;
		atLeastOneRemoved |= super.removeTag(tag);
		for(Resource childResource : childResources)
			atLeastOneRemoved |= childResource.removeTag(tag);
		return atLeastOneRemoved;
	}

	@Override
	public Set<Tag> getTags() {
		Set<Tag> tags = new HashSet<>();
		tags.addAll(super.getTags());
		for(Resource childResource : childResources)
			tags.addAll(childResource.getTags());
		return tags;
	}

	@Override
	public Set<TagAssignment> getTagAssignments(){
		Set<TagAssignment> tagAssignments = new HashSet<>();
		tagAssignments.addAll(super.getTagAssignments());
		for(Resource childResource : childResources)
			tagAssignments.addAll(childResource.getTagAssignments());
		return tagAssignments;
	}

	@Override
	public void update(Tag tag, Participant assigner, UpdateAction action) {
		if(tag == null)
			throw new IllegalArgumentException("tag cannot be null");
		if(action == null)
			throw new IllegalArgumentException("action cannot be null");
		if(action == UpdateAction.ADDED && assigner == null)
			throw new IllegalArgumentException("assigner cannot be null");

		boolean changed = false;
		switch (action) {
			case ADDED:
				changed = super.addTag(tag, assigner);
				break;
			case REMOVED:
				changed = super.removeTag(tag);
				break;
			default:
				throw new UnsupportedOperationException("unsupported action: " + action.toString());
		}
		if(changed)
			notifyObservers(tag, assigner, action);
	}
}
