package com.w4t3rcs.newtodo.controller;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.data.dto.TaskDTO;
import com.w4t3rcs.newtodo.model.data.dto.TodoDTO;
import com.w4t3rcs.newtodo.model.entity.Task;
import com.w4t3rcs.newtodo.model.entity.Todo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/todo-create")
@Controller
public class CreateTodoController {
    private final List<TaskDTO> tasks = new ArrayList<>();
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public CreateTodoController(UserRepository userRepository, TodoRepository todoRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
    }

    @ModelAttribute("todo")
    public TodoDTO todo() {
        return new TodoDTO();
    }

    @ModelAttribute(name = "tasks")
    public List<TaskDTO> tasks() {
        return tasks;
    }

    @ModelAttribute("task")
    public TaskDTO task() {
        return new TaskDTO();
    }

    @GetMapping
    public String getTodoCreationPage() {
        return "authenticated/todo/create";
    }

    @PostMapping
    public String postTodoCreation(@ModelAttribute @Valid TodoDTO todoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "authenticated/todo/create";
        Todo todo = todoDTO.toTodo(userRepository);
        todoRepository.save(todo);
        tasks.forEach(taskDTO -> {
            Task task = taskDTO.toTask(todoRepository);
            taskRepository.save(task);
        });

        tasks.clear();
        return "redirect:/todo";
    }

    @GetMapping("/task")
    public String getTaskCreationPage() {
        return "authenticated/todo/create-task";
    }

    @PostMapping("/task")
    public String postTaskCreation(@ModelAttribute @Valid TaskDTO task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "authenticated/todo/create-task";
        tasks.add(task);
        return "redirect:/todo-create";
    }
}
