package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.entities.types.EntityType;

/**
 * Classe abstraite représentant une entité déplaçable
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public abstract class MovableEntity extends Entity {
    /**
     * Crée une entité se déplaçant
     *
     * @param position  : position initiale de l'entité
     * @param direction : direction de déplacement de l'entité
     * @param type      : type de l'entité
     */
    public MovableEntity(Position position, Direction direction, EntityType type) {
        super(position, direction, type);
    }
}
