package com.github.lorenzosmc.model;

public interface Discussable {
    boolean addMessage(Message message);
    boolean removeMessage(Message message);
    Discussion getDiscussion();
}
