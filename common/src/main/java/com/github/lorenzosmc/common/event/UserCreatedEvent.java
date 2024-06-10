package com.github.lorenzosmc.common.event;

import com.github.lorenzosmc.common.dto.user.outbound.UserDto;

public class UserCreatedEvent {
	private UserDto userDto;
	
	public UserCreatedEvent(UserDto userDto) {
		this.setUserDto(userDto);
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
}
