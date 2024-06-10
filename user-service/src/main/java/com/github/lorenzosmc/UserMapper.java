package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.user.inbound.UserCreationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserQueryDto;
import com.github.lorenzosmc.common.dto.user.outbound.UserDto;
import com.github.lorenzosmc.common.exception.MapperConversionException;
import com.github.lorenzosmc.common.security.PasswordHashingUtility;
import com.github.lorenzosmc.dao.UserQuery;
import com.github.lorenzosmc.model.Role;
import com.github.lorenzosmc.model.User;
import jakarta.inject.Inject;

import java.time.Instant;

//FIXME code duplication: (can you?) use generics, inheritance and make an interface for Mappers (like DAOs).
public class UserMapper {
	@Inject
	private PasswordHashingUtility passwordHasher;

	public UserDto convertUser(User userEntity) {
		if (userEntity == null)
			throw new MapperConversionException("The user Entity is NULL");

		UserDto userDto = new UserDto();

		//TODO builder pattern
		userDto.setUuid(userEntity.getUuid());
		userDto.setAcademicEmail(userEntity.getAcademicEmail());
		userDto.setSecondaryEmail(userEntity.getSecondaryEmail());
		userDto.setName(userEntity.getName());
		userDto.setSurname(userEntity.getSurname());
		userDto.setPhoneNumber(userEntity.getPhoneNumber());
		userDto.setUsername(userEntity.getUsername());
		userDto.setCreationDate(userEntity.getCreationDate().toEpochMilli());
		userDto.setProfilePicture(userEntity.getProfilePicture());
		userDto.setAbout(userEntity.getAbout());
		userDto.setVerified(userEntity.isVerified());
		userDto.setRole(serializeRole(userEntity.getRole()));

		return userDto;
	}
	
	public void transferUserDto(UserDto userDto, User userEntity) throws IllegalArgumentException {
		if (userDto == null)
			throw new MapperConversionException("The user DTO is NULL");

		if (userEntity == null)
			throw new MapperConversionException("The user Entity is NULL");

		//TODO builder pattern
		userEntity.setAcademicEmail(userDto.getAcademicEmail());
		userEntity.setSecondaryEmail(userDto.getSecondaryEmail());
		userEntity.setName(userDto.getName());
		userEntity.setSurname(userDto.getSurname());
		userEntity.setPhoneNumber(userDto.getPhoneNumber());
		userEntity.setUsername(userDto.getUsername());
		userEntity.setProfilePicture(userDto.getProfilePicture());
		userEntity.setAbout(userDto.getAbout());
		userEntity.setVerified(userDto.isVerified());
		userEntity.setRole(deserializeRole(userDto.getRole()));
	}

	public void transferUserCreationDto(UserCreationDto userDto, User userEntity) throws IllegalArgumentException {
		if (userDto == null)
			throw new MapperConversionException("The user DTO is NULL");

		if (userEntity == null)
			throw new MapperConversionException("The user Entity is NULL");

		//TODO builder pattern
		userEntity.setAcademicEmail(userDto.getAcademicEmail());
		userEntity.setName(userDto.getName());
		userEntity.setSurname(userDto.getSurname());
		userEntity.setUsername(userDto.getUsername());

		String passwordHash = passwordHasher.hashPassword(userDto.getPassword());
		userEntity.setPasswordHash(passwordHash);

		userEntity.setRole(deserializeRole(userDto.getRole()));
	}
	
	private String serializeRole(Role role) {
		return role.toString();
	}
	
	private Role deserializeRole(String roleString) {
		Role role = null;
		
		if(roleString != null) {
			try {
				role = Role.valueOf(roleString);
			}catch(IllegalArgumentException e) {
				throw new IllegalArgumentException("no such role: " + role);
			}
	}
		
		return role;
	}
	
	
	public UserQuery translateQuery(UserQueryDto queryDto){
		UserQuery query = null;
		
		if(queryDto != null) {
			query = new UserQuery();
			
			query.setName(queryDto.getName());
			query.setSurname(queryDto.getSurname());
			query.setVerified(queryDto.getVerified());
			if(queryDto.getCreatedBefore() != null)
				query.setCreatedBefore(Instant.ofEpochMilli(queryDto.getCreatedBefore()));
			if(queryDto.getCreatedAfter() != null)
				query.setCreatedAfter(Instant.ofEpochMilli(queryDto.getCreatedAfter()));
			query.setRole(deserializeRole(queryDto.getRole()));
		}
		
		return query;
	}
}
