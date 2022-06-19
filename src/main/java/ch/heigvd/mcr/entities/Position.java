package ch.heigvd.mcr.entities;

import java.util.List;

/**
 * Record représentant une position dans la grille de jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public record Position(int x, int y) {
    public static final Position UP = new Position(0, -1);
    public static final Position DOWN = new Position(0, 1);
    public static final Position LEFT = new Position(-1, 0);
    public static final Position RIGHT = new Position(1, 0);
    public static final Position UP_LEFT = new Position(-1, -1);
    public static final Position UP_RIGHT = new Position(1, -1);
    public static final Position DOWN_LEFT = new Position(-1, 1);
    public static final Position DOWN_RIGHT = new Position(1, 1);

    public static final List<Position> DIRECTIONS =
            List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);

    /**
     * @return une position représentant direction aléatoire
     */
    public static Position randomDirection() {
        return DIRECTIONS.get((int) (Math.random() * DIRECTIONS.size()));
    }

    /**
     * Additionne deux positions
     *
     * @param p1 : première position
     * @param p2 : deuxième position
     * @return la somme des deux positions
     */
    public static Position add(Position p1, Position p2) {
        return new Position(p1.x + p2.x, p1.y + p2.y);
    }
}
