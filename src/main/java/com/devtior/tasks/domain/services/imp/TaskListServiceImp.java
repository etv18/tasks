package com.devtior.tasks.domain.services.imp;

import com.devtior.tasks.domain.entities.TaskList;
import com.devtior.tasks.domain.repositories.TaskListRepository;
import com.devtior.tasks.domain.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImp implements TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskListServiceImp(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(taskList.getId() != null)
            throw new IllegalArgumentException("Task list already has an ID!");

        if(taskList.getTitle() == null || taskList.getTitle().isBlank())
            throw new IllegalArgumentException("Task list title must be present !");

        LocalDateTime now = LocalDateTime.now();

        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }
}
