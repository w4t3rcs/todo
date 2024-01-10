package com.w4t3rcs.newtodo.controller.simple.todo;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.todo.Task;
import com.w4t3rcs.newtodo.model.entity.todo.Todo;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.common.container.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/todo")
@Controller
public class GetTodoController {
    private final TodoRepository todoRepository;
    private final TaskRepository taskRepository;
    private final Getter<User> currentUserGetter;

    @Autowired
    public GetTodoController(TodoRepository todoRepository, TaskRepository taskRepository, Getter<User> currentUserGetter) {
        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
        this.currentUserGetter = currentUserGetter;
    }

    @GetMapping
    public String getTodoHome(Model model) {
        User currentUser = currentUserGetter.get();
        model.addAttribute("todos", todoRepository.findAllByUser(currentUser));
        return "authenticated/todo/home";
    }

    @GetMapping("/{id}")
    public String getCurrentTodo(@PathVariable Long id, Model model) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        model.addAttribute("currentTodo", todo);
        Iterable<Task> tasks = taskRepository.findAllByTodo(todo);
        model.addAttribute("currentTasks", tasks);
        return "authenticated/todo/current";
    }
}
