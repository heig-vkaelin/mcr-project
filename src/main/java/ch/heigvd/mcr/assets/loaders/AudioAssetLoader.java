package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.assets.Audio;

import java.io.IOException;
import java.net.URL;

/**
 * Classe permettant de charger des sons
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class AudioAssetLoader implements AssetLoader<Audio> {
    private final URL url;
    private Audio audio;

    private boolean loaded = false;

    public AudioAssetLoader(String path) {
        url = ClassLoader.getSystemResource(path);
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        if (loaded) return;
        try {
            audio = new Audio(url);
            loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Audio get() {
        return audio;
    }
}
