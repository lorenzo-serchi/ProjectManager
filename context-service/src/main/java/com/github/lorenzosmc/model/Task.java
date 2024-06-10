package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Task extends BaseEntity {
	private String name;
	
	private String statement;
	
	private int score;

	// TODO override equals() and hashCode()
	
	protected Task(){}
	
	public Task(UUID uuid) {
		super(uuid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
