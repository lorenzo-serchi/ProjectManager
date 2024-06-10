package com.github.lorenzosmc.common.dto.project.outbound;

import java.time.Instant;

public class MessageDto{
	private String uuid;
	
	private String text;
	
	private Instant creationDate;
	
	private String creatorUuid;
	
	private String replyToUuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatorUuid() {
		return creatorUuid;
	}

	public void setCreatorUuid(String creatorUuid) {
		this.creatorUuid = creatorUuid;
	}

	public String getReplyToUuid() {
		return replyToUuid;
	}

	public void setReplyToUuid(String replyToUuid) {
		this.replyToUuid = replyToUuid;
	}

	
}
