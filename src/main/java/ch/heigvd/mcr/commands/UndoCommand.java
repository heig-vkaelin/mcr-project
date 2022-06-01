package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

public class UndoCommand extends ControllerCommand implements Command {
    public UndoCommand(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
