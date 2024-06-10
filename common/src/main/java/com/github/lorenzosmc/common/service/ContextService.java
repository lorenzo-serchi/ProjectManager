package com.github.lorenzosmc.common.service;

import com.github.lorenzosmc.common.dto.context.inbound.ContextCreationDto;
import com.github.lorenzosmc.common.dto.context.outbound.ContextDto;
import com.github.lorenzosmc.common.dto.context.outbound.ContextParticipationDto;
import com.github.lorenzosmc.common.exception.IllegalUpdateException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ContextService {
	public List<ContextDto> findAllContexts(Map<String, String> queryParamas);
	public ContextDto findContextByUuid(UUID contextUuid);
	public ContextDto createContext(ContextCreationDto contextDto);
	public ContextDto updateContext(UUID contextUuid, ContextDto contextDto) throws IllegalUpdateException;
	public ContextParticipationDto addParticipant();
	public boolean isUserProfessorOfContext(UUID userUuid, UUID contextUuid);
	public boolean isUserParticipantOfContext(UUID userUuid, UUID contextUuid);
	public boolean verifyCredentials(String password);
}
