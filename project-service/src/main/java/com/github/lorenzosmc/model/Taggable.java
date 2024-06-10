package com.github.lorenzosmc.model;

import java.util.Set;

public interface Taggable {
    boolean addTag(Tag tag, Participant assigner);
    boolean removeTag(Tag tag);
    Set<Tag> getTags();
    Set<TagAssignment> getTagAssignments();
}
