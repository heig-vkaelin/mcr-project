package ch.heigvd.mcr;

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
        GameController controller = new GameController();
        controller.run(DELTA_MS);
    }
}
