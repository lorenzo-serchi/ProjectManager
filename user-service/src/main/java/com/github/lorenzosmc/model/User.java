package com.github.lorenzosmc.model;

import com.github.lorenzosmc.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;
import java.util.UUID;

@Entity
public class User extends BaseEntity {
	private String academicEmail;

	private String secondaryEmail;

	private String name;

	private String surname;

	private String phoneNumber;

	private String username;

	private String passwordHash;

	private byte[] profilePicture;

	private String about;

	private boolean verified;

	@Enumerated(EnumType.STRING)
	private Role role;

	private Instant creationDate;

	// TODO override equals() and hashCode()

	protected User() {}

	public User(UUID uuid) {
		super(uuid);
		creationDate = Instant.now();
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getProfilePicture() {
		// FIXME defensive-copy
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		// FIXME defensive-copy
		this.profilePicture = profilePicture;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Role getRole() {
		// FIXME defensive-copy
		return role;
	}

	public void setRole(Role role) {
		// FIXME defensive-copy
		this.role = role;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

}
