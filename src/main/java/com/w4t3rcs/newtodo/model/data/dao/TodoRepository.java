package com.w4t3rcs.newtodo.model.data.dao;

import com.w4t3rcs.newtodo.model.entity.Todo;
import com.w4t3rcs.newtodo.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    Optional<Todo> findTopByOrderByIdDesc();

    Iterable<Todo> findAllByUser(User user);

    Optional<Todo> findByUserAndId(User user, Long id);
}