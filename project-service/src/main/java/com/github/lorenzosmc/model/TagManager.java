package com.github.lorenzosmc.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Embeddable
public class TagManager {
    @Enumerated(EnumType.STRING)
    @NotNull
    private TagType tagsType;

    @ManyToMany
    private Set<TagAssignment> tagAssignments;

    public TagManager(TagType tagsType){
        this.tagsType = TagType.valueOf(tagsType.name());
        this.tagAssignments = new HashSet<>();
    }

    public boolean addTag(Tag tag, Participant assigner){
        if(tag.getType() == tagsType)
            return tagAssignments.add(new TagAssignment(tag, assigner));
        else
            throw new IllegalArgumentException("tag has type: " + tag.getType().toString() + " expected: " + tagsType.toString());
    }

    public boolean removeTag(Tag tag){
        if(tag.getType() == tagsType)
            return tagAssignments.removeIf(tagAssignment -> tagAssignment.getTag().equals(tag));
        return false;
    }

    public Set<Tag> getTags(){
        return tagAssignments.stream()
                .map(TagAssignment::getTag)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<TagAssignment> getTagAssignments(){
        return Set.copyOf(tagAssignments);
    }
}
