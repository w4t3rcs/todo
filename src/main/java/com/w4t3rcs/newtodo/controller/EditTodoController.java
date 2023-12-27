package com.w4t3rcs.newtodo.controller;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.Task;
import com.w4t3rcs.newtodo.model.entity.Todo;
import com.w4t3rcs.newtodo.model.entity.User;
import com.w4t3rcs.newtodo.model.service.getter.Getter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String getCurrentEditorPage(@PathVariable Long id, Model model) {
        User currentUser = currentUserGetter.get();
        Todo todo = todoRepository.findByUserAndId(currentUser, id).orElseThrow();
        model.addAttribute("currentTodo", todo);
        Iterable<Task> tasks = taskRepository.findAllByTodo(todo);
        model.addAttribute("currentTasks", tasks);
        return "authenticated/todo/editor-current";
    }

    @PutMapping("/{id}")
    public String postCurrentEditor(@PathVariable Long id, @ModelAttribute @Valid Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "authenticated/todo/editor-current";
        todo.setUser(currentUserGetter.get());
        todoRepository.save(todo);
        return "redirect:/todo/" + id;
    }

    @PutMapping("/task/{id}")
    public String postCurrentTasksEditor(@PathVariable Long id, @ModelAttribute @Valid Todo todo, @ModelAttribute @Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "authenticated/todo/editor-current";
//        task.setTodo(todo);
        taskRepository.save(task);
        return "redirect:/todo-editor/" + todo.getId();
    }
}
