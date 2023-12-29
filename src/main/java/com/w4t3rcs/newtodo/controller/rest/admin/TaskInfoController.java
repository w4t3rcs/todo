package com.w4t3rcs.newtodo.controller.rest.admin;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/api/task", produces = "application/json")
@RestController
public class TaskInfoController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskInfoController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.of(taskRepository.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json")
    public Task postTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
}
