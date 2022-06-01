package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

public class RestartCommand extends ControllerCommand implements Command {
    public RestartCommand(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute() {
        // TODO appel getController().restart(); ?
        return true;
    }
}
