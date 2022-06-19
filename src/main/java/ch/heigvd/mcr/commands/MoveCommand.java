package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Position;

/**
 * Classe représentant une commande de déplacement d'une entité
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class MoveCommand implements Command {
    private final Entity entity;
    private final int oldX;
    private final int oldY;
    private final int newX;
    private final int newY;

    /**
     * Crée une nouvelle commande de déplacement d'une entité
     *
     * @param entity      : entité à déplacer
     * @param newPosition : nouvelle position de l'entité
     */
    public MoveCommand(Entity entity, Position newPosition) {
        this.entity = entity;
        this.oldX = entity.getX();
        this.oldY = entity.getY();
        this.newX = newPosition.x();
        this.newY = newPosition.y();
    }

    @Override
    public void execute() {
        GameController.getInstance().setSafePosition(entity, newX, newY);
    }

    @Override
    public void rollback() {
        entity.setPosition(oldX, oldY);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
