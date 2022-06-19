package ch.heigvd.mcr.assets;

import java.util.HashSet;

/**
 * Singleton permettant de gérer les sons du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class AudioManager {
    private static AudioManager instance;
    private final HashSet<Audio> audios;
    private double volume;
    boolean isMuted;

    /**
     * Crée le manager des sons du jeu
     */
    private AudioManager() {
        audios = new HashSet<>();
        volume = 1.0;
        isMuted = false;
    }

    /**
     * @return le manager des sons du jeu
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Joue un nouveau son
     *
     * @param audio : son à jouer
     */
    public void play(Audio audio) {
        audios.add(audio);
        audio.play(isMuted ? 0 : volume);
    }

    /**
     * Joue en boucle un nouveau son
     *
     * @param audio : son à jouer en boucle
     */
    public void loop(Audio audio) {
        audios.add(audio);
        audio.loop(isMuted ? 0 : volume);
    }

    /**
     * Arrête de jouer un son
     *
     * @param audio : son à arrêter
     */
    public void stop(Audio audio) {
        audios.remove(audio);
        audio.stop();
    }

    /**
     * Modifie le volume des sons
     *
     * @param isMuted : true pour mute, false pour unmute
     */
    public void setMuted(boolean isMuted) {
        this.isMuted = isMuted;
        refreshVolume();
    }

    /**
     * @return true si les sons du jeu sont mute, false sinon
     */
    public boolean isMuted() {
        return isMuted;
    }

    /*
     * Modifie le volume de tous les sons du jeu
     */
    private void refreshVolume() {
        for (Audio audio : audios) {
            audio.setVolume(isMuted ? 0 : volume);
        }
    }
}
