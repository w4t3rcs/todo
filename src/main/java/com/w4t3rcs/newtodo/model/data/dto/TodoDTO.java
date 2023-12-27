package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.entity.Todo;
import com.w4t3rcs.newtodo.model.entity.User;
import com.w4t3rcs.newtodo.model.service.getter.Getter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoDTO {
    @Size(message = "Invalid name", max = 256)
    @NotEmpty(message = "Invalid name")
    private String name;

    public Todo toTodo(Getter<User> currentUserGetter) {
        Todo todo = new Todo();
        todo.setName(this.getName());
        todo.setUser(currentUserGetter.get());
        return todo;
    }
}