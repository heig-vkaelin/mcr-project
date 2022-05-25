package entities;

/**
 * Enum spécifiant les orientations possibles pour une pièce
 *
 * @author Lazar Pavicevic
 */
public enum Direction {
    UP('u'), DOWN('d'), LEFT('l'), RIGHT('r');

    private final char key;

    Direction(char key) {
        this.key = key;
    }
    }
