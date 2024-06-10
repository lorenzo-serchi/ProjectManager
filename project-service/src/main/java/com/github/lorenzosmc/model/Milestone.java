package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Milestone extends BaseEntity {
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "description cannot be blank")
    private String description;

    // TODO potentially AI generated summary of past checkpoints, discussions, meetings, file uploads, ...
    private String summary;

    @PastOrPresent(message = "creation date must be a past or present date")
    @NotNull
    private Instant creationDate;

    // FIXME not everyone can create a Milestone... where does this rule go? (constructor, service layer, ModelFactory...)
    @ManyToOne(optional = false)
    private Participant creator;

    protected Milestone(){}

    public Milestone(UUID uuid, String name, String description, String summary, Participant creator){
        super(uuid);
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.creationDate = Instant.now();
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Participant getCreator() {
        return creator;
    }
}
