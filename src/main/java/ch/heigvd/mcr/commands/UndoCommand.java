package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

public class UndoCommand implements Command {

    @Override
    public boolean execute() {
        GameController.getInstance().undo();
        return false;
    }

    @Override
    public boolean rollback() {
        return true;
    }
}
