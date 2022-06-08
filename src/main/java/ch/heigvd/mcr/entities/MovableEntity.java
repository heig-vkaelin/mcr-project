package ch.heigvd.mcr.entities;

/**
 * Classe abstraite représentant une entité déplaçable
 *
 * @author Lazar Pavicevic
 */
public abstract class MovableEntity extends Entity {
    public MovableEntity(int originX, int originY, Direction direction, EntityType type) {
        super(originX, originY, direction, type);
    }

    public void updateCoords(int newX, int newY) {
        coordX = newX;
        coordY = newY;
    }
}
