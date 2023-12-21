package com.w4t3rcs.newtodo.controller;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.Task;
import com.w4t3rcs.newtodo.model.entity.Todo;
import com.w4t3rcs.newtodo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/todo")
@Controller
public class GetTodoController {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public GetTodoController(UserRepository userRepository, TodoRepository todoRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getTodoHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByName(currentUsername).orElseThrow();
        model.addAttribute("todos", todoRepository.findAllByUser(currentUser));
        return "authenticated/todo/home";
    }

    @GetMapping("/{id}")
    public String getCurrentTodo(@PathVariable Long id, Model model) {
        Todo todo = todoRepository.findById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("currentTodo", todo);
        Iterable<Task> tasks = taskRepository.findAllByTodo(todo);
        model.addAttribute("currentTasks", tasks);
        return "authenticated/todo/current";
    }
}
