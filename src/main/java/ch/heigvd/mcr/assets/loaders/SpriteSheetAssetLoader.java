package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.assets.SpriteSheet;

public class SpriteSheetAssetLoader implements AssetLoader<SpriteSheet> {

    private final String path;

    private SpriteSheet spriteSheet;
    private boolean loaded = false;

    public SpriteSheetAssetLoader(String path) {
        this.path = path;
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
