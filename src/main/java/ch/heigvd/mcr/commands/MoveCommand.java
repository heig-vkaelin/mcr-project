package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

public class MoveCommand extends ControllerCommand implements Command {
    public MoveCommand(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute() {
        return false;
    }
}
