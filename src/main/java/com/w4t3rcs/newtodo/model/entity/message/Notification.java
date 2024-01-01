package com.w4t3rcs.newtodo.model.entity.message;

import com.w4t3rcs.newtodo.model.entity.time.Schedule;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.Persistable;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification implements Persistable<Long>, TextMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User to;
    @NotNull(message = "Invalid method")
    @Column(name = "method_value")
    private Method method;
    @NotNull(message = "Invalid schedule")
    @Embedded
    private Schedule schedule;

    @Override
    public String getMessage(MessageProperties messageProperties) {
        return format(messageProperties.getNotificationText());
    }

    @Override
    public String format(String text) {
        return String.format(text, to.getName());
    }

    @Override
    public boolean isNew() {
        return this.getId() == null;
    }

    public enum Method {
        EMAIL
    }
}
