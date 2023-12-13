package com.w4t3rcs.newtodo.model.data.dao;

import com.w4t3rcs.newtodo.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByName(String username);
}
