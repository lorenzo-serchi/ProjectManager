package com.github.lorenzosmc.common.event;

import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;

public class TaskCreatedEvent {
	private TaskDto taskDto;

	public TaskCreatedEvent(TaskDto taskDto) {
		this.taskDto = taskDto;
	}

	public TaskDto getTaskDto() {
		return taskDto;
	}

	public void setTaskDto(TaskDto taskDto) {
		this.taskDto = taskDto;
	}
	
}
