package ch.heigvd.mcr;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.loaders.AudioAssetLoader;
import ch.heigvd.mcr.assets.loaders.ImageAssetLoader;
import ch.heigvd.mcr.assets.loaders.SpriteSheetAssetLoader;
import ch.heigvd.mcr.assets.registers.LevelsRegister;

import javax.swing.*;
import java.awt.*;

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
        AssetManager.images.register("menu_background", new ImageAssetLoader("images/menu_background.png"));
        AssetManager.audios.register("death", new AudioAssetLoader("audio/death.wav"));
        AssetManager.audios.register("horn", new AudioAssetLoader("audio/horn.wav"));
        AssetManager.audios.register("music", new AudioAssetLoader("audio/music.wav"));
        AssetManager.audios.register("bonk", new AudioAssetLoader("audio/bonk.wav"));
        AssetManager.audios.register("menu", new AudioAssetLoader("audio/menu.wav"));
        AssetManager.sprites.register("board", new SpriteSheetAssetLoader("sprites/board.sheet"));
        AssetManager.sprites.register("cars", new SpriteSheetAssetLoader("sprites/cars.sheet"));
        AssetManager.sprites.register("obstacles", new SpriteSheetAssetLoader("sprites/obstacles.sheet"));
        AssetManager.sprites.register("pedestrians", new SpriteSheetAssetLoader("sprites/pedestrians.sheet"));

        AssetManager.levels.register(new LevelsRegister());

        //create jframe for loading screen
        final JFrame frame = new JFrame("MCR");
        var loader = new JProgressBar();
        frame.add(loader);
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width >> 2, 10);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        AssetManager.loadAll((progress, done) -> {
            loader.setValue((int) (progress * 100));
            System.out.println("Loading assets: " + (int) (progress * 100) + "%");
            if (done) {
                frame.dispose();
                AssetManager.audios.get("menu").play();
                GameController controller = GameController.getInstance();
                controller.run(DELTA_MS);
            }
        });
    }
}
