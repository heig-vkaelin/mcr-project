package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.assets.SpriteSheet;

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
    private final String path;
    private SpriteSheet spriteSheet;
    private boolean loaded;

    public SpriteSheetAssetLoader(String path) {
        this.path = path;
        loaded = false;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        try {
            spriteSheet = new SpriteSheet(ClassLoader.getSystemResource(path));
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
