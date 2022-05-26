package entities;

import java.util.Arrays;

/**
 * Enum spécifiant les couleurs disponibles pour les pièces
 *
 * @author Lazar Pavicevic
 * @author Nicolas Crausaz
 */
public enum Color {
    RED("red"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    PINK("pink"),
    BROWN("brown"),
    WHITE("white"),
    ORANGE("orange"),
    BLACK("black");

    private final String key;

    private Color(String key) {
        this.key = key;
    }

    /**
     * Récupère une couleur en fonction de sa clé
     *
     * @param key clé de la couleur, non sensible a la casse
     * @return Direction liée à la couleur
     * @throws IllegalArgumentException si clé invalide
     */
    public static Color getFromKey(String key) {
        return Arrays.stream(Color.values())
                .filter(color -> key.equalsIgnoreCase(color.key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid color key"));
    }
}
