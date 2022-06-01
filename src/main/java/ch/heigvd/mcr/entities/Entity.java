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
    private Direction direction;
    private EntityType type;

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
            case UP -> {
                return new Rectangle(coordX, coordY, -type.getWidth() + 1, -type.getLength() + 1);
            }

            case DOWN -> {
                return new Rectangle(coordX, coordY, type.getWidth() - 1, type.getLength() - 1);
            }

            case LEFT -> {
                return new Rectangle(coordX, coordY, -type.getLength() + 1, type.getWidth() - 1);
            }

            case RIGHT -> {
                return new Rectangle(coordX, coordY, type.getLength() - 1, type.getWidth() - 1);
            }
        }
        return null;
    }
}
