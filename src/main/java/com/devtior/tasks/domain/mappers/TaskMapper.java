package com.devtior.tasks.domain.mappers;

import com.devtior.tasks.domain.dto.TaskDto;
import com.devtior.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
