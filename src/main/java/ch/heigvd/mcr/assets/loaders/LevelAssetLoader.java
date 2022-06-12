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

    public LevelAssetLoader(String path) {
        this.path = ClassLoader.getSystemResource(path);
    }

    @Override
    public boolean isLoaded() {
        return true;
    }

    @Override
    public void load() {
    }

    @Override
    public LevelState get() {
        return LevelParser.parseLevelFile(path);
    }
}
