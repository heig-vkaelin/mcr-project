package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;

/**
 * Commande permettant de changer un niveau
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class LoadLevelCommand implements Command {
    private final int levelId;

    /**
     * Crée une nouvelle commande de chargement de niveau
     *
     * @param id : id du niveau à charger
     */
    public LoadLevelCommand(int id) {
        this.levelId = id;
    }

    @Override
    public void execute() {
        LevelState level = AssetManager.levels.get("level" + levelId);
        level.loadState();
        GameController.getInstance().setState(level);
        MainFrame.getInstance().openLevelView();
    }

    @Override
    public void rollback() {
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
