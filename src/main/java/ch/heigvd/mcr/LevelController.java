package ch.heigvd.mcr;

import ch.heigvd.mcr.levels.LevelParser;
import ch.heigvd.mcr.levels.LevelState;

import java.util.LinkedList;
import java.util.List;

/**
 * Controller qui s'occupe de la gestion des niveaux du jeu
 *
 * @author Valentin Kaelin
 */
public class LevelController {
    private static LevelController instance;
    private final List<LevelState> levels;

    /**
     * Charge tous les niveaux du jeu
     */
    private LevelController() {
        levels = new LinkedList<>(LevelParser.loadAllLevels());
    }

    /**
     * @return l'instance du controller
     */
    public static LevelController getInstance() {
        if (instance == null)
            instance = new LevelController();
        return instance;
    }

    /**
     * Ajoute un niveau au jeu
     *
     * @param level : le niveau à ajouter
     */
    public void addLevel(LevelState level) {
        levels.add(level);
    }

    /**
     * @return la liste des niveaux du jeu
     */
    public List<LevelState> getLevels() {
        return levels;
    }
}
