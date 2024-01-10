package com.w4t3rcs.newtodo.model.service.player;

import com.w4t3rcs.newtodo.model.common.executor.player.SoundPropertiesPlayer;
import com.w4t3rcs.newtodo.model.common.executor.player.StringPlayer;
import com.w4t3rcs.newtodo.model.properties.SoundProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class NotificationPlayerService implements SoundPropertiesPlayer {
    private final StringPlayer stringPlayer;

    @Autowired
    public NotificationPlayerService(StringPlayer stringPlayer) {
        this.stringPlayer = stringPlayer;
    }

    @Override
    @Async
    public void play(SoundProperties soundProperties) {
        stringPlayer.play(soundProperties.getNotificationPath());
    }

    @Override
    @Async
    public void play(SoundProperties soundProperties, Duration duration) {
        stringPlayer.play(soundProperties.getNotificationPath(), duration);
    }
}
