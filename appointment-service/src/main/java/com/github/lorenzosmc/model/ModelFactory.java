package com.github.lorenzosmc.model;


import com.github.lorenzosmc.common.model.AbstractModelFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class ModelFactory extends AbstractModelFactory {
	private ModelFactory() {};
	
	public static Appointment createExam(User creator, Instant startDate, Duration duration, String location) {
		Appointment exam = createAppointment(AppointmentType.EXAM, creator, startDate, duration, location);
		exam.setExamPassed(false);
		
		return exam;
	}
	
	public static Appointment createMeeting(User creator, Instant startDate, Duration duration, String location, String topic, String detailedTopic) {
		if (topic == null)
			throw new IllegalArgumentException("topic cannot be NULL");
		
		if (detailedTopic == null)
			throw new IllegalArgumentException("detailedTopic cannot be NULL");
		
		Appointment meeting = createAppointment(AppointmentType.MEETING, creator, startDate, duration, location);
		meeting.setMeetingTopic(topic);
		meeting.setMeetingTopicDetailed(detailedTopic);
		
		return meeting;
	}
	
	private static Appointment createAppointment(AppointmentType type, User creator, Instant startDate, Duration duration, String location) {
		if (type == null)
			throw new IllegalArgumentException("appointment type cannot be NULL");
		
		if (creator == null)
			throw new IllegalArgumentException("creator cannot be NULL");

		if (startDate == null)
			throw new IllegalArgumentException("startDate cannot be NULL");
		
		if (duration == null)
			throw new IllegalArgumentException("duration cannot be NULL");

		if (location == null)
			throw new IllegalArgumentException("location cannot be NULL");
		
		Appointment appointment = new Appointment(generateUuid());
		appointment.setType(type);
		appointment.setStartDate(startDate);
		appointment.setDuration(duration);
		appointment.setLocation(location);
		appointment.setStatus(AppointmentStatus.AVAILABLE);
		appointment.setCreator(creator);
		
		return appointment;
	}
	
	public static Context createContext(UUID uuid) {
		return new Context(uuid);
	}

	public static Participation createParticipation(Appointment appointment, User participant, Role role) {
		if (appointment == null)
			throw new IllegalArgumentException("appointment cannot be NULL");
		
		if (participant == null)
			throw new IllegalArgumentException("participant cannot be NULL");

		if (role == null)
			throw new IllegalArgumentException("participant role cannot be NULL");
		
		Participation participation = new Participation(generateUuid());
		participation.setRole(role);
		participation.setMandatory(false);
		participation.setAppointment(appointment);
		participation.setParticipant(participant);
		
		return participation;
	}
	
	public static User createUser(UUID uuid) {
		return new User(uuid);
	}
	
	public static Workgroup createWorkgroup(UUID uuid) {
		return new Workgroup(uuid);
	}
}
