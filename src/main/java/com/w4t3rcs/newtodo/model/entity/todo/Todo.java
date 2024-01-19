package com.w4t3rcs.newtodo.model.entity.todo;

import com.w4t3rcs.newtodo.model.entity.authentication.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.hateoas.RepresentationModel;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class Todo extends RepresentationModel<Todo> implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Invalid name")
    @Size(max = 256, message = "Invalid name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean isNew() {
        return id == null;
    }
}