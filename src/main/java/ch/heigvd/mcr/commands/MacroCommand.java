package ch.heigvd.mcr.commands;

import java.util.LinkedList;
import java.util.List;

public class MacroCommand implements Command {
    private final List<Command> subCommands;

    public MacroCommand() {
        subCommands = new LinkedList<>();
    }

    @Override
    public void execute() {
        for (Command c : subCommands) {
            c.execute();
        }
    }

    @Override
    public void rollback() {
        for (Command c : subCommands) {
            c.rollback();
        }
    }

    public void addCommand(Command command) {
        subCommands.add(command);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
