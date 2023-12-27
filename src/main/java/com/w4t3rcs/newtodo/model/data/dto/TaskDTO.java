package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.Task;
import com.w4t3rcs.newtodo.model.entity.Todo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {
    private Long id;
    private Todo todo;
    @Size(message = "Invalid description", max = 512)
    @NotEmpty(message = "Invalid description")
    private String description;
    private boolean finished;

    public static TaskDTO fromTask(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setFinished(task.isFinished());
        taskDTO.setTodo(task.getTodo());
        return taskDTO;
    }

    public Task toTask(TodoRepository todoRepository) {
        Task task = new Task();
        if (this.getId() != null) task.setId(this.getId());
        task.setDescription(this.getDescription());
        task.setFinished(this.isFinished());
        task.setTodo(this.getTodo() == null ? todoRepository.findAllOrderedDesc().get(0) : this.getTodo());
        return task;
    }
}