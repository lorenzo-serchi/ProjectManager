package com.github.lorenzosmc.model;

import com.github.lorenzosmc.model.tagobserver.Observable;

import java.time.Instant;
import java.util.Set;

public interface Resource extends Taggable, Discussable, Observable {
    String getName();
    int getSizeInBytes();
    Participant getCreator();
    Instant getCreationDate();
    Resource getParentResource();
    void setParentResource(Resource resource); // FIXME remove (addResource, removeResource do it)
    default boolean addResource(Resource resource) throws UnsupportedOperationByResourceException {
        throw new UnsupportedOperationByResourceException("unable to add a resource");
    };
    default boolean removeResource(Resource resource) throws UnsupportedOperationByResourceException {
        throw new UnsupportedOperationByResourceException("unable to remove resource");
    };
    default Set<Resource> getChildResources() throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to get child resources");
    };
    default boolean contains(Resource resource) throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to search children");
    };
    boolean containsOrEquals(Resource resource);
    default boolean containsAll(Set<Resource> resource) throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to search children");
    };
    default boolean containsNone(Set<Resource> resource) throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to search children");
    };
    default Resource getPreviousRevision() throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to get previous revision");
    }
    default void setPreviousRevision(Resource resource) throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to set previous revision");
    }
    default Resource getNextRevision() throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to get next revision");
    }
    default int getRevisionNumber() throws UnsupportedOperationByResourceException{
        throw new UnsupportedOperationByResourceException("unable to get revision number");
    }
}
