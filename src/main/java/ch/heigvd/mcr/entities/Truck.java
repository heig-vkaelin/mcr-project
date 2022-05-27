package ch.heigvd.mcr.entities;

/**
 * Classe représentant un camion
 *
 * @author Lazar Pavicevic
 */
public class Truck extends MovableEntity {

    public static final int WIDTH = 1;
    public static final int LENGTH = 3;

    public Truck(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }
}
