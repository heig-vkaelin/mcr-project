package ch.heigvd.mcr.entities;

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

    public int getHeadX() {
        switch (direction) {
            case UP, DOWN -> {
                return coordX + type.getWidth() - 1;
            }
/*
            case DOWN -> {
                return coordX + type.getWidth() + 1;
            }
 */

            case LEFT -> {
                return coordX - type.getLength() + type.getWidth() + 1;
            }

            case RIGHT -> {
                return coordX + type.getLength() + type.getWidth() - 1;
            }
            default -> throw new RuntimeException("Invalid direction");
        }
    }

    public int getHeadY() {
        switch (direction) {
            case UP -> {
                return coordY - type.getLength() + 1;
            }

            case LEFT -> {
                return coordY - type.getWidth() + 1;
            }

            case RIGHT -> {
                return coordY + type.getWidth() - 1;
            }

            case DOWN -> {
                return coordY + type.getLength() - 1;
            }

            default -> throw new RuntimeException("Invalid direction");
        }
    }
}
