package com.github.lorenzosmc.common.dto.project.inbound;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.PathParam;

import java.util.UUID;

public class TaskCreationDto {
	@NotBlank(message = "name cannot be blank")
	private String name;

	@NotBlank(message = "statement cannot be blank")
	private String statement;
	
	private Boolean visible;
	
	private Boolean drafted;

	@JsonIgnore
	@PathParam("contextUuid")
	@NotNull(message = "context uuid cannot be null")
	UUID contextUuid;

	@JsonIgnore
	@NotNull(message = "creator uuid cannot be null")
	UUID creatorUuid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getDrafted() {
		return drafted;
	}

	public void setDrafted(Boolean drafted) {
		this.drafted = drafted;
	}

	public UUID getContextUuid() {
		return contextUuid;
	}

	public void setContextUuid(UUID contextUuid) {
		this.contextUuid = contextUuid;
	}

	public UUID getCreatorUuid() {
		return creatorUuid;
	}

	public void setCreatorUuid(UUID creatorUuid) {
		this.creatorUuid = creatorUuid;
	}
}
