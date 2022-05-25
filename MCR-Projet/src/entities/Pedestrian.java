package entities;

/**
 * Classe représentant un piéton pouvant se déplacer de manière autonome sur le board
 *
 * @author Lazar Pavicevic
 */
public class Pedestrian extends MovableEntity {

    public static final int WIDTH = 1;
    public static final int LENGTH = 1;

    public Pedestrian(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }
}
