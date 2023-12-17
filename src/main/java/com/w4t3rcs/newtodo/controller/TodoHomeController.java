package com.w4t3rcs.newtodo.controller;

import com.w4t3rcs.newtodo.model.entity.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/todo")
@Controller
public class TodoHomeController {
    @ModelAttribute
    public List<Todo> todoList() {
        return null;
    }

    @GetMapping
    public String getTodoHome() {
        return "authenticated/todo/home";
    }
}
