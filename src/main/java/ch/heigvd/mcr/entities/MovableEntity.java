package ch.heigvd.mcr.entities;

/**
 * Classe abstraite représentant une entité déplaçable
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public abstract class MovableEntity extends Entity {
    public MovableEntity(Position position, Direction direction, EntityType type) {
        super(position, direction, type);
    }
}
