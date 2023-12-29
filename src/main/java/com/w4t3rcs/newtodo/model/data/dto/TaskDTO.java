package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.Task;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {
    private Long id;
    @Size(message = "Invalid description", max = 512)
    @NotEmpty(message = "Invalid description")
    private String description;
    private boolean finished;

    public static TaskDTO fromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setFinished(task.isFinished());
        return taskDTO;
    }

    public Task toTask(TodoRepository todoRepository, TaskRepository taskRepository) {
        Task task = new Task();
        if (this.getId() != null) task.setId(this.getId());
        task.setDescription(this.getDescription());
        task.setFinished(this.isFinished());
        if (taskRepository.existsById(this.getId())) {
            task.setTodo(taskRepository.findById(id).orElseThrow().getTodo());
        } else {
            task.setTodo(todoRepository.findTopByOrderByIdDesc().orElseThrow());
        }

        return task;
    }
}