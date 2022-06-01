package ch.heigvd.mcr;

import ch.heigvd.mcr.assets.Asset;
import ch.heigvd.mcr.assets.AudioAssetLoader;
import ch.heigvd.mcr.assets.ImageAssetLoader;
import ch.heigvd.mcr.assets.LevelAssetLoader;

/**
 * Classe principale du jeu
 */
public class Main {
    /**
     * Point d'entrée du programme
     *
     * @param args : arguments passés en ligne de commande
     */
    public static void main(String[] args) {
        final int DELTA_MS = 20;

        Asset.IMAGE_ASSET.register("logo", new ImageAssetLoader("images/logo.png"));
        Asset.AUDIO_ASSET.register("death", new AudioAssetLoader("audio/death.wav"));

        Asset.LEVEL_ASSET.register("level1", new LevelAssetLoader("1.txt"));
        Asset.LEVEL_ASSET.register("level2", new LevelAssetLoader("2.txt"));
        Asset.LEVEL_ASSET.register("level3", new LevelAssetLoader("3.txt"));
        Asset.LEVEL_ASSET.register("level4", new LevelAssetLoader("4.txt"));

        Asset.loadAll((progress, done) -> {
            System.out.println("Loading assets: " + (int) (progress * 100) + "%");
            if (done) {
                Asset.AUDIO_ASSET.get("death").play();
                GameController controller = new GameController();
                controller.run(DELTA_MS);
            }
        });
    }
}
