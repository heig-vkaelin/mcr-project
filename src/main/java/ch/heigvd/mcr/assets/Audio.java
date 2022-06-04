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
        System.out.println("Playing audio");
        clip.setFramePosition(0);
        clip.start();
    }
}
