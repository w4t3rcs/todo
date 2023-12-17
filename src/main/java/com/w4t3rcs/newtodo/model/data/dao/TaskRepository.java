package com.w4t3rcs.newtodo.model.data.dao;

import com.w4t3rcs.newtodo.model.entity.Task;
import com.w4t3rcs.newtodo.model.entity.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findAllByTodo(Todo todo);
}