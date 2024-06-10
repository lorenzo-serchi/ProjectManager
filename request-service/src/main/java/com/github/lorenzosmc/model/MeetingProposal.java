package com.github.lorenzosmc.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Embeddable
public class MeetingProposal {
	private boolean withCollaborators;
	
	@ElementCollection
	@NotEmpty
	private Set<TimeSlot> timeSlots = new HashSet<>();

	// TODO override equals() and hashCode()

	public boolean addTimeSlots(Set<TimeSlot> timeSlots) {
		//FIXME defensive-copy
		if(timeSlots == null)
			throw new IllegalArgumentException("Set<TimeSlot> cannot be null");
		
		boolean overlaps = false;
		Iterator<TimeSlot> i = timeSlots.iterator();
		while(i.hasNext() && !overlaps)
			overlaps = timeSlotOverlaps(i.next(), timeSlots);
		
		if(!overlaps)
			for(TimeSlot timeSlot : timeSlots)
				this.timeSlots.add(timeSlot);
		
		return !overlaps;			
	}
	
	public boolean addTimeSlot(TimeSlot newTimeSlot) {
		//FIXME defensive-copy
		if(newTimeSlot == null)
			throw new IllegalArgumentException("TimeSlot cannot be null");
		
		return timeSlotOverlaps(newTimeSlot, this.timeSlots) ? false : timeSlots.add(newTimeSlot);
	}
	
	private boolean timeSlotOverlaps(TimeSlot newTimeSlot, Set<TimeSlot> timeSlots) {
		boolean overlaps = false;
		Iterator<TimeSlot> i = timeSlots.iterator();
		while (i.hasNext() && !overlaps)
			overlaps = newTimeSlot.overlaps(i.next());
				
		return overlaps;
	}
	
	public boolean removeTimeSlot(TimeSlot timeSlot) {
		return timeSlots.remove(timeSlot);
	}
		
	public boolean isWithCollaborators() {
		return withCollaborators;
	}

	public void setWithCollaborators(boolean withCollaborators) {
		this.withCollaborators = withCollaborators;
	}

	public Set<TimeSlot> getTimeSlots() {
		//FIXME defensive-copy
		return timeSlots;
	}

	public void setTimeSlots(Set<TimeSlot> timeSlots) {
		//FIXME defensive-copy
		this.timeSlots = timeSlots;
	}
}
