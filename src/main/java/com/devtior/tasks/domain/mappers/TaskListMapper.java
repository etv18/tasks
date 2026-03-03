package com.devtior.tasks.domain.mappers;

import com.devtior.tasks.domain.dto.TaskListDto;
import com.devtior.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto tasklistDto);

    TaskListDto toDto(TaskList taskList);
}
