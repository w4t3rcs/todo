package com.w4t3rcs.newtodo.controller.rest.admin;

import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/api/todo", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class TodoInfoController {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoInfoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        List<Todo> todos = (List<Todo>) todoRepository.findAll();
        todos.forEach(todo -> todo.add(linkTo(TodoInfoController.class).slash(todo.getId()).withSelfRel()));
        return todos;
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
