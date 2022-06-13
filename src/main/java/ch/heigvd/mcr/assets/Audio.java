package ch.heigvd.mcr.assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

/**
 * An Audio wrapper that can be used to play audio files.
 *
 * @author Maxime Scharwath
 */
public class Audio {
    private final Clip clip;

    private boolean isPlaying;

    public Audio(URL file) throws IOException {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * Plays the audio file.
     */
    public void play() {
        clip.setFramePosition(0);
        clip.start();
        isPlaying = true;
    }

    /**
     * Stops the audio file
     */
    public void stop() {
        if (isPlaying) {
            clip.stop();
            isPlaying = false;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
