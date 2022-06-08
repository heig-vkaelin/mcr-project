package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.entities.Entity;

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

    public MoveCommand(Entity entity, int newX, int newY) {
        this.entity = entity;
        this.oldX = entity.getX();
        this.oldY = entity.getY();
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public boolean execute() {
        GameController.getInstance().addCommand(this);
        return GameController.getInstance().setNewPosition(entity, newX, newY);
    }

    @Override
    public boolean rollback() {
        return GameController.getInstance().setNewPosition(entity, oldX, oldY);
    }
}
