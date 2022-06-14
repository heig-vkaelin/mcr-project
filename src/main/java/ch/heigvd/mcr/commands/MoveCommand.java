package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Position;
import ch.heigvd.mcr.ui.MainFrame;

/**
 * Classe représentant une commande de déplacement d'une entité
 *
 * @author Lazar Pavicevic
 * @author Jonathan Friedli
 */
public class MoveCommand implements Command {
    private final Entity entity;
    private final int oldX;
    private final int oldY;
    private final int newX;
    private final int newY;

    public MoveCommand(Entity entity, Position newPosition) {
        this.entity = entity;
        this.oldX = entity.getX();
        this.oldY = entity.getY();
        this.newX = newPosition.x();
        this.newY = newPosition.y();
    }

    @Override
    public void execute() {
        GameController.getInstance().setNewPosition(entity, newX, newY);
    }

    @Override
    public void rollback() {
        GameController.getInstance().setNewPosition(entity, oldX, oldY);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
