package ch.heigvd.mcr.commands;

import java.util.LinkedList;
import java.util.List;

/**
 * Macro regroupant plusieurs sous commandes
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class MacroCommand implements Command {
    private final List<Command> subCommands;

    /**
     * Crée une nouvelle macro (aggregation de commandes)
     */
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

    /**
     * Ajoute une commande à la macro
     *
     * @param command : commande à ajouter
     */
    public void addCommand(Command command) {
        subCommands.add(command);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
