package com.github.lorenzosmc.common.dto.context.outbound;

import java.util.UUID;

public class ContextDto {
	private UUID uuid;

	private String name;

	private String description;

	private Boolean locked;

	private Boolean hidden;

	private UUID creatorUuid;

	private Long creationDate;

	public ContextDto() {}

	public ContextDto(ContextDto contextDto) {
		this.uuid = contextDto.uuid;
		this.name = contextDto.name;
		this.locked = contextDto.locked;
		this.hidden = contextDto.hidden;
		this.creatorUuid = contextDto.creatorUuid;
		this.creationDate = contextDto.creationDate;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public UUID getCreatorUuid() {
		return creatorUuid;
	}

	public void setCreatorUuid(UUID creatorUuid) {
		this.creatorUuid = creatorUuid;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
}