package com.github.lorenzosmc;

import com.github.lorenzosmc.common.dto.project.inbound.TaskCreationDto;
import com.github.lorenzosmc.common.dto.project.outbound.TaskDto;
import com.github.lorenzosmc.dao.TaskDao;
import com.github.lorenzosmc.dao.UserDao;
import com.github.lorenzosmc.model.Task;
import com.github.lorenzosmc.model.User;
import jakarta.inject.Inject;

import java.util.UUID;

public class TaskController {
    @Inject
    private UserDao userDao;

    @Inject
    private TaskDao taskDao;

    @Inject
    private ModelMapper modelMapper;

    public TaskDto createTask(TaskCreationDto taskCreationDto, UUID contextUuid, UUID creatorUuid){
        //FIXME what if it doesn't exist?
        User creator = userDao.findByUuid(creatorUuid);

        Task task = ModelFactory.newTask(contextUuid, taskCreationDto.getName(), taskCreationDto.getStatement(), creator);

        modelMapper.toEntity(taskCreationDto, task);

        taskDao.save(task);

        return modelMapper.toDto(task);
    }
}
