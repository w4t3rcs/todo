package com.w4t3rcs.newtodo.model.entity.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.domain.Persistable;
import org.springframework.hateoas.RepresentationModel;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task extends RepresentationModel<Task> implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;
    @NotEmpty(message = "Invalid description")
    @Size(max = 512, message = "Invalid description")
    private String description;
    @Column(name = "is_finished")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean finished;

    @Override
    public boolean isNew() {
        return id == null;
    }
}