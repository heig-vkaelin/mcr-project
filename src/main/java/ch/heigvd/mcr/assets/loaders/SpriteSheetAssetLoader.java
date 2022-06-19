package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.assets.SpriteSheet;

import java.io.IOException;
import java.net.URL;

/**
 * Classe permettant de charger des SpriteSheets
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class SpriteSheetAssetLoader extends AssetLoader<SpriteSheet> {
    /**
     * Crée un nouveau chargeur de SpriteSheet
     *
     * @param path : chemin de la SpriteSheet à charger
     */
    public SpriteSheetAssetLoader(String path) {
        super(path);
    }

    @Override
    protected SpriteSheet loadAsset() throws IOException {
        return new SpriteSheet(getUrl());
    }
}
