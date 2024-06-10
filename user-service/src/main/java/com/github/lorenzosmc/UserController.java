package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.user.inbound.UserCreationDto;
import com.github.lorenzosmc.common.dto.user.outbound.UserDto;
import com.github.lorenzosmc.dao.UserDao;
import com.github.lorenzosmc.model.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

public class UserController {
	@Inject
	private UserDao userDao;
	
	@Inject
	private UserMapper userMapper;
	
	@Transactional
	public UserDto createUser(UserCreationDto userDto) {
		if(userDto.getAcademicEmail() == null)
			throw new ClientErrorException("user academic email is mandatory", Response.Status.BAD_REQUEST);
		if(userDto.getName() == null)
			throw new ClientErrorException("user name is mandatory", Response.Status.BAD_REQUEST);
		if(userDto.getSurname() == null)
			throw new ClientErrorException("user surname is mandatory", Response.Status.BAD_REQUEST);
		if(userDto.getUsername() == null)
			throw new ClientErrorException("user username is mandatory", Response.Status.BAD_REQUEST);
		if(userDto.getPassword() == null)
			throw new ClientErrorException("user password is mandatory", Response.Status.BAD_REQUEST);
		if(userDto.getRole() == null)
			throw new ClientErrorException("user role is mandatory", Response.Status.BAD_REQUEST);

		User userEntity = ModelFactory.createUser();

		userMapper.transferUserCreationDto(userDto, userEntity);

		userDao.save(userEntity);

		return userMapper.convertUser(userEntity);
	}
}
