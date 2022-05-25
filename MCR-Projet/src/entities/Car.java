package entities;

/**
 * Classe représentant une voiture
 *
 * @author Lazar Pavicevic
 */
public class Car extends MovableEntity {

    public static final int WIDTH = 1;
    public static final int LENGTH = 2;

    public Car(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }
}
