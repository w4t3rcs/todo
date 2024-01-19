package com.w4t3rcs.newtodo.model.common.executor.player;

import java.time.Duration;

public interface ServicePlayer<T> extends Player {
    void play(T t);

    void play(T t, Duration duration);
}
