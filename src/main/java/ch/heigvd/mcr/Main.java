package ch.heigvd.mcr;

import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.levels.LevelParser;
import ch.heigvd.mcr.levels.LevelState;

import ch.heigvd.mcr.ui.ImageManager;
import ch.heigvd.mcr.ui.GameController;

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
        LevelState state = LevelParser.parseLevelFile("1.txt");

        System.out.println(state.getDifficulty());
        System.out.println(state.getSideSize());
        for (Entity e: state.getEntities()) {
            System.out.println(e);
        }
        GameController controller = new GameController();
        ImageManager.loadImages((progress, finished) -> {
            System.out.println("Progress: " + progress*100.0 + "%");
            if (finished) {
              controller.run(DELTA_MS);
            }
        });
    }
}
