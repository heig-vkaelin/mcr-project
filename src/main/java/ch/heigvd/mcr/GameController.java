package ch.heigvd.mcr;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.views.MenuView;

/**
 * Controller qui s'occupe des interactions entre l'utilisateur et le jeu ainsi
 * que de la gestion des vues.
 *
 * @author Valentin Kaelin
 */
public class GameController {

    private static GameController instance;

    private LevelState state;

    /**
     * Constructeur du contrôleur du jeu
     */
    private GameController() {
    }

    /**
     * @return l'instance du controller
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
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

        new MenuView().show();
    }

    public void setState(LevelState state) {
        this.state = state;
    }

    public void resetState() {
        state = AssetManager.levels.get("level" + state.getId());
    }
}
