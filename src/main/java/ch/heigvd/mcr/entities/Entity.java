package ch.heigvd.mcr.entities;

import java.awt.*;

/**
 * Classe représentant une entité affichable sur la grille
 *
 * @author Lazar Pavicevic
 * @author Nicolas Crausaz
 */
public abstract class Entity {
    protected int coordX, coordY;
    private final Direction direction;
    private final EntityType type;

    public Entity(int originX, int originY, Direction direction, EntityType type) {
        coordX = originX;
        coordY = originY;
        this.direction = direction;
        this.type = type;
    }

    public int getX() {
        return coordX;
    }

    public int getY() {
        return coordY;
    }

    public Direction getDirection() {
        return direction;
    }

    public EntityType getType() {
        return type;
    }

    public Rectangle getBounds() {
        switch (direction) {
            case UP, DOWN -> {
                return new Rectangle(coordX, coordY, type.getWidth(), type.getLength());
            }

            case LEFT, RIGHT -> {
                return new Rectangle(coordX, coordY, type.getLength(), type.getWidth());
            }
        }
        return null;
    }
}
