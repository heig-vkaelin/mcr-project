package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

/**
 * Commande permettant d'annuler la dernière commande effectuée avant celle-ci
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class UndoCommand implements Command {
    @Override
    public void execute() {
        GameController.getInstance().undo();
    }

    @Override
    public void rollback() {
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
