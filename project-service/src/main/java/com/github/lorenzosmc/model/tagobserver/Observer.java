package com.github.lorenzosmc.model.tagobserver;

import com.github.lorenzosmc.model.Participant;
import com.github.lorenzosmc.model.Tag;

public interface Observer {
    public void update(Tag tag, Participant assigner, UpdateAction action);
}
