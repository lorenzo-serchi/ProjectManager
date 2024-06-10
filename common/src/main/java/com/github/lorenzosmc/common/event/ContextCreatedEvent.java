package com.github.lorenzosmc.common.event;

import com.github.lorenzosmc.common.dto.context.outbound.ContextDto;

public class ContextCreatedEvent {
	private ContextDto contextDto;

	public ContextCreatedEvent(ContextDto contextDto) {
		this.contextDto = contextDto;
	}
	
	public ContextDto getContextDto() {
		return contextDto;
	}
}
