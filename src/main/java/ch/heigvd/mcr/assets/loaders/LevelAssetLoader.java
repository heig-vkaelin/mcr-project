package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.levels.LevelParser;
import ch.heigvd.mcr.levels.LevelState;

import java.io.IOException;
import java.net.URL;

/**
 * Classe permettant de charger des niveaux
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class LevelAssetLoader extends AssetLoader<LevelState> {
    /**
     * Crée un nouveau charger de niveau
     *
     * @param path : chemin du niveau à charger
     */
    public LevelAssetLoader(String path) {
        super(path);
    }

    @Override
    protected LevelState loadAsset() {
        return LevelParser.parseLevelFile(getUrl());
    }
}
