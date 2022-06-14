package ch.heigvd.mcr.assets;

import java.util.HashSet;

/**
 * Classe permettant de gérer les sons du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class AudioManager {
    private static AudioManager instance;
    private final HashSet<Audio> audios = new HashSet<>();

    private double volume = 1.0;

    boolean isMuted = false;

    public void play(Audio audio) {
        audios.add(audio);
        audio.play(isMuted ? 0 : volume);
    }

    public void loop(Audio audio) {
        audios.add(audio);
        audio.loop(isMuted ? 0 : volume);
    }

    public void stop(Audio audio) {
        audios.remove(audio);
        audio.stop();
    }

    void stop() {
        for (Audio audio : audios) {
            audio.stop();
        }
        audios.clear();
    }

    private void refreshVolume() {
        for (Audio audio : audios) {
            audio.setVolume(isMuted ? 0 : volume);
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
        refreshVolume();
    }

    public void setMuted(boolean isMuted) {
        this.isMuted = isMuted;
        refreshVolume();
    }

    public boolean isMuted() {
        return isMuted;
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

}
