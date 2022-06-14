package ch.heigvd.mcr.commands;

/**
 * Interface pour les différentes commandes du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public interface Command {
    void execute();

    void rollback();

    boolean isUndoable();
}
