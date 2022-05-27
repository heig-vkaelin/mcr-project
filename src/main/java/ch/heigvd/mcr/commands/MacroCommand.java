import ch.heigvd.mcr.commands.Command;

import java.util.LinkedList;

public class MacroCommand implements Command {

    private LinkedList<Command> subCommands;

    @Override
    public boolean execute() {
        for (Command c : subCommands) {
            c.execute();
        }
        return true;
    }
}
