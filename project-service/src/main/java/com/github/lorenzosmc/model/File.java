package com.github.lorenzosmc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

@Entity
public class File extends AbstractResourceImpl {
	@NotNull(message = "file data cannot be null")
	private byte[] data;

	//FIXME media type validation
	@NotBlank(message = "media type cannot be blank")
	private String mediaType;

	@PositiveOrZero(message = "revision number must be greater than or equal to zero")
	private Integer revisionNumber;

	@OneToOne
	private File previousRevision;

	@OneToOne(mappedBy = File_.PREVIOUS_REVISION, cascade = CascadeType.REMOVE)
	private File nextRevision;

	protected File() {}

	public File(UUID uuid, String name, byte[] data, String mediaType) {
		super(uuid, name);
		this.data = data.clone();
		this.mediaType = mediaType;
	}

	public byte[] getData() {
		return data.clone();
	}

	public String getMediaType() {
		return mediaType;
	}

	@Override
	public final boolean containsOrEquals(Resource resource){
		// TODO document this (method depends on equals(): FBC)
		return equals(resource);
	}

	@Override
	public int getRevisionNumber(){
		return revisionNumber;
	}

	@Override
	public File getPreviousRevision() {
		return previousRevision;
	}

	@Override
	public void setPreviousRevision(Resource resource) {
		if(resource == null)
			throw new IllegalArgumentException("resource cannot be null");

		if(!(resource instanceof File))
			throw new IllegalArgumentException("resource must be a File");
		File file = (File) resource;

		if(file.nextRevision != null)
			throw new IllegalArgumentException("resource already has a next revision");

		this.previousRevision = file;
		file.nextRevision = this;
		this.revisionNumber = file.revisionNumber + 1;
	}

	@Override
	public File getNextRevision() {
		return nextRevision;
	}
}
