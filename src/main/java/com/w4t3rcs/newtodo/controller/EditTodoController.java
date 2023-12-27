package com.w4t3rcs.newtodo.controller;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.Todo;
import com.w4t3rcs.newtodo.model.entity.User;
import com.w4t3rcs.newtodo.model.service.getter.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/todo-editor")
@Controller
public class EditTodoController {
    private final TodoRepository todoRepository;
    private final TaskRepository taskRepository;
    private final Getter<User> currentUserGetter;

    @Autowired
    public EditTodoController(TodoRepository todoRepository, TaskRepository taskRepository, Getter<User> currentUserGetter) {
        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
        this.currentUserGetter = currentUserGetter;
    }

    @GetMapping
    public String getEditorHomePage(Model model) {
        User currentUser = currentUserGetter.get();
        Iterable<Todo> todos = todoRepository.findAllByUser(currentUser);
        model.addAttribute("todos", todos);
        return "authenticated/todo/editor-home";
    }
}
