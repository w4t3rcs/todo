package com.w4t3rcs.newtodo.model.common.executor.player;

import java.time.Duration;

public interface TypedPlayer<T> {
    void play(T t);

    void play(T t, Duration duration);
}
