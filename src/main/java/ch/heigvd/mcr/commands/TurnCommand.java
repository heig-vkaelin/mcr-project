package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

/**
 * Macro représentant toutes les commandes réalisées lors d'un tour du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class TurnCommand extends MacroCommand {
    @Override
    public void execute() {
        super.execute();
        GameController.getInstance().getState().addMove();
    }

    @Override
    public void rollback() {
        super.rollback();
        GameController.getInstance().getState().cancelMove();
    }
}
