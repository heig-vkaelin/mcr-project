package ch.heigvd.mcr;

import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.views.HomeView;
import ch.heigvd.mcr.ui.views.MenuView;

/**
 * Controller qui s'occupe des interactions entre l'utilisateur et le jeu ainsi
 * que de la gestion des vues.
 *
 * @author Valentin Kaelin
 */
public class GameController {
    /**
     * Constructeur du contrôleur du jeu
     */
    public GameController() {
    }

    /**
     * Démarre le jeu
     *
     * @param delta : ms entre les deux rafraichissements de la vue
     */
    public void run(int delta) {
//        new Timer(delta, e -> {
//            MenuView.getInstance().repaint()
//        }).start();

        new MainFrame();
    }
}
