package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Project extends BaseEntity {
	@NotBlank(message = "title cannot be blank")
	private String title;

	@NotBlank(message = "statement cannot be blank")
	private String statement;

	@ManyToOne(optional = false)
	private User creator;

	@PastOrPresent(message = "creation date must be a past or present date")
	@NotNull
	private Instant creationDate;

	//FIXME is cascade = CascadeType.REMOVE certain?
	@OneToMany(mappedBy = Task_.PROJECT, cascade = CascadeType.REMOVE)
	private Set<Task> tasks;
	
	//TODO override equals() and hashCode()
	
	protected Project(){}
	
	public Project(UUID uuid, String title, String statement, User creator) {
		super(uuid);
		this.title = title;
		this.statement = statement;
		this.creator = creator;
		this.creationDate = Instant.now();
		this.tasks = new HashSet<>();
	}

	public boolean addTask(Task task) {
		return tasks.add(task);
	}
	
	public boolean removeTask(Task task) {
		return tasks.remove(task);
	}
	
	public String getTitle() {
		return title;
	}

	public String getStatement() {
		return statement;
	}

	public User getCreator() {
		return creator;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public Set<Task> getTasks() {
		return Set.copyOf(tasks);
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = new HashSet<>(tasks);
	}

}
