package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.entity.todo.Todo;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.Getter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoDTO {
    private Long id;
    @Size(message = "Invalid name", max = 256)
    @NotEmpty(message = "Invalid name")
    private String name;
    private User user;

    public static TodoDTO fromTodo(Todo todo) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(todo.getId());
        todoDTO.setName(todo.getName());
        todoDTO.setUser(todo.getUser());
        return todoDTO;
    }

    public Todo toTodo(Getter<User> currentUserGetter) {
        Todo todo = new Todo();
        if (this.getId() != null) todo.setId(this.getId());
        todo.setName(this.getName());
        todo.setUser(this.getUser() == null ? currentUserGetter.get() : this.getUser());
        return todo;
    }
}