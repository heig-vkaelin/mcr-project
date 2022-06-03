package ch.heigvd.mcr;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.loaders.AudioAssetLoader;
import ch.heigvd.mcr.assets.loaders.ImageAssetLoader;
import ch.heigvd.mcr.assets.loaders.SpriteSheetAssetLoader;
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
        AssetManager.images.register("logo", new ImageAssetLoader("images/logo.png"));
        AssetManager.audios.register("death", new AudioAssetLoader("audio/death.wav"));
        AssetManager.sprites.register("board", new SpriteSheetAssetLoader("sprites/board.sheet"));
        AssetManager.levels.register(new LevelsRegister());

        AssetManager.loadAll((progress, done) -> {
            System.out.println("Loading assets: " + (int) (progress * 100) + "%");
            if (done) {
                AssetManager.audios.get("death").play();
                GameController controller = new GameController();
                controller.run(DELTA_MS);
            }
        });
    }
}
