package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

import java.util.LinkedList;
import java.util.List;

public class MacroCommand implements Command {

    private List<Command> subCommands;

    public MacroCommand() {
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
