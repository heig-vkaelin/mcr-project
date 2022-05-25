package entities;

import utils.Pair;

/**
 * Classe représentant une entité affichable sur la grille
 *
 * @author Lazar Pavicevic
 */
public abstract class Entity {
    private Pair<Integer, Integer> coords;
    private Direction direction;
    private Color color;

    public Entity(int originX, int originY, Direction direction, Color color) {
        coords = new Pair<>(originX, originY);
        this.direction = direction;
        this.color = color;
    }
}
