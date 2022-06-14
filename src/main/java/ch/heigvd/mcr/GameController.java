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

import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * Controller qui s'occupe des interactions entre l'utilisateur et le jeu ainsi
 * que de la gestion des vues.
 *
 * @author Valentin Kaelin
 */
public class GameController {
    private final LinkedList<CommandListener> commandListeners;

    private static GameController instance;
    private final Stack<Command> undoStack;
    private LevelState state;

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

    public LevelState getState() {
        return this.state;
    }

    public void setState(LevelState state) {
        this.state = state;
    }

    public boolean setNewPosition(Entity entity, int newX, int newY) {
        if (!state.getEntities().contains(entity))
            return false;

        // Clang
        newY = Math.max(0, Math.min(newY, state.getSideSize() - entity.getType().getLength()));
        newX = Math.max(0, Math.min(newX, state.getSideSize() - entity.getType().getLength()));

        for (Entity e : state.getEntities()) {
            if (entity != e && entity.isColliding(e, newX, newY)) {
                return false;
            }
        }

        entity.setPosition(newX, newY);
        return true;
    }

    public ValidateState validatePosition(Entity entity, Position newPosition) {
        int newX = newPosition.x();
        int newY = newPosition.y();
        Position current = entity.getPosition();

        // force axial movement
        if (entity.getDirection() == Direction.UP || entity.getDirection() == Direction.DOWN) {
            newX = current.x();
            newY = Math.max(current.y() - 1, Math.min(newY, current.y() + 1)); // only move one cell
            newY = Math.max(0, Math.min(newY, state.getSideSize() - entity.getType().getLength()));
        } else {
            newY = current.y();
            newX = Math.max(current.x() - 1, Math.min(newX, current.x() + 1)); // only move one cell
            newX = Math.max(0, Math.min(newX, state.getSideSize() - entity.getType().getLength()));
        }

        for (Entity e : state.getEntities()) {
            if (entity != e && entity.isColliding(e, newX, newY)) {
                return new ValidateState(current, e);
            }
        }
        return new ValidateState(new Position(newX, newY), null);
    }

    public void addCommand(Command command) {
        if (command.isUndoable())
            undoStack.push(command);

        command.execute();
        commandListeners.forEach(l -> l.commandExecuted(command));
    }

    public void playTurn(Command command) {
        TurnCommand turn = new TurnCommand();
        turn.addCommand(command);
        // TODO: make move the peyDey
        //        turn.addCommand(pedestrianCommand);

        List<Pedestrian> pedestrians = state.getPedestrians();
        for (Pedestrian pedestrian : pedestrians) {
            turn.addCommand(
                    new MoveCommand(
                            pedestrian,
                            Position.add(pedestrian.getPosition(), Position.randomDirection())
                    )
            );
        }

        addCommand(turn);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            undoStack.pop().rollback();
        }
    }

    public Event onCommand(CommandListener listener) {
        commandListeners.add(listener);
        return () -> commandListeners.remove(listener);
    }
}
