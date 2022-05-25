package entities;

/**
 * Classe représentant une voiture
 *
 * @author Lazar Pavicevic
 */
public class Car extends Entity implements Movable{
    public Car(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }

    @Override
    public void updatePosition() {
        // TODO
    }
}
