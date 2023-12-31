package com.w4t3rcs.newtodo.controller.rest.admin;

import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/api/todo", produces = "application/json")
@RestController
public class TodoInfoController {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoInfoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return (List<Todo>) todoRepository.findAll();
    }

    @GetMapping(params = "name")
    public List<String> getAllNames() {
        return todoRepository.findAllNames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return ResponseEntity.of(todoRepository.findById(id));
    }
}
