package ch.heigvd.mcr.entities;

/**
 * Classe abstraite représentant une entité déplaçable
 *
 * @author Lazar Pavicevic
 */
public abstract class MovableEntity extends Entity {
    public MovableEntity(Position position, Direction direction, EntityType type) {
        super(position, direction, type);
    }
}
