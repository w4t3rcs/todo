package com.w4t3rcs.newtodo.model.entity.time;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Deadline {
    @NotNull(message = "Invalid date")
    @Column(name = "end")
    private LocalDate deadline;

    public boolean isEnded() {
        return isEnded(LocalDate.now());
    }

    public boolean isEnded(LocalDate localDate) {
        return localDate.isAfter(this.getDeadline());
    }
}
