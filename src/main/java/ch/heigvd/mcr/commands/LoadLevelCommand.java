package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;

public class LoadLevelCommand implements Command {
    private final int levelId;

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
