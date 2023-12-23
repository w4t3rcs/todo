package com.w4t3rcs.newtodo.config;

import com.w4t3rcs.newtodo.model.data.dao.UserRepository;
import com.w4t3rcs.newtodo.model.properties.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityProperties securityProperties) throws Exception {
        return httpSecurity.authorizeHttpRequests(matcherRegistry ->
                        matcherRegistry.requestMatchers(securityProperties.getPatternsForAll()).permitAll()
                                .requestMatchers(securityProperties.getPatternsForUsers()).hasRole("USER")
                ).formLogin(formLoginConfigurer -> formLoginConfigurer.loginPage("/login").defaultSuccessUrl("/"))
                .logout(logoutConfigurer -> logoutConfigurer.clearAuthentication(true).logoutUrl("/exit")
                        .invalidateHttpSession(true).logoutSuccessUrl("/"))
                .build();
    }
}
