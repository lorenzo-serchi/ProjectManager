package com.github.lorenzosmc.common.service;

import com.github.lorenzosmc.common.dto.user.inbound.UserAuthenticationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserCreationDto;
import com.github.lorenzosmc.common.dto.user.inbound.UserQueryDto;
import com.github.lorenzosmc.common.dto.user.outbound.UserDto;
import com.github.lorenzosmc.common.exception.IllegalUpdateException;

import java.util.List;
import java.util.UUID;

public interface UserService {
	public List<UserDto> findAll(UserQueryDto query);
	public UserDto findByUuid(UUID userUuid);
	public UserDto findByUsername(String username);
	public UserDto create(UserCreationDto userDto);
	public UserDto update(UUID userUuid, UserDto userDto) throws IllegalUpdateException;
	public boolean verifyCredentials(UserAuthenticationDto userDto);
}
