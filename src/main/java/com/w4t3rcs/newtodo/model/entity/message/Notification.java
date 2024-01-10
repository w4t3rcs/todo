package com.w4t3rcs.newtodo.model.entity.message;

import com.w4t3rcs.newtodo.model.common.Formatter;
import com.w4t3rcs.newtodo.model.entity.time.Deadline;
import com.w4t3rcs.newtodo.model.entity.authentication.User;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.domain.Persistable;

@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification implements Persistable<Long>, TextMessage, Formatter<String, String> {
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
    private Deadline deadline;
    @Column(name = "is_enabled")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean enabled;

    @Override
    public boolean isNew() {
        return this.getId() == null;
    }

    @Override
    public String getMessageSubject(MessageProperties messageProperties) {
        return format(messageProperties.getNotificationSubject());
    }

    @Override
    public String getMessageBody(MessageProperties messageProperties) {
        return format(messageProperties.getNotificationText());
    }

    @Override
    public String getSenderAddress() {
        return null;
    }

    @Override
    public String getRecipientAddress() {
        return switch (getMethod()) {
            case EMAIL -> this.getTo().getEmail();
            case MESSAGE -> this.getTo().getName();
        };
    }

    @Override
    public String format(String text) {
        return String.format(text, to.getName());
    }

    public enum Method {
        EMAIL,
        MESSAGE
    }
}
