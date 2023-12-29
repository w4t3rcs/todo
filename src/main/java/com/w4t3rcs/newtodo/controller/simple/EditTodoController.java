package com.w4t3rcs.newtodo.controller.simple;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.data.dto.TaskDTO;
import com.w4t3rcs.newtodo.model.data.dto.TodoDTO;
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
        model.addAttribute("currentTodo", TodoDTO.fromTask(todo));
        Iterable<Task> tasks = taskRepository.findAllByTodo(todo);
        model.addAttribute("currentTasks", tasks);
        return "authenticated/todo/editor-current";
    }

    @PutMapping("/{id}")
    public String putCurrentEditor(@PathVariable Long id, @ModelAttribute("currentTodo") @Valid TodoDTO todoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "authenticated/todo/editor-current";
        Todo todo = todoDTO.toTodo(currentUserGetter);
        todoRepository.save(todo);
        return "redirect:/todo/" + id;
    }

    @GetMapping("/task/{id}")
    public String getCurrentTaskEditorPage(@PathVariable Long id, Model model) {
        Task currentTask = taskRepository.findById(id).orElseThrow();
        model.addAttribute("currentTask", TaskDTO.fromTask(currentTask));
        return "authenticated/todo/editor-current-task";
    }

    @PutMapping("/task/{id}")
    public String putCurrentTaskEditor(@ModelAttribute("currentTask") @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "authenticated/todo/editor-current";
        Task task = taskDTO.toTask(todoRepository);
        taskRepository.save(task);
        return "redirect:/todo-editor/" + task.getTodo().getId();
    }
}
