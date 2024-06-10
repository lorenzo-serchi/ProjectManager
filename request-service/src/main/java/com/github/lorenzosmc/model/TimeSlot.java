package com.github.lorenzosmc.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.Instant;

@Embeddable
public class TimeSlot {
	@FutureOrPresent
	@NotNull
	private Instant startDate;

	@NotNull
	private Duration duration;

	// TODO override equals() and hashCode()

	public boolean overlaps(TimeSlot other) {
		Instant endDate = startDate.plus(duration);
		Instant otherEndDate = other.startDate.plus(other.duration);
		
		return startDate.isBefore(otherEndDate) && endDate.isAfter(other.startDate);
	}
	
	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
