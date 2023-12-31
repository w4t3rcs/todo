package com.w4t3rcs.newtodo.model.data.dao;

import com.w4t3rcs.newtodo.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAllByRole(String role);

    Optional<User> findByName(String username);

    @Query("SELECT name FROM User")
    List<String> findAllUsernames();

    @Query("SELECT email FROM User")
    List<String> findAllEmails();
}
