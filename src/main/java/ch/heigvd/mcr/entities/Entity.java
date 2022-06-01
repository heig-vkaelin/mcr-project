package ch.heigvd.mcr.entities;

/**
 * Classe représentant une entité affichable sur la grille
 *
 * @author Lazar Pavicevic
 */
public abstract class Entity {
    private final Direction direction;
    private final EntityType type;
    protected int coordX, coordY;

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
}
