package com.github.lorenzosmc.common.service;

import com.github.lorenzosmc.common.dto.project.inbound.ProjectCreationDto;
import com.github.lorenzosmc.common.dto.project.inbound.TaskCreationDto;
import com.github.lorenzosmc.common.dto.project.inbound.TopicCreationDto;
import com.github.lorenzosmc.common.dto.project.outbound.ProjectDto;
import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;
import com.github.lorenzosmc.common.dto.project.outbound.TopicDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProjectService {
	public List<ProjectDto> findAllProjects(Map<String, Object> queryParams);
	public ProjectDto findProjectByUuid(UUID projectUuid);
	public ProjectDto createProject(ProjectCreationDto projectDto, UUID creatorUuid);
	public ProjectDto updateProject(ProjectDto projectDto);
	//TODO add task to project
	
	public List<TaskDto> findAllTasks(Map<String, Object> queryParams);
	public TaskDto findTaskByUuid(UUID projectUuid);
	public TaskDto createTask(TaskCreationDto taskDto, UUID contextUuid, UUID creatorUuid);
	public TaskDto updateTask(TaskDto taskDto);
	//TODO add tag to task
	
	public List<TopicDto> findAllTopics(Map<String, Object> queryParams);
	public TopicDto findTopicByUuid(UUID topicUuid);
	public TopicDto createTopic(TopicCreationDto topicDto, UUID taskUuid, UUID creatorUuid);
	public TopicDto updateTopic(TopicDto topicDto);
	
//	TODO Resources, Discussion and Messages
}
