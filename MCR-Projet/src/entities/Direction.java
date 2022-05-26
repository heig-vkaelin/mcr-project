package entities;

import java.util.Arrays;

/**
 * Enum spécifiant les orientations possibles pour une pièce
 *
 * @author Lazar Pavicevic
 * @author Nicolas Crausaz
 */
public enum Direction {
    UP("u"), DOWN("d"), LEFT("l"), RIGHT("r");

    private final String key;

    private Direction(String key) {
        this.key = key;
    }

    /**
     * Récupère une direction en fonction de sa clé
     *
     * @param key clé de la direction, non sensible a la casse
     * @return Direction liée à la clé
     * @throws IllegalArgumentException si clé invalide
     */
    public static Direction getFromKey(String key) {
        return Arrays.stream(Direction.values())
                .filter(direction -> key.equalsIgnoreCase(direction.key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid direction key"));
    }
}
