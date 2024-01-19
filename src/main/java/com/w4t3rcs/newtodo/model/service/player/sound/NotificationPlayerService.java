package com.w4t3rcs.newtodo.model.service.player.sound;

import com.w4t3rcs.newtodo.model.common.executor.player.SoundPlayer;
import com.w4t3rcs.newtodo.model.common.executor.player.ServicePlayer;
import com.w4t3rcs.newtodo.model.properties.SoundProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class NotificationPlayerService implements SoundPlayer {
    @Qualifier("soundFromPathPlayerService")
    private final ServicePlayer<String> fromPathPlayer;
    private final SoundProperties soundProperties;

    @Autowired
    public NotificationPlayerService(ServicePlayer<String> fromPathPlayer, SoundProperties soundProperties) {
        this.fromPathPlayer = fromPathPlayer;
        this.soundProperties = soundProperties;
    }

    @Override
    @Async
    public void play() {
        SoundProperties.Notification notificationProperties = soundProperties.getNotification();
        fromPathPlayer.play(notificationProperties.getPath());
    }

    @Override
    @Async
    public void play(Duration duration) {
        SoundProperties.Notification notificationProperties = soundProperties.getNotification();
        fromPathPlayer.play(notificationProperties.getPath(), duration);
    }
}
