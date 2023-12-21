package com.w4t3rcs.newtodo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.Persistable;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;
    @NotEmpty(message = "Invalid description")
    @Size(max = 512, message = "Invalid description")
    private String description;

    @Override
    public boolean isNew() {
        return id == null;
    }
}