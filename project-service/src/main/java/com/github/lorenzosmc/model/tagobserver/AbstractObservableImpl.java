package com.github.lorenzosmc.model.tagobserver;

import com.github.lorenzosmc.model.AbstractResourceImpl;
import com.github.lorenzosmc.common.model.BaseEntity;
import com.github.lorenzosmc.model.Participant;
import com.github.lorenzosmc.model.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class AbstractObservableImpl extends BaseEntity implements Observable{
    @ManyToMany(targetEntity = AbstractResourceImpl.class)
    private Set<Observer> observers;

    protected AbstractObservableImpl(){}

    public AbstractObservableImpl(UUID uuid){
        super(uuid);
        observers = new HashSet<>();
    }

    public boolean attach(Observer observer){
        return observers.add(observer);
    }

    public boolean detach(Observer observer){
        return observers.remove(observer);
    }

    // FIXME can't make protected
    public void notifyObservers(Tag tag, Participant assigner, UpdateAction action){
        for(Observer observer : observers)
            observer.update(tag, assigner, action);
    }
}
