package com.w4t3rcs.newtodo.model.data.dao;

import com.w4t3rcs.newtodo.model.entity.todo.Task;
import com.w4t3rcs.newtodo.model.entity.todo.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByFinished(boolean finished);

    Iterable<Task> findAllByTodo(Todo todo);

    @Query("SELECT description FROM Task")
    List<String> findAllDescriptions();

    @Query("SELECT id FROM Task")
    List<Long> findAllIds();

    @Query("SELECT description FROM Task WHERE todo.id = :todoId")
    List<String> findDescriptionsByTodoId(@Param("todoId") Long todoId);
}