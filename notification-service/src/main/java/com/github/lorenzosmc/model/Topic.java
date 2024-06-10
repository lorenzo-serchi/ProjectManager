package com.github.lorenzosmc.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Topic extends Publisher {
	@Embedded
	UpdateCategory category;
	
	@OneToMany
	private Set<Update> updates;

	// TODO override equals() and hashCode()

	protected Topic(){}

	public Topic(UUID uuid) {
		super(uuid);
		updates = new HashSet<>();
	}
	
	public void addUpdate(Update update) {
		UpdateCategory updateCategory = update.getCategory();
		if(!category.equals(updateCategory))
			throw new IllegalArgumentException(
					"cannot add update of category (" + updateCategory.toString() + ")"
					+ " to topic for updates of category (" + category.toString() + ")");

		updates.add(update);
		notifySubscribers(update);
	}
	
	public UpdateCategory getCategory() {
		//FIXME defensive copy
		return category;
	}

	public void setCategory(UpdateCategory category) {
		//FIXME defensive copy
		this.category = category;
	}

	public Set<Update> getUpdates() {
		return updates;
	}

	public void setUpdates(Set<Update> updates) {
		this.updates = updates;
	}
}
