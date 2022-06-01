package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

import java.util.LinkedList;
import java.util.List;

public class MacroCommand extends ControllerCommand implements Command {

    private List<Command> subCommands;

    public MacroCommand(GameController controller) {
        super(controller);
        subCommands = new LinkedList<>();
    }

    @Override
    public boolean execute() {
        for (Command c : subCommands) {
            c.execute();
        }
        return true;
    }
}
