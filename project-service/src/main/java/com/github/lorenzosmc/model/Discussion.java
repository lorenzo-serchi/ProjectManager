package com.github.lorenzosmc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Discussion {
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Message> messages;

	//TODO override equals() and hashCode()

	public Discussion(){
		this.messages = new ArrayList<>();
	}

	public Discussion(Discussion other){
		this.messages = new ArrayList<>(other.messages);
	}

	public boolean addMessage(Message message) {
		return messages.add(message);
	}
	
	public boolean removeMessage(Message message) {
		return messages.remove(message);
	}
	
	public List<Message> getMessages() {
		return List.copyOf(messages);
	}
	
	public void setMessages(List<Message> comments) {
		this.messages = new ArrayList<>(comments);
	}
}
