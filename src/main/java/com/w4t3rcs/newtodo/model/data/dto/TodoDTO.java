package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.Todo;
import com.w4t3rcs.newtodo.model.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class TodoDTO {
    @Size(message = "Invalid name", max = 256)
    @NotEmpty(message = "Invalid name")
    private String name;

    public Todo toTodo(UserRepository userRepository) {
        Todo todo = new Todo();
        todo.setName(this.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByName(currentUsername).orElseThrow();
        todo.setUser(currentUser);

        return todo;
    }
}