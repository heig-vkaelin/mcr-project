package ch.heigvd.mcr.levels;

import java.awt.*;

/**
 * Enum des différentes difficultés possibles pour les niveaux du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public enum Difficulty {
    EASY("Easy", new Color(34, 197, 94)),
    MEDIUM("Medium", new Color(250, 204, 21)),
    HARD("Difficult", new Color(220, 38, 38));

    private final String name;
    private final Color color;

    /**
     * Crée une nouvelle difficulté
     *
     * @param name  : nom de la difficulté
     * @param color : couleur de la difficulté
     */
    Difficulty(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     * @return le texte correspondant à la difficulté
     */
    public String getName() {
        return name;
    }

    /**
     * @return la couleur correspondante à la difficulté
     */
    public Color getColor() {
        return color;
    }
}
