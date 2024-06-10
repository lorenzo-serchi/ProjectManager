package com.github.lorenzosmc.common.event;

import com.github.lorenzosmc.common.dto.workgroup.outbound.WorkgroupDto;

public class WorkgroupCreatedEvent {
	private WorkgroupDto workgroupDto;
	
	public WorkgroupCreatedEvent(WorkgroupDto workgroupDto) {
		this.workgroupDto = workgroupDto;
	}
	
	public WorkgroupDto getWorkgroupDto() {
		return workgroupDto;
	}

	public void setWorkgroupDto(WorkgroupDto workgroupDto) {
		this.workgroupDto = workgroupDto;
	}
	
}
