package ch.heigvd.mcr;

import ch.heigvd.mcr.commands.Command;
import ch.heigvd.mcr.entities.Direction;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Position;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.ValidateState;

import java.util.EventListener;
import java.util.LinkedList;
import java.util.Stack;


/**
 * Controller qui s'occupe des interactions entre l'utilisateur et le jeu ainsi
 * que de la gestion des vues.
 *
 * @author Valentin Kaelin
 */
public class GameController {
    private LinkedList<CommandListener> commandListeners = new LinkedList<>();

    private static GameController instance;
    private final Stack<Command> undoStack;
    private LevelState state;

    /**
     * Constructeur du contrôleur du jeu
     */
    private GameController() {
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
     *
     * @param delta : ms entre les deux rafraichissements de la vue
     */
    public void run(int delta) {
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

        if ((entity.getDirection() == Direction.UP || entity.getDirection() == Direction.DOWN) && newX != entity.getX()) {
            return false;
        } else if ((entity.getDirection() == Direction.RIGHT || entity.getDirection() == Direction.LEFT) && newY != entity.getY()) {
            return false;
        }

        for (Entity e : state.getEntities()) {
            if (entity != e && entity.isColliding(e))
                return false;
        }

        if (newX < 0 || newY < 0 || newX >= state.getSideSize() || newY >= state.getSideSize())
            return false;

        entity.setPosition(newX, newY);
        return true;
    }


    //TODO on va surement avoir besoin de modifier cette méthode
    public void setPosition(Entity entity, int newX, int newY) {
        if (entity.getDirection() == Direction.UP || entity.getDirection() == Direction.DOWN) {
            newX = entity.getX();
        }
    }

    public ValidateState validatePosition(Entity entity, Position newPosition) {
        int newX = newPosition.x();
        int newY = newPosition.y();
        Position current = entity.getPosition();

        //force axial movement
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
        commandListeners.forEach(l -> l.commandExecuted(command));
        undoStack.push(command);
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
