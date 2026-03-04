package com.devtior.tasks.domain.mappers.imp;

import com.devtior.tasks.domain.dto.TaskListDto;
import com.devtior.tasks.domain.entities.Task;
import com.devtior.tasks.domain.entities.TaskList;
import com.devtior.tasks.domain.entities.TaskStatus;
import com.devtior.tasks.domain.mappers.TaskListMapper;
import com.devtior.tasks.domain.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper){
        this.taskMapper = taskMapper;
    }

    /* Turns an object from an instance TaskListDto class to be an instance of TaskList class  */
    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(
                        tasks -> tasks.stream().map(taskMapper::toDto).toList()
                ).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if(tasks == null) return null;

        long closedTaskCount = tasks.stream().filter(
                task -> TaskStatus.CLOSED == task.getStatus()
        ).count();

        return (double ) closedTaskCount / tasks.size();
    }
}
