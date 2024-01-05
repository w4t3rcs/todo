package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.todo.Task;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class TaskDTO {
    private Long id;
    @Size(message = "Invalid description", max = 512)
    @NotEmpty(message = "Invalid description")
    private String description;
    private boolean finished;

    public static TaskDTO fromTask(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .finished(task.isFinished())
                .build();
    }

    public Task toTask(TodoRepository todoRepository, TaskRepository taskRepository) {
        return Task.builder()
                .id(this.getId())
                .description(this.getDescription())
                .finished(this.isFinished())
                .todo(taskRepository.existsById(this.getId()) ? taskRepository.findById(id).orElseThrow().getTodo() : todoRepository.findTopByOrderByIdDesc().orElseThrow())
                .build();
    }
}