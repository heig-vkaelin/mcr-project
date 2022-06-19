package ch.heigvd.mcr.assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.net.URL;

/**
 * Classe permettant de jouer des sons
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class Audio {
    private final Clip clip;
    private boolean isPlaying;

    /**
     * Crée un nouveau son
     *
     * @param file : url du fichier à jouer
     * @throws IOException si le fichier n'est pas trouvé
     */
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
     * Joue le fichier du son
     */
    public void play() {
        clip.setFramePosition(0);
        clip.start();
        isPlaying = true;
    }

    /**
     * Joue le fichier du son à un volume donné
     *
     * @param volume : volume souhaité
     */
    public void play(double volume) {
        setVolume(volume);
        play();
    }

    /**
     * Arrête de jouer le fichier du son
     */
    public void stop() {
        if (isPlaying) {
            clip.stop();
            isPlaying = false;
        }
    }

    /**
     * Modifie le volume du son
     *
     * @param volume : volume souhaité dans l'intervalle [0, 1]
     * @throws IllegalArgumentException si le volume est invalide
     */
    public void setVolume(double volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    /**
     * Joue un son en boucle
     *
     * @param volume : volume souhaité
     */
    public void loop(double volume) {
        setVolume(volume);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        isPlaying = true;
    }
}
