package ch.heigvd.mcr.assets;

import java.io.IOException;
import java.net.URL;

/**
 * A class that loads audio from the file system from resources.
 *
 * @author Maxime Scharwath
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
