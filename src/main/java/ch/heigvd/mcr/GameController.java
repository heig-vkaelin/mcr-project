package ch.heigvd.mcr;

import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.views.HomeView;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.commands.Command;
import ch.heigvd.mcr.entities.Direction;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.views.MenuView;

import java.util.Stack;

/**
 * Controller qui s'occupe des interactions entre l'utilisateur et le jeu ainsi
 * que de la gestion des vues.
 *
 * @author Valentin Kaelin
 */
public class GameController {

    private static GameController instance;

    private LevelState state;

    private final Stack<Command> undoStack;

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
//        new Timer(delta, e -> {
//            MenuView.getInstance().repaint()
//        }).start();

        new MainFrame();
    }

    public void setState(LevelState state) {
        this.state = state;
    }

    public void resetState() {
        state = AssetManager.levels.get("level" + state.getId());
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

        entity.setX(newX);
        entity.setY(newY);
        return true;
    }
//TODO on va surement avoir besoin de modifier cette méthode
    public void setPosition(Entity entity, int newX, int newY) {
        int currentX = entity.getX();
        int currentY = entity.getY();
        //force axial movement
        if(entity.getDirection() == Direction.UP || entity.getDirection() == Direction.DOWN) {
            newX = currentX;
            newY = Math.max(0, Math.min(newY, state.getSideSize() - entity.getType().getLength()));
        } else {
            newY = currentY;
            newX = Math.max(0, Math.min(newX, state.getSideSize() - entity.getType().getLength()));
        }

        for (Entity e : state.getEntities()) {
            if (entity != e && entity.isColliding(e, newX, newY)){
                System.out.println("Entity[" + entity.getType() + "] Colliding with " + e.getType());
                return;
            }
        }
        System.out.println("Entity[" + entity.getType() + "] Setting position to " + newX + " " + newY);
        entity.setX(newX);
        entity.setY(newY);
    }

    public void addCommand(Command command) {
        undoStack.push(command);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            undoStack.pop().rollback();
        }
    }
}
