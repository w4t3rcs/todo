package com.w4t3rcs.newtodo.model.entity.holder;

import com.w4t3rcs.newtodo.model.entity.message.Notification;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_holder")
public class NotificationHolder implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sentDate;

    @Override
    public boolean isNew() {
        return id == null;
    }
}