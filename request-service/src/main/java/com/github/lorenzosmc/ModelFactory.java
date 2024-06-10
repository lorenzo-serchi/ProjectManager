package com.github.lorenzosmc;

import com.github.lorenzosmc.common.model.AbstractModelFactory;
import com.github.lorenzosmc.model.*;

import java.util.Set;
import java.util.UUID;

public class ModelFactory extends AbstractModelFactory {
	
	private ModelFactory() {};
	
	public static MeetingProposal createMeetingProposal(Set<TimeSlot> timeSlots) {
		if(timeSlots == null)
			throw new IllegalArgumentException("timeSlots cannot be null");
		
		MeetingProposal meetingProposal = new MeetingProposal();
		meetingProposal.setWithCollaborators(false);
		meetingProposal.addTimeSlots(timeSlots);
		
		return null;
	}
	
	public static Participant createParticipation(Request request, UUID participantUuid) {
		if(request == null)
			throw new IllegalArgumentException("request cannot be null");

		if(participantUuid == null)
			throw new IllegalArgumentException("participant uuid cannot be null");
		
		Participant participant = new Participant(generateUuid());
		participant.setRequest(request);
		participant.setUserUuid(participantUuid);
		
		return participant;
	}
	
	public static Request createRequest(UUID contextUuid, RequestType type) {
		if(contextUuid == null)
			throw new IllegalArgumentException("context uuid cannot be null");

		if(type == null)
			throw new IllegalArgumentException("request type cannot be null");
		
		Request request = new Request(generateUuid());
		request.setType(type);
		request.setStatus(RequestStatus.PENDING);
		request.setContextUuid(contextUuid);

		return request;
	}
	
	public static TaskProposal createTaskProposal(UUID taskUuid) {
		if(taskUuid == null)
			throw new IllegalArgumentException("task uuid cannot be null");
		
		TaskProposal taskProposal = new TaskProposal();
		taskProposal.setWithCollaborators(false);
		taskProposal.setProposedTaskUuid(taskUuid);
		
		return taskProposal;
	}
	
	public static TimeSlot createTimeSlot() {
		return new TimeSlot();
	}
}
