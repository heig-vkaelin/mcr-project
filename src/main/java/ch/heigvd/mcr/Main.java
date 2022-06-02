package ch.heigvd.mcr;

import ch.heigvd.mcr.assets.*;
import ch.heigvd.mcr.assets.loaders.AudioAssetLoader;
import ch.heigvd.mcr.assets.loaders.ImageAssetLoader;
import ch.heigvd.mcr.assets.loaders.LevelAssetLoader;
import ch.heigvd.mcr.assets.registers.LevelsRegister;

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
        Asset.LEVEL_ASSET.register(new LevelsRegister());

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
