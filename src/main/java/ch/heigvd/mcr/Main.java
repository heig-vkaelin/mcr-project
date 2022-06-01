package ch.heigvd.mcr;

import ch.heigvd.mcr.ui.ImageManager;

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
        
        GameController controller = new GameController();
        ImageManager.loadImages((progress, finished) -> {
            System.out.println("Progress: " + progress * 100.0 + "%");
            if (finished) {
                controller.run(DELTA_MS);
            }
        });
    }
}
