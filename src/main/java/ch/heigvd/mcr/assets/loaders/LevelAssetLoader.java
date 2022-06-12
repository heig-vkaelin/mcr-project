package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.levels.LevelParser;
import ch.heigvd.mcr.levels.LevelState;

import java.net.URL;

/**
 * A class that loads levels from the file system from resources.
 *
 * @author Maxime Scharwath
 */
public class LevelAssetLoader implements AssetLoader<LevelState> {
    private final URL path;

    private boolean loaded = false;

    public LevelAssetLoader(String path) {
        this.path = ClassLoader.getSystemResource(path);
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        try {
            LevelParser.parseLevelFile(path);
            loaded = true;
        } catch (Exception e) {
            System.out.println("catched");
            loaded = false;
        }
    }

    @Override
    public LevelState get() {
        return loaded ? LevelParser.parseLevelFile(path) : null;
    }
}
