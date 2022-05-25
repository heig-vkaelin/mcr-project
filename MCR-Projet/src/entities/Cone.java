package entities;

/**
 * Classe représentant un cône de signalisation qui est une pièce immobile dans le jeu
 *
 * @author Lazar Pavicevic
 */
public class Cone extends Entity{
    public Cone(int originX, int originY, Direction direction, Color color) {
        super(originX, originY, direction, color);
    }
}
