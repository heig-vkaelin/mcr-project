package ch.heigvd.mcr.entities;

/**
 * Classe représentant un piéton pouvant se déplacer de manière autonome sur le board
 *
 * @author Lazar Pavicevic
 */
public class Pedestrian extends MovableEntity {
    public Pedestrian(int originX, int originY, Direction direction, PedestrianType type) {
        super(originX, originY, direction, type);
    }
}
