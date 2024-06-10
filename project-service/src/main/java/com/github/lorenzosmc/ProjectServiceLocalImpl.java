package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.project.inbound.ProjectCreationDto;
import com.github.lorenzosmc.common.dto.project.inbound.TaskCreationDto;
import com.github.lorenzosmc.common.dto.project.inbound.TopicCreationDto;
import com.github.lorenzosmc.common.dto.project.outbound.ProjectDto;
import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;
import com.github.lorenzosmc.common.dto.project.outbound.TopicDto;
import com.github.lorenzosmc.common.event.TaskCreatedEvent;
import com.github.lorenzosmc.common.event.UserCreatedEvent;
import com.github.lorenzosmc.common.service.ProjectService;
import com.github.lorenzosmc.dao.TaskDao;
import com.github.lorenzosmc.dao.UserDao;
import com.github.lorenzosmc.model.Task;
import com.github.lorenzosmc.model.User;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProjectServiceLocalImpl implements ProjectService {
	@Inject
	private TaskController taskController;

	@Inject
	private TaskDao modelDao;

	@Inject
	private ModelMapper modelMapper;

	@Inject
	private UserDao userDao;
	
	@Inject
	private Event<TaskCreatedEvent> taskCreatedEvent;
	
	
	@Override
	public List<ProjectDto> findAllProjects(Map<String, Object> queryParams) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ProjectDto findProjectByUuid(UUID projectUuid) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ProjectDto createProject(ProjectCreationDto projectDto, UUID creatorUuid) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ProjectDto updateProject(ProjectDto projectDto) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<TaskDto> findAllTasks(Map<String, Object> queryParams) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TaskDto findTaskByUuid(UUID projectUuid) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@Transactional
	public TaskDto createTask(@Valid TaskCreationDto taskCreationDto, UUID contextUuid, UUID creatorUuid) {
		return taskController.createTask(taskCreationDto, contextUuid, creatorUuid);
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<TopicDto> findAllTopics(Map<String, Object> queryParams) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TopicDto findTopicByUuid(UUID topicUuid) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TopicDto createTopic(TopicCreationDto topicDto, UUID taskUuid, UUID creatorUuid) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TopicDto updateTopic(TopicDto topicDto) {
		//TODO input validation
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Transactional
//	public void onUserCreation(@Observes UserCreatedEvent event) {
//		User user = ModelFactory.newUser(event.getUserDto().getUuid(), event.getUserDto().getName());
//		userDao.save(user);
//	}

}
