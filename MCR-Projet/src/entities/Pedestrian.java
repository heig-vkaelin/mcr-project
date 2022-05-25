package entities;

/**
 * Classe représentant un piéton pouvant se déplacer de manière autonome sur le board
 *
 * @author Lazar Pavicevic
 */
public class Pedestrian extends Entity implements Movable{
    public Pedestrian(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }

    @Override
    public void updatePosition() {
     // TODO
    }
}
