package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

public class UndoCommand implements Command {
    public UndoCommand() {
        super();
    }

    @Override
    public boolean execute() {
        return false;
    }
}
