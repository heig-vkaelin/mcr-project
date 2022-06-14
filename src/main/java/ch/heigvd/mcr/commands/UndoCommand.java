package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

public class UndoCommand implements Command {
    @Override
    public void execute() {
        GameController.getInstance().undo();
    }

    @Override
    public void rollback() {
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
