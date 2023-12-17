package com.w4t3rcs.newtodo.model.data.dao;

import com.w4t3rcs.newtodo.model.entity.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}