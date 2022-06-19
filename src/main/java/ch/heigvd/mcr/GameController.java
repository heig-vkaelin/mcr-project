package ch.heigvd.mcr;

import ch.heigvd.mcr.commands.Command;
import ch.heigvd.mcr.commands.MoveCommand;
import ch.heigvd.mcr.commands.TurnCommand;
import ch.heigvd.mcr.entities.Direction;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Pedestrian;
import ch.heigvd.mcr.entities.Position;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.ValidateState;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Controller qui s'occupe des interactions entre l'utilisateur et le jeu ainsi
 * que de la gestion des vues.
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class GameController {
    private static GameController instance;

    private LevelState state;
    private final Stack<Command> undoStack;
    private final LinkedList<CommandListener> commandListeners;

    /**
     * Constructeur du contrôleur du jeu
     */
    private GameController() {
        commandListeners = new LinkedList<>();
        undoStack = new Stack<>();
    }

    /**
     * @return l'instance du controller
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     * Démarre le jeu
     */
    public void run() {
        MainFrame.getInstance();
    }

    /**
     * @return l'état actuel du jeu
     */
    public LevelState getState() {
        return state;
    }

    /**
     * Change l'état actuel du jeu (ex: changement de niveau)
     *
     * @param state : nouvel état du jeu
     */
    public void setState(LevelState state) {
        this.state = state;
    }

    /**
     * Applique une nouvelle position à une entité
     * <p>
     * La nouvelle position est vérifiée, elle ne peut pas sortir de la grille ou
     * entrer en collision avec une autre entité.
     *
     * @param entity : l'entité à déplacer
     * @param newX   : sa nouvelle coordonnée X
     * @param newY   : sa nouvelle coordonnée Y
     */
    public void setSafePosition(Entity entity, int newX, int newY) {
        if (!state.getEntities().contains(entity))
            return;

        // Bien sur la grille
        newY = Math.max(0, Math.min(newY, state.getSideSize() - entity.getHeight()));
        newX = Math.max(0, Math.min(newX, state.getSideSize() - entity.getWidth()));

        // Collisions
        for (Entity e : state.getEntities())
            if (entity != e && entity.isColliding(e, newX, newY))
                return;

        entity.setPosition(newX, newY);
    }

    /**
     * Vérifie la position d'une entité en train de se déplacer (drag en cours)
     *
     * @param entity      : entité à vérifier
     * @param newPosition : nouvelle position souhaitée par l'entité
     * @return un état contenant sa nouvelle position, l'entité potentiellement en
     * collision et un booléen indiquant si l'entité a atteint la sortie
     */
    public ValidateState validatePosition(Entity entity, Position newPosition) {
        int newX = newPosition.x();
        int newY = newPosition.y();
        Position current = entity.getPosition();

        // Force le mouvement dans la direction de l'entité
        if (entity.getDirection() == Direction.UP || entity.getDirection() == Direction.DOWN) {
            newX = current.x();
            newY = Math.max(current.y() - 1, Math.min(newY, current.y() + 1)); // only move one cell
            newY = Math.max(0, Math.min(newY, state.getSideSize() - entity.getHeight()));
        } else {
            newY = current.y();
            newX = Math.max(current.x() - 1, Math.min(newX, current.x() + 1)); // only move one cell
            newX = Math.max(0, Math.min(newX, state.getSideSize() - entity.getWidth()));
        }

        // Collisions
        for (Entity e : state.getEntities())
            if (entity != e && entity.isColliding(e, newX, newY))
                return new ValidateState(current, e, false);

        Position newPos = new Position(newX, newY);

        // Vérification de victoire
        boolean win = playerWon(entity, newX, newY);

        return new ValidateState(newPos, null, win);
    }

    /**
     * Exécute une commande et l'ajoute à la pile des commandes potentiellement
     * annulables.
     * Notifie également les listeners de la commande.
     *
     * @param command : commande à ajouter
     */
    public void addCommand(Command command) {
        if (command.isUndoable())
            undoStack.push(command);

        command.execute();
        commandListeners.forEach(l -> l.commandExecuted(command));
    }

    /**
     * Exécute un tour du jeu
     *
     * @param command : commande à exécuter lors du tour
     */
    public void playTurn(Command command) {
        TurnCommand turn = new TurnCommand();
        turn.addCommand(command);

        for (Entity pedestrian : state.getPedestrians()) {
            MoveCommand move = new MoveCommand(
                    pedestrian,
                    Position.add(pedestrian.getPosition(), Position.randomDirection())
            );
            turn.addCommand(move);
        }

        addCommand(turn);
    }

    /**
     * Annule la dernière commande annulable exécutée
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            undoStack.pop().rollback();
        }
    }

    /**
     * Permet à un listener de s'abonner à l'exécution du commande
     *
     * @param listener : listener à abonner
     * @return l'événement à appeler pour que le listener puisse se désabonner
     */
    public Event onCommand(CommandListener listener) {
        commandListeners.add(listener);
        return () -> commandListeners.remove(listener);
    }

    /**
     * Termine le niveau et réouvre le menu
     */
    public void endGame() {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "You won in " + state.getNbMoves() + " moves !");
        MainFrame.getInstance().openMenuView();
    }

    /**
     * Vérifie si le joueur a gagné
     *
     * @param entity : entité à vérifier
     * @param newX   : nouvelle coordonnée X
     * @param newY   : nouvelle coordonnée Y
     * @return true si le joueur a gagné, false sinon
     */
    private boolean playerWon(Entity entity, int newX, int newY) {
        return entity.isThePlayer()
                && (state.getExitSide() == Direction.UP && newY == 0
                || state.getExitSide() == Direction.DOWN && newY == state.getSideSize() - entity.getType().getLength()
                || state.getExitSide() == Direction.LEFT && newX == 0
                || state.getExitSide() == Direction.RIGHT && newX == state.getSideSize() - entity.getType().getLength());
    }
}
