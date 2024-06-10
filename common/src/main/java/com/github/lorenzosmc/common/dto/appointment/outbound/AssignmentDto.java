package com.github.lorenzosmc.common.dto.appointment.outbound;

import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;

public class AssignmentDto {
	private int progress;
	
	private Long assignmentDate;
	
	private TaskDto assignedTaskDto;

	private String assignedWorkgroupUuid;
	
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Long getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Long assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public TaskDto getAssignedTaskDto() {
		return assignedTaskDto;
	}

	public void setAssignedTaskDto(TaskDto assignedTaskDto) {
		this.assignedTaskDto = assignedTaskDto;
	}

	public String getAssignedWorkgroupUuid() {
		return assignedWorkgroupUuid;
	}

	public void setAssignedWorkgroupUuid(String assignedWorkgroupUuid) {
		this.assignedWorkgroupUuid = assignedWorkgroupUuid;
	}
}
