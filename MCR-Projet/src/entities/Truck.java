package entities;

/**
 * Classe représentant un camion
 *
 * @author Lazar Pavicevic
 */
public class Truck extends Entity implements Movable{
    public Truck(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }

    @Override
    public void updatePosition() {
        // TODO
    }
}
