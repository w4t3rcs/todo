package com.w4t3rcs.newtodo.model.data.dto;

import com.w4t3rcs.newtodo.model.entity.User;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDTO {
    @NotBlank(message = "Invalid name")
    @Size(max = 63, message = "Invalid name")
    private String name;
    @NotBlank(message = "Invalid password")
    @Size(max = 127, message = "Invalid password")
    private String password;
    @Email(message = "Invalid email")
    @Size(max = 127, message = "Invalid email")
    private String email;
    @Past(message = "Invalid date")
    private LocalDate birthdate;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(UUID.randomUUID().toString(), this.getName(), passwordEncoder.encode(password), this.getEmail(), this.getBirthdate(), "user");
    }
}
