package com.github.lorenzosmc.common.dto.appointment.outbound;

import com.github.lorenzosmc.common.dto.user.outbound.UserDto;

public class ParticipationDto {
	private String appointmentUuid;
	
	private UserDto participantDto;
	
	private AssignmentDto assignmentDto;
	
	private Boolean mandatory;
	
	private String role;

	public String getAppointmentUuid() {
		return appointmentUuid;
	}

	public void setAppointmentUuid(String appointmentUuid) {
		this.appointmentUuid = appointmentUuid;
	}

	public UserDto getParticipantDto() {
		return participantDto;
	}

	public void setParticipantDto(UserDto participantDto) {
		this.participantDto = participantDto;
	}

	public AssignmentDto getAssignmentDto() {
		return assignmentDto;
	}

	public void setAssignmentDto(AssignmentDto assignmentDto) {
		this.assignmentDto = assignmentDto;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
