package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.project.inbound.TaskCreationDto;
import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;
import com.github.lorenzosmc.common.dto.project.outbound.TaskTagAssignmentDto;
import com.github.lorenzosmc.common.exception.MapperConversionException;
import com.github.lorenzosmc.dao.TaskTagAssignmentDao;
import com.github.lorenzosmc.dao.UserDao;
import com.github.lorenzosmc.model.Tag;
import com.github.lorenzosmc.model.TagAssignment;
import com.github.lorenzosmc.model.Task;
import com.github.lorenzosmc.model.TaskStatus;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Set;

public class ModelMapper {
	@Inject
	UserDao userDao;
	
	@Inject
	TaskTagAssignmentDao taskTagAssignmentDao;
	
	public TaskDto toDto(Task task) {
		if (task == null)
			throw new MapperConversionException("The task Entity is null");

		TaskDto taskDto = new TaskDto();

		//TODO builder pattern
		taskDto.setUuid(task.getUuid());
		taskDto.setName(task.getName());
		taskDto.setStatement(task.getStatement());
		taskDto.setContextUuid(task.getContextUuid());
		taskDto.setCreatorUuid(task.getCreator().getUuid());
		taskDto.setCreationDate(task.getCreationDate().toEpochMilli());
		taskDto.setVisible(task.isVisible());
		taskDto.setStatus(serializeTaskStatus(task.getStatus()));
//		taskDto.setProgress(taskEntity.getProgress());
		taskDto.setTagAssignments(serializeTagAssignments(task.getTagAssignments()));

		return taskDto;
	}
	
//	public void toEntity(TaskDto taskDto, Task task) throws IllegalArgumentException{
//		if (taskDto == null)
//			throw new MapperConversionException("The task DTO is null");
//
//		if (task == null)
//			throw new MapperConversionException("The task Entity is null");
//
//		task.setName(taskDto.getName());
//		task.setStatement(taskDto.getStatement());
//		//TODO [microservice] create a local representation of Context
//		task.setContext(contextDao.findByUuid(taskDto.getContextUuid()));
//		//TODO [microservice] create a local representation of User
//		task.setCreator(userDao.findByUuid(taskDto.getCreatorUuid()));
//		task.setVisible(taskDto.getVisible());
//		task.setStatus(deserializeTaskStatus(taskDto.getStatus()));
////		taskEntity.setProgress(taskDto.getProgress());
//		task.setTagAssignments(deserializeTags(taskDto.getTags()));
//
//	}
	
	public void toEntity(TaskCreationDto taskDto, Task task) throws IllegalArgumentException{
		if (taskDto == null)
			throw new MapperConversionException("The task DTO is null");
		if (task == null)
			throw new MapperConversionException("The task Entity is null");
		
		task.setName(taskDto.getName());
		task.setStatement(taskDto.getStatement());
		if(taskDto.getVisible() != null)
			task.setVisible(taskDto.getVisible());
		if(taskDto.getDrafted() != null)
			task.setStatus(taskDto.getDrafted() ? TaskStatus.DRAFTED : TaskStatus.UNASSIGNED);
	}
	
	private Set<TaskTagAssignmentDto> serializeTagAssignments(Set<TagAssignment> taskTagAssignemnts) {
		Set<TaskTagAssignmentDto> serializedTagAssignments = null;
		
		if(taskTagAssignemnts != null) {
			serializedTagAssignments = new HashSet<>();
			for(TagAssignment taskTagAssignment : taskTagAssignemnts) {
				//TODO delegate to TaskTagAssignmentMapper.convertToDto()
				TaskTagAssignmentDto taskTagAssignmentDto = new TaskTagAssignmentDto();
				taskTagAssignmentDto.setTagUuid(taskTagAssignment.getTag().getUuid());
				taskTagAssignmentDto.setTagName(taskTagAssignment.getTag().getName());
				taskTagAssignmentDto.setAssignmentDate(taskTagAssignment.getAssignmentDate().toEpochMilli());
				taskTagAssignmentDto.setAssignerUuid(taskTagAssignment.getAssigner().getUuid());
				serializedTagAssignments.add(taskTagAssignmentDto);
			}
		}
		
		return serializedTagAssignments;
	}
	
//	private Set<Tag> deserializeTags(Set<TaskTagAssignmentDto> taskTagAssignmentDtos){
//		Set<Tag> deserializedTags = null;
//
//		if(taskTagAssignmentDtos != null) {
//			deserializedTags = new HashSet<>();
//
//			for(TaskTagAssignmentDto taskTagAssignmentDto : taskTagAssignmentDtos)
//				deserializedTags.add(taskTagAssignmentDao.findByUuid(taskTagAssignmentDto.getUuid()));
//		}
//
//		return deserializedTags;
//	}
	
//	private TaskStatus deserializeTaskStatus(String status) {
//		try {
//			return TaskStatus.valueOf(status);
//		}catch(IllegalArgumentException e) {
//			throw new IllegalArgumentException("No such task status: " + status);
//		}
//	}
//
	private String serializeTaskStatus(TaskStatus status) {
		return status.toString();
	}
}
