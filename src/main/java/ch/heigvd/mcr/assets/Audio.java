package ch.heigvd.mcr.assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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

    public void play(double volume) {
        setVolume(volume);
        play();
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
    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(double volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void loop(double volume) {
        setVolume(volume);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        isPlaying = true;
    }
}
