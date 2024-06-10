package com.github.lorenzosmc.common.dto.user.outbound;

import java.util.List;
import java.util.UUID;

//FIXME this is useless (?)... make UserAppointmentsDto, UserNotificationsDto, ...
public class UserComplexDto {
	private UUID uuid;
	
	private String academicEmail;

	private String secondaryEmail;
	
	private String name;
	
	private String surname;
	
	private String phoneNumber;

	private String username;
	
	private String password;
	
	private byte[] profilePicture;
	
	private Boolean verified;

	private String role;
	
	private List<UUID> readNotificationsIds;

	private List<UUID> unreadNotificationsIds;

	private String about;

	private List<UUID> messagesIds;

	private List<UUID> createdProjectsIds;
	
	private List<UUID> createdTasksIds;
	
	private List<UUID> requestParticipationsIds;
	
	private List<UUID> createdContextsIds;
	
	private List<UUID> contextParticipationsIds;

	private List<UUID> createdAppointmentsIds;
	
	private List<UUID> appointmentParticipationsIds;

	private List<UUID> createdWorkgroupsIds;
	
	private List<UUID> workgroupParticipationsIds;
	
	private List<UUID> collaborationsIds;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getAcademicEmail() {
		return academicEmail;
	}

	public void setAcademicEmail(String academicEmail) {
		this.academicEmail = academicEmail;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UUID> getReadNotificationsIds() {
		return readNotificationsIds;
	}

	public void setReadNotificationsIds(List<UUID> readNotificationsIds) {
		this.readNotificationsIds = readNotificationsIds;
	}

	public List<UUID> getUnreadNotificationsIds() {
		return unreadNotificationsIds;
	}

	public void setUnreadNotificationsIds(List<UUID> unreadNotificationsIds) {
		this.unreadNotificationsIds = unreadNotificationsIds;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<UUID> getMessagesIds() {
		return messagesIds;
	}

	public void setMessagesIds(List<UUID> messagesIds) {
		this.messagesIds = messagesIds;
	}

	public List<UUID> getCreatedProjectsIds() {
		return createdProjectsIds;
	}

	public void setCreatedProjectsIds(List<UUID> createdProjectsIds) {
		this.createdProjectsIds = createdProjectsIds;
	}

	public List<UUID> getCreatedTasksIds() {
		return createdTasksIds;
	}

	public void setCreatedTasksIds(List<UUID> createdTasksIds) {
		this.createdTasksIds = createdTasksIds;
	}

	public List<UUID> getRequestParticipationsIds() {
		return requestParticipationsIds;
	}

	public void setRequestParticipationsIds(List<UUID> requestParticipationsIds) {
		this.requestParticipationsIds = requestParticipationsIds;
	}

	public List<UUID> getCreatedContextsIds() {
		return createdContextsIds;
	}

	public void setCreatedContextsIds(List<UUID> createdContextsIds) {
		this.createdContextsIds = createdContextsIds;
	}

	public List<UUID> getContextParticipationsIds() {
		return contextParticipationsIds;
	}

	public void setContextParticipationsIds(List<UUID> contextParticipationsIds) {
		this.contextParticipationsIds = contextParticipationsIds;
	}

	public List<UUID> getCreatedAppointmentsIds() {
		return createdAppointmentsIds;
	}

	public void setCreatedAppointmentsIds(List<UUID> createdAppointmentsIds) {
		this.createdAppointmentsIds = createdAppointmentsIds;
	}

	public List<UUID> getAppointmentParticipationsIds() {
		return appointmentParticipationsIds;
	}

	public void setAppointmentParticipationsIds(List<UUID> appointmentParticipationsIds) {
		this.appointmentParticipationsIds = appointmentParticipationsIds;
	}

	public List<UUID> getCreatedWorkgroupsIds() {
		return createdWorkgroupsIds;
	}

	public void setCreatedWorkgroupsIds(List<UUID> createdWorkgroupsIds) {
		this.createdWorkgroupsIds = createdWorkgroupsIds;
	}

	public List<UUID> getWorkgroupParticipationsIds() {
		return workgroupParticipationsIds;
	}

	public void setWorkgroupParticipationsIds(List<UUID> workgroupParticipationsIds) {
		this.workgroupParticipationsIds = workgroupParticipationsIds;
	}

	public List<UUID> getCollaborationsIds() {
		return collaborationsIds;
	}

	public void setCollaborationsIds(List<UUID> collaborationsIds) {
		this.collaborationsIds = collaborationsIds;
	}
	
}
