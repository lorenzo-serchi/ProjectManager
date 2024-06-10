package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.user.inbound.UserAuthenticationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserCreationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserQueryDto;
import com.github.lorenzosmc.common.dto.user.outbound.UserDto;
import com.github.lorenzosmc.common.event.UserCreatedEvent;
import com.github.lorenzosmc.common.exception.IllegalUpdateException;
import com.github.lorenzosmc.common.security.PasswordHashingUtility;
import com.github.lorenzosmc.common.service.UserService;
import com.github.lorenzosmc.dao.UserDao;
import com.github.lorenzosmc.dao.UserQuery;
import com.github.lorenzosmc.model.User;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserServiceLocalImpl implements UserService {
	@Inject
	private UserController userController;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private UserMapper userMapper;
	
	@Inject
	private PasswordHashingUtility passwordHasher;

	@Inject
	private Event<UserCreatedEvent> userCreatedEvent;
	
	@Override
	public List<UserDto> findAll(UserQueryDto queryDto){
		//TODO input validation
		
		UserQuery query = userMapper.translateQuery(queryDto);
		List<User> userEntities = userDao.findAll(query);
		
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for(User userEntity : userEntities)
			userDtos.add(userMapper.convertUser(userEntity));
	
		return userDtos;
	}
	
	//FIXME make another method for smaller UserDto
	@Override
	public UserDto findByUuid(UUID userUuid) {
		//TODO input validation
		User userEntity = userDao.findByUuid(userUuid);
			
		return (userEntity == null) ? null : userMapper.convertUser(userEntity); 
	}
		
	@Override
	public UserDto findByUsername(String username) {
		//TODO input validation
		User userEntity = userDao.findByUsername(username);
		
		return (userEntity == null) ? null : userMapper.convertUser(userEntity); 
	}
	
	@Override
	public UserDto create(UserCreationDto userDto) throws IllegalArgumentException {
		//TODO input validation
		
		UserDto createdUserDto = userController.createUser(userDto);
		
		userCreatedEvent.fire(new UserCreatedEvent(createdUserDto));
		
		return createdUserDto;
	}
	
	@Override
	@Transactional
	public UserDto update(UUID userUuid, UserDto userDto) throws IllegalUpdateException {
		//TODO input validation
		User userEntity = userDao.findByUuid(userUuid);

		if(allowedUpdate(userDto, userEntity))
			try {
				userMapper.transferUserDto(userDto, userEntity);
			} catch (IllegalArgumentException e) { throw new IllegalUpdateException(e.getMessage());}
		
		return userMapper.convertUser(userEntity);
	}
	
	@Override
	public boolean verifyCredentials(UserAuthenticationDto userDto) {
		//TODO input validation
		User userEntity = userDao.findByUsername(userDto.getUsername());
		
		String inputPassword = userDto.getPassword();
		
		String storedPasswordHash;
		
		//addresses username enumeration attack
		if(userEntity != null) {
			storedPasswordHash = userEntity.getPasswordHash();
			
			return passwordHasher.verifyPassword(inputPassword, storedPasswordHash);
		}else {
			//hash of 'password' (just for compute time - username enumeration attack)
			storedPasswordHash = "$2a$14$FgISC/Pt4tQ92aiJgkR9Wu9lTQBL6AfMGDWeZuOIvVNS0qukZ8am.";
			passwordHasher.verifyPassword(inputPassword, storedPasswordHash);
			
			return false;
		}
	}
	
	//[microservice] part of (asynchronous) event queue processing to align local representation
//	public void onContextCreation(@Observes ContextCreatedEvent event) {
//		//TODO input validation
//		//TODO implement
//		System.out.println("[user service] adding context to user's createdContexts");
//	}
//
//	public void onTaskCreation(@Observes TaskCreatedEvent event) {
//		//TODO input validation
//		//TODO implement
//		System.out.println("[user service] adding task to user's createdTasks");
//	}
//
//	public void onWorkgroupCreation(@Observes WorkgroupCreatedEvent event) {
//		//TODO input validation
//		//TODO implement
//		System.out.println("[user service] adding workgroup to user's createdWorkgroups");
//	}

	//FIXME move to domain model
	private boolean allowedUpdate(UserDto updatedUserDto, User userEntity) throws IllegalUpdateException {
		//TODO input validation
		UserDto originalUserDto = userMapper.convertUser(userEntity);
		
		if(!originalUserDto.getUuid().equals(updatedUserDto.getUuid()))
			throw new IllegalUpdateException("Cannot modify 'id' of existing user.");
		if(!originalUserDto.getAcademicEmail().equals(updatedUserDto.getAcademicEmail()))
			throw new IllegalUpdateException("Cannot modify 'acadmic e-mail' of existing user.");
		if(!originalUserDto.getName().equals(updatedUserDto.getName()))
			throw new IllegalUpdateException("Cannot modify 'name' of existing user.");
		if(!originalUserDto.getSurname().equals(updatedUserDto.getSurname()))
			throw new IllegalUpdateException("Cannot modify 'surname' of existing user.");
		if(!originalUserDto.getSurname().equals(updatedUserDto.getSurname()))
			throw new IllegalUpdateException("Cannot modify 'username' of existing user.");
		if(!originalUserDto.getCreationDate().equals(updatedUserDto.getCreationDate()))
			throw new IllegalUpdateException("Cannot modify 'creation date' of existing user.");
		if(!originalUserDto.getRole().equals(updatedUserDto.getRole()))
			throw new IllegalUpdateException("Cannot modify 'role' of existing user.");
		
		return true;
	}
}
