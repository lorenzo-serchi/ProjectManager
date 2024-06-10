package com.github.lorenzosmc.common.dto.user.outbound;

import java.util.UUID;

public class UserDto{
	private UUID uuid;
	
	private String academicEmail;
	
	private String secondaryEmail;
	
	private String name;
	
	private String surname;
	
	private String phoneNumber;

	private String username;
	
	private Long creationDate;
	
	//FIXME don't send profilePicture in DTO. Add HATEOAS for endpoint to get it.
	private byte[] profilePicture;
	
	private String about;

	private Boolean verified;
	
	private String role;
	
	//TODO override equals and hashcode
	
	public UserDto() {}
	
	public UserDto(UserDto userDto) {
		this.uuid = userDto.uuid;
		this.academicEmail = userDto.academicEmail;
		this.secondaryEmail = userDto.secondaryEmail;
		this.name = userDto.name;
		this.surname = userDto.surname;
		this.phoneNumber = userDto.phoneNumber;
		this.username = userDto.username;
		this.creationDate = userDto.creationDate;
		this.profilePicture = userDto.profilePicture;
		this.about = userDto.about;
		this.verified = userDto.verified;
		this.role = userDto.role;
	}

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

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Boolean isVerified() {
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

}
