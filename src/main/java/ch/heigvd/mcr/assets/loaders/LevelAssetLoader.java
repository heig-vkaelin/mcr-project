package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.levels.LevelParser;
import ch.heigvd.mcr.levels.LevelState;

/**
 * A class that loads levels from the file system from resources.
 *
 * @author Maxime Scharwath
 */
public class LevelAssetLoader implements AssetLoader<LevelState> {
    private final String path;
    boolean loaded = false;
    private LevelState level;

    public LevelAssetLoader(String path) {
        this.path = path;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        if (loaded) return;
        level = LevelParser.parseLevelFile(path);
        loaded = true;
    }

    @Override
    public LevelState get() {
        return level;
    }
}
