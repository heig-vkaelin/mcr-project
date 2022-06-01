package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

/**
 * Classe abstraite représantent les commandes ayant un GameController commme receiver.
 *
 * @author Lazar Pavicevic
 */
public abstract class ControllerCommand implements Command {
    private final GameController controller;

    public ControllerCommand(GameController controller) {
        this.controller = controller;
    }

    protected GameController getController() {
        return controller;
    }

    public abstract boolean execute();
}
