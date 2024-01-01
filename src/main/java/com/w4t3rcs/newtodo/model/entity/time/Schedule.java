package com.w4t3rcs.newtodo.model.entity.time;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Schedule {
    @NotNull(message = "Invalid time period")
    @Column(name = "time_period_value")
    private TimePeriod timePeriod;
    private int count;
    @NotNull(message = "Invalid time")
    private LocalTime time;
    @NotNull(message = "Invalid date")
    private LocalDate end;
}
