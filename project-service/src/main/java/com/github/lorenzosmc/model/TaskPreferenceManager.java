package com.github.lorenzosmc.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MapKeyColumn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Embeddable
public class TaskPreferenceManager {
    @ElementCollection
    @MapKeyColumn(name = "preference")
    @Column(name = "value")
    private Map<String, Boolean> preferences = new HashMap<>();

    private static final Set<String> validPreferences = new HashSet<>(){{
        add("checkpoint.approval.mandatory");
    }};

    public boolean setPreference(String preference, boolean value){
        if(isValidPreference(preference)){
            preferences.put(preference, value);
            return true;
        }
        return false;
    }

    public Map<String, Boolean> getPreferences(){
        return Map.copyOf(preferences);
    }

    private boolean isValidPreference(String preference){
        return validPreferences.contains(preference);
    }
}
