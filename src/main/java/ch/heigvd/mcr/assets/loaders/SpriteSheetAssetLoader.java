package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.assets.SpriteSheet;

import java.net.URL;

/**
 * Classe permettant de charger des SpriteSheets
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class SpriteSheetAssetLoader implements AssetLoader<SpriteSheet> {
    private final URL url;
    private SpriteSheet spriteSheet;
    private boolean loaded;

    /**
     * Crée un nouveau chargeur de SpriteSheet
     *
     * @param path : chemin de la SpriteSheet à charger
     */
    public SpriteSheetAssetLoader(String path) {
        url = ClassLoader.getSystemResource(path);
        loaded = false;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        try {
            spriteSheet = new SpriteSheet(url);
            loaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SpriteSheet get() {
        return spriteSheet;
    }
}
