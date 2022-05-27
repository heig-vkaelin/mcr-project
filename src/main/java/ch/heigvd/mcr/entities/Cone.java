package ch.heigvd.mcr.entities;

/**
 * Classe représentant un cône de signalisation qui est une pièce immobile dans le jeu
 *
 * @author Lazar Pavicevic
 */
public class Cone extends Entity {

    public static final int WIDTH = 1;
    public static final int LENGTH = 1;

    public Cone(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }
}
