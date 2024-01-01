package com.w4t3rcs.newtodo.model.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Method method;
    private LocalTime time;
    private LocalDate end;

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Method {
        private TimePeriod timePeriod;
        private int count;
    }
}
