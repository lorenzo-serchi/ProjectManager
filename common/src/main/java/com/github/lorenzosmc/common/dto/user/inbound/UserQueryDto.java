package com.github.lorenzosmc.common.dto.user.inbound;

import jakarta.ws.rs.QueryParam;

public class UserQueryDto {
	@QueryParam("name")
	String name;
	
	@QueryParam("surname")
	String surname;
	
	@QueryParam("verified")
	Boolean verified;
	
	@QueryParam("createdBefore")
	Long createdBefore;
	
	@QueryParam("createdAfter")
	Long createdAfter;
	
	@QueryParam("role")
	String role;

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

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Long getCreatedBefore() {
		return createdBefore;
	}

	public void setCreatedBefore(Long createdBefore) {
		this.createdBefore = createdBefore;
	}

	public Long getCreatedAfter() {
		return createdAfter;
	}

	public void setCreatedAfter(Long createdAfter) {
		this.createdAfter = createdAfter;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
