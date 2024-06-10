package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Message extends BaseEntity {
	@NotBlank(message = "text cannot be blank")
	private String text;

	@PastOrPresent(message = "creation date must be a past or present date")
	@NotNull
	private Instant creationDate;
	
	@ManyToOne(optional = false)
	private Participant creator;
	
	@ManyToOne
	private Message replyTo;
	
	@OneToMany(mappedBy = Message_.REPLY_TO)
	private List<Message> replies;

	//TODO override equals() and hashCode()

	protected Message(){}
	
	public Message(UUID uuid, String text, Participant creator) {
		super(uuid);
		this.text = text;
		this.creationDate = Instant.now();
		this.creator = creator;
		this.replies = new ArrayList<>();
	}

	public boolean addReply (Message reply) {
		return replies.add(reply);
	}
		
	public boolean removeReply (Message reply) {
		return replies.remove(reply);
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

	public Participant getCreator() {
		return creator;
	}

	public void setCreator(Participant creator) {
		this.creator = creator;
	}

	public Message getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(Message replyTo) {
		this.replyTo = replyTo;
	}

}
