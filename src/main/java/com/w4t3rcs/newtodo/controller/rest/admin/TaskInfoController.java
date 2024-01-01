package com.w4t3rcs.newtodo.controller.rest.admin;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.todo.Task;
import com.w4t3rcs.newtodo.model.entity.todo.Todo;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/api/task", produces = "application/json")
@RestController
public class TaskInfoController {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskInfoController(UserRepository userRepository, TodoRepository todoRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @GetMapping(params = "finished")
    public List<Task> getAllFinishedTasks() {
        return taskRepository.findAllByFinished(true);
    }

    @GetMapping(params = "unfinished")
    public List<Task> getAllUnfinishedTasks() {
        return taskRepository.findAllByFinished(false);
    }

    @GetMapping(params = "description")
    public List<String> getAllTaskDescriptions() {
        return taskRepository.findAllDescriptions();
    }

    @GetMapping(params = "id")
    public List<Long> getAllTaskIds() {
        return taskRepository.findAllIds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.of(taskRepository.findById(id));
    }

    @GetMapping(path = "/{id}", params = "todo")
    public List<Task> getAllTasksByTodoId(@PathVariable Long id) {
        Todo todoById = todoRepository.findById(id).orElseThrow();
        return (List<Task>) taskRepository.findAllByTodo(todoById);
    }

    @GetMapping(path = "/{id}", params = "user")
    public List<Task> getAllTasksByUsername(@PathVariable String id) {
        User userById = userRepository.findByName(id).orElseThrow();
        List<Todo> allByUser = (List<Todo>) todoRepository.findAllByUser(userById);
        List<Task> allTasks = new ArrayList<>();
        allByUser.stream()
                .map(taskRepository::findAllByTodo)
                .forEach(tasks -> tasks.forEach(allTasks::add));
        return allTasks;
    }

    @GetMapping(path = "/{id}", params = {"todo", "description"})
    public List<String> getTaskDescriptions(@PathVariable Long id) {
        return taskRepository.findDescriptionsByTodoId(id);
    }
}
