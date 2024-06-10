package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class File extends BaseEntity {
	private String name;
	
	private int revisionNumber;

	private byte[] data;
	
	private String mediaType;
	
	private int score;
	
	protected File(){}
	
	public File(UUID uuid) {
		super(uuid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(int revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public byte[] getData() {
		//FIXME defensive copy
		return data;
	}

	public void setData(byte[] data) {
		//FIXME defensive copy
		this.data = data;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
