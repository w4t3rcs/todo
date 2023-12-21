package com.w4t3rcs.newtodo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Persistable<String>, UserDetails {
    @Id
    private String id;
    @NotEmpty(message = "Invalid name")
    @Size(max = 63, message = "Invalid name")
    private String name;
    @NotEmpty(message = "Invalid password")
    @Size(max = 127, message = "Invalid password")
    private String password;
    @Email(message = "Invalid email")
    @Size(max = 127, message = "Invalid email")
    private String email;
    @Past(message = "Invalid date")
    private LocalDate birthdate;

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}