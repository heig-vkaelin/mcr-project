package ch.heigvd.mcr.entities;

import java.util.Arrays;

/**
 * Enum spécifiant les orientations possibles pour une entité
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public enum Direction {
    UP("u"), DOWN("d"), LEFT("l"), RIGHT("r");

    private final String key;

    /**
     * Crée une nouvelle orientation
     *
     * @param key : clé associée à l'orientation
     */
    Direction(String key) {
        this.key = key;
    }

    /**
     * Vérifie si l'orientation est verticale
     *
     * @return true si l'orientation est verticale, false sinon
     */
    public boolean isVertical() {
        return this == Direction.UP || this == Direction.DOWN;
    }

    /**
     * Récupère une orientation en fonction de sa clé
     *
     * @param key : clé de l'ortientation, non sensible à la casse
     * @return la direction liée à la clé
     * @throws IllegalArgumentException si la clé est invalide
     */
    public static Direction getFromKey(String key) {
        return Arrays.stream(Direction.values())
                .filter(direction -> key.equalsIgnoreCase(direction.key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid direction key"));
    }
}
