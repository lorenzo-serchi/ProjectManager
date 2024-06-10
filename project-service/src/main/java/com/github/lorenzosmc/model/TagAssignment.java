package com.github.lorenzosmc.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;

@Embeddable
public class TagAssignment {
    @ManyToOne(optional = false)
    private Tag tag;

    @ManyToOne(optional = false)
    private Participant assigner;

    @PastOrPresent(message = "assignment date must be a past or present date")
    @NotNull
    private Instant assignmentDate;

    protected TagAssignment(){}

    public TagAssignment(Tag tag, Participant assigner){
        this.tag = tag;
        this.assigner = assigner;
        this.assignmentDate = Instant.now();
    }

    public Tag getTag(){
        return tag;
    }

    public Participant getAssigner() {
        return assigner;
    }

    public Instant getAssignmentDate() {
        return assignmentDate;
    }
}
