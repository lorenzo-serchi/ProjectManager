package com.github.lorenzosmc.model.tagobserver;

import com.github.lorenzosmc.model.Participant;
import com.github.lorenzosmc.model.Tag;

public interface Observable {
   boolean attach(Observer observer);
   boolean detach(Observer observer);
   void notifyObservers(Tag tag, Participant assigner, UpdateAction action); // FIXME can't make protected
}
