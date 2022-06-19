package ch.heigvd.mcr.commands;

/**
 * Interface pour les différentes commandes du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public interface Command {
    /**
     * Exécute la commande
     */
    void execute();

    /**
     * Annule la commande
     */
    void rollback();

    /**
     * @return true si la commande est annulable, false sinon
     */
    boolean isUndoable();
}
