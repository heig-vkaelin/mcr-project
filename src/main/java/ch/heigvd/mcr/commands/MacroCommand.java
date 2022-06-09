package ch.heigvd.mcr.commands;

import java.util.LinkedList;
import java.util.List;

public class MacroCommand implements Command {

    private final List<Command> subCommands;

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

    @Override
    public boolean rollback() {
        for (Command c : subCommands) {
            c.rollback();
        }
        return true;
    }

    public void addCommand(Command command) {
        subCommands.add(command);
    }
}
