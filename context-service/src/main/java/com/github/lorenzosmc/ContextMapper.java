package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.context.outbound.ContextDto;
import com.github.lorenzosmc.common.dto.context.inbound.ContextCreationDto;
import com.github.lorenzosmc.common.exception.MapperConversionException;
import com.github.lorenzosmc.common.security.PasswordHashingUtility;
import com.github.lorenzosmc.dao.UserDao;
import com.github.lorenzosmc.model.Context;
import jakarta.inject.Inject;

public class ContextMapper {
	@Inject
	UserDao userDao;
	
	@Inject
	private PasswordHashingUtility passwordHasher;

	public ContextDto convertContext(Context context) {
		if (context == null)
			throw new MapperConversionException("The context Entity is NULL");

		ContextDto contextDto = new ContextDto();

		contextDto.setUuid(context.getUuid());
		contextDto.setName(context.getName());
		contextDto.setDescription(context.getDescription());
		contextDto.setCreatorUuid(context.getCreator().getUuid());
		contextDto.setCreationDate(context.getCreationDate().toEpochMilli());
		contextDto.setLocked(context.isLocked());
		contextDto.setHidden(context.isHidden());

		return contextDto;
	}
	
	public void transferContextDto(ContextDto contextDto, Context context) {
		if (contextDto == null)
			throw new MapperConversionException("The context DTO is NULL");

		if (context == null)
			throw new MapperConversionException("The context Entity is NULL");
		
		context.setName(contextDto.getName());
		context.setDescription(contextDto.getDescription());
		context.setCreator(userDao.findByUuid(contextDto.getCreatorUuid()));
		context.setLocked(contextDto.getLocked());
		context.setHidden(contextDto.getHidden());
	}
	
	//FIXME missing password
	public void transferContextCreationDto(ContextCreationDto contextDto, Context context) {
		if (contextDto == null)
			throw new MapperConversionException("The context DTO is NULL");

		if (context == null)
			throw new MapperConversionException("The context Entity is NULL");

		context.setName(contextDto.getName());
		context.setDescription(contextDto.getDescription());
		context.setStudentPasswordHash(passwordHasher.hashPassword(contextDto.getPassword()));
		context.setLocked(contextDto.getLocked());
		context.setHidden(contextDto.getHidden());
	}
}
