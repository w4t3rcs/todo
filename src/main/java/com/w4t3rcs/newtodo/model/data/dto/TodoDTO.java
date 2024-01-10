package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.entity.todo.Todo;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.container.Getter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public class TodoDTO {
    private Long id;
    @Size(message = "Invalid name", max = 256)
    @NotEmpty(message = "Invalid name")
    private String name;
    private User user;

    public static TodoDTO fromTodo(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .name(todo.getName())
                .user(todo.getUser())
                .build();
    }

    public Todo toTodo(Getter<User> currentUserGetter) {
        return Todo.builder()
                .id(this.getId())
                .name(this.getName())
                .user(this.getUser() == null ? currentUserGetter.get() : this.getUser())
                .build();
    }
}