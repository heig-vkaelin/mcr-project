package entities;

/**
 * Classe représentant une entité affichable sur la grille
 *
 * @author Lazar Pavicevic
 */
public abstract class Entity {
    protected int coordX, coordY;
    private Direction direction;
    private Color color;

    public Entity(int originX, int originY, Direction direction, Color color) {
        coordX = originX;
        coordY = originY;
        this.direction = direction;
        this.color = color;
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

    public Color getColor() {
        return color;
    }
}
