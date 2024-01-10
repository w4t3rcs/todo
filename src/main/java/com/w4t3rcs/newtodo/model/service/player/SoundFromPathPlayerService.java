package com.w4t3rcs.newtodo.model.service.player;

import com.w4t3rcs.newtodo.model.common.StringPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.IOException;
import java.time.Duration;

@Slf4j
@Service
public class SoundFromPathPlayerService implements StringPlayer {
    @Override
    public void play(String path) {
        final Resource resource = createClassPathResource(path);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource.getInputStream())) {
            playSound(audioInputStream);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            log.warn("{} hasn't been played", path);
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void play(String path, Duration duration) {
        final Resource resource = createClassPathResource(path);
        try (AudioInputStream stubStream = AudioSystem.getAudioInputStream(resource.getInputStream());
             AudioInputStream audioInputStream = new AudioInputStream(stubStream, stubStream.getFormat(), duration.getSeconds() * stubStream.getFrameLength())) {
            playSound(audioInputStream);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            log.warn("{} hasn't been played", path);
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Resource createClassPathResource(String name) {
        return new ClassPathResource("/static/sounds/" + name);
    }

    private void playSound(AudioInputStream audioInputStream) throws LineUnavailableException, IOException {
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}
