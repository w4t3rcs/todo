package com.w4t3rcs.newtodo.model.service.getter;

import com.w4t3rcs.newtodo.model.common.container.Getter;
import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationGetterService implements Getter<User> {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationGetterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByName(currentUsername).orElseThrow(() -> new UsernameNotFoundException("No account with this username!"));
        log.info("currentUser: \"{}\" - is authenticated!", currentUser);
        return currentUser;
    }
}
