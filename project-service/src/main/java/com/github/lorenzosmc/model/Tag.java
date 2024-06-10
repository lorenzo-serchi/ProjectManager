package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Tag extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@NotNull
	private TagType type;

	@NotBlank(message = "name cannot be blank")
	private String name;

	protected Tag() {}

	public Tag(UUID uuid, TagType type, String name) {
		super(uuid);
		this.type = TagType.valueOf(type.name());
		this.name = name;
	}

	public TagType getType() {
		return TagType.valueOf(type.name());
	}

	public String getName() {
		return name;
	}
}
