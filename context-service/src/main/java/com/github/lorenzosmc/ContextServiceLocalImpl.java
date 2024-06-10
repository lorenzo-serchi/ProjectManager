package com.github.lorenzosmc;

import com.github.lorenzosmc.common.exception.IllegalUpdateException;
import com.github.lorenzosmc.common.dto.context.inbound.ContextCreationDto;
import com.github.lorenzosmc.common.dto.context.outbound.ContextDto;
import com.github.lorenzosmc.common.dto.context.outbound.ContextParticipationDto;
import com.github.lorenzosmc.common.event.ContextCreatedEvent;
import com.github.lorenzosmc.common.service.ContextService;
import com.github.lorenzosmc.dao.ContextDao;
import com.github.lorenzosmc.dao.UserDao;
import com.github.lorenzosmc.model.Context;
import com.github.lorenzosmc.model.ModelFactory;
import com.github.lorenzosmc.model.User;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ContextServiceLocalImpl implements ContextService {
	@Inject
	ContextDao contextDao;
	
	@Inject
	UserDao userDao;
	
	@Inject
	private ContextMapper contextMapper;
	
	@Inject
	Event<ContextCreatedEvent> contextCreatedEvent;
	
	@Override
	public List<ContextDto> findAllContexts(Map<String, String> queryParamas) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ContextDto findContextByUuid(UUID contextUuid) {
		//TODO input validation
		Context context = contextDao.findByUuid(contextUuid);

		return (context == null) ? null : contextMapper.convertContext(context);
	}
	
	@Override
	@Transactional
	public ContextDto createContext(ContextCreationDto contextCreationDto) {
		//TODO input validation
		if(contextCreationDto.getName() == null)
			throw new ClientErrorException("context name is mandatory", Response.Status.BAD_REQUEST);

		// FIXME what if creator doesn't exist? create it.
		User creator = userDao.findByUuid(contextCreationDto.getCreatorUuid());
		Context context = ModelFactory.createContext(creator);
		
		contextMapper.transferContextCreationDto(contextCreationDto, context);
		contextDao.save(context);
		
		ContextDto createdContextDto = contextMapper.convertContext(context);
		contextCreatedEvent.fire(new ContextCreatedEvent(createdContextDto));
				
		return createdContextDto;
	}
	
	@Override
	@Transactional
	public ContextDto updateContext(UUID contextUuid, ContextDto contextDto) throws IllegalUpdateException {
		//TODO input validation
		Context context = contextDao.findByUuid(contextUuid);

		if (allowedUpdate(contextDto, context))
			contextMapper.transferContextDto(contextDto, context);

		return contextMapper.convertContext(context);
	}

	@Override
	public ContextParticipationDto addParticipant() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	//FIXME return the context if true, null if false
	public boolean isUserProfessorOfContext(UUID userUuid, UUID contextUuid) {
		//TODO input validation
		boolean result = false;
		
		Context context = contextDao.findByUuid(contextUuid);
		if(context != null)
			 result = userUuid.equals(context.getCreator().getUuid());
		
		return result;
	}
	
	@Override
	public boolean isUserParticipantOfContext(UUID userUuid, UUID contextUuid) {
		//TODO input validation
		//TODO implement
//		List<User> participants = userDao.findAllByContext(contextUuid);
//		
//		for(ContextParticipation participant : participants) {
//			String participantUuid = participant.getParticipant().getUuid();
//			if(participantUuid.equals(userUuid))
//				return true;
//		}
		
		System.out.println("use findAll with query params");
		return false;
	}
	
	@Override
	public boolean verifyCredentials(String password) {
		//TODO Implement
		return false;
	}
	
//	public void onTaskCreation(@Observes TaskCreatedEvent event) {
//		//TODO input validation
//		System.out.println("[context service] adding task to context");
//	}
//
//	public void onWorkgroupCreation(@Observes WorkgroupCreatedEvent event) {
//		//TODO input validation
//		System.out.println("[context service] adding workgroup to context");
//	}
//
//	@Transactional
//	public void onUserCreation(@Observes UserCreatedEvent event) {
//		User user = ModelFactory.createUser(event.getUserDto().getUuid());
//		userDao.save(user);
//	}
	
	//TODO move in domain model
	private boolean allowedUpdate(ContextDto updatedContextDto, Context context) throws IllegalUpdateException {
		//TODO input validation
		ContextDto originalContextDto = contextMapper.convertContext(context);
			
		if (!originalContextDto.getUuid().equals(updatedContextDto.getUuid()))
			throw new IllegalUpdateException("Cannot modify 'id' of existing context.");
		else if (!originalContextDto.getName().equals(updatedContextDto.getName()))
			throw new IllegalUpdateException("Cannot modify 'name' of existing context.");
		else if (!originalContextDto.getCreatorUuid().equals(updatedContextDto.getCreatorUuid()))
			throw new IllegalUpdateException("Cannot modify 'creator' of existing context.");
		else if (!originalContextDto.getCreationDate().equals(updatedContextDto.getCreationDate()))
			throw new IllegalUpdateException("Cannot modify 'creation date' of existing context.");
			
		return true;
	}
}
