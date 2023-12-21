package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.Task;
import com.w4t3rcs.newtodo.model.entity.Todo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {
    @Size(message = "Invalid description", max = 512)
    @NotEmpty(message = "Invalid description")
    private String description;

    public Task toTask(TodoRepository todoRepository) {
        Task task = new Task();
        task.setDescription(this.getDescription());

        Todo currentTodoFromDb = todoRepository.findAllOrderedDesc().get(0);
        task.setTodo(currentTodoFromDb);
        return task;
    }
}