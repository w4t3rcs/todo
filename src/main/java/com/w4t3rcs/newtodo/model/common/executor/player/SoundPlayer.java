package com.w4t3rcs.newtodo.model.common.executor.player;

import java.time.Duration;

public interface SoundPlayer extends Player {
    void play();

    void play(Duration duration);
}
