package com.w4t3rcs.newtodo.controller.simple;

import com.w4t3rcs.newtodo.model.data.dao.TaskRepository;
import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import com.w4t3rcs.newtodo.model.entity.Task;
import com.w4t3rcs.newtodo.model.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/todo/{id}")
@Controller
public class DeleteTodoController {
    private final TodoRepository todoRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public DeleteTodoController(TodoRepository todoRepository, TaskRepository taskRepository) {
        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
    }

    @DeleteMapping
    public String deleteCurrentTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        Iterable<Task> allByTodo = taskRepository.findAllByTodo(todo);
        todoRepository.delete(todo);
        taskRepository.deleteAll(allByTodo);

        return "redirect:/todo";
    }
}
