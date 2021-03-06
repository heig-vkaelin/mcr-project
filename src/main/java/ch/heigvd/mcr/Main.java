package ch.heigvd.mcr;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;
import ch.heigvd.mcr.assets.loaders.AudioAssetLoader;
import ch.heigvd.mcr.assets.loaders.ImageAssetLoader;
import ch.heigvd.mcr.assets.loaders.SpriteSheetAssetLoader;
import ch.heigvd.mcr.assets.registers.LevelsRegister;

import javax.swing.*;
import java.awt.*;

/**
 * Classe principale du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class Main {
    /**
     * Point d'entrée du programme
     *
     * @param args : arguments passés en ligne de commande, inutilisés ici
     */
    public static void main(String[] args) {
        registerAssets();

        JProgressBar loader = new JProgressBar();
        final JFrame frame = createLoadingScreen(loader);

        AssetManager.loadAll((progress, done) -> {
            loader.setValue((int) (progress * 100));
            if (done) {
                frame.dispose();
                AudioManager.getInstance().play(AssetManager.audios.get("menu"));
                GameController controller = GameController.getInstance();
                controller.run();
            }
        });
    }

    /**
     * Enregistre les assets à charger
     */
    private static void registerAssets() {
        AssetManager.images.register("logo", new ImageAssetLoader("images/logo.png"));
        AssetManager.images.register("menu_background", new ImageAssetLoader("images/menu_background.png"));

        AssetManager.audios.register("death", new AudioAssetLoader("audio/death.wav"));
        AssetManager.audios.register("horn", new AudioAssetLoader("audio/horn.wav"));
        AssetManager.audios.register("sw", new AudioAssetLoader("audio/sw.wav"));
        AssetManager.audios.register("bonk", new AudioAssetLoader("audio/bonk.wav"));
        AssetManager.audios.register("menu", new AudioAssetLoader("audio/menu.wav"));

        AssetManager.sprites.register("icons", new SpriteSheetAssetLoader("sprites/icons.sheet"));
        AssetManager.sprites.register("board", new SpriteSheetAssetLoader("sprites/board.sheet"));
        AssetManager.sprites.register("cars", new SpriteSheetAssetLoader("sprites/cars.sheet"));
        AssetManager.sprites.register("obstacles", new SpriteSheetAssetLoader("sprites/obstacles.sheet"));
        AssetManager.sprites.register("pedestrians", new SpriteSheetAssetLoader("sprites/pedestrians.sheet"));

        AssetManager.levels.register(new LevelsRegister());
    }

    /**
     * Crée une fenêtre de chargement
     *
     * @param loader : barre de progression
     * @return la frame de chargement
     */
    private static JFrame createLoadingScreen(JProgressBar loader) {
        final JFrame frame = new JFrame("MCR");
        frame.add(loader);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width >> 2, 10);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
}
