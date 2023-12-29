package com.w4t3rcs.newtodo;

import com.w4t3rcs.newtodo.model.data.dao.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoApplicationTests {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoApplicationTests(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Test
    void contextLoads() {
        System.out.println(todoRepository.findTopByOrderByIdDesc().get());
    }
}
