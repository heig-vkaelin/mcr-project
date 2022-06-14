package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.levels.LevelParser;
import ch.heigvd.mcr.levels.LevelState;

import java.net.URL;

/**
 * Classe permettant de charger des niveaux
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class LevelAssetLoader implements AssetLoader<LevelState> {
    private final URL path;
    boolean loaded = false;
    private LevelState level;

    public LevelAssetLoader(String path) {
        this.path = ClassLoader.getSystemResource(path);
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