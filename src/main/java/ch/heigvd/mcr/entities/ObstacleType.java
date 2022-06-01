package ch.heigvd.mcr.entities;

import java.util.Arrays;

/**
 * Enum spécifiant les types disponibles pour les obstacles
 *
 * @author Lazar Pavicevic
 * @author Nicolas Crausaz
 */
public enum ObstacleType implements EntityType {
    MAIL_BOX("mailbox"),
    HYDRANT("hydrant"),
    TRASH_BIN("trashbin"),
    OIL_STAIN("oilstain"),
    BARRIER("barrier"),
    CONE("cone"),
    BARRIER2("barrier2"),
    RESERVOIR("reservoir");

    private final String key;
    private final int width;
    private final int length;

    ObstacleType(String key) {
        this.key = key;
        this.width = 1;
        this.length = 1;
    }

    /**
     * Récupère un type en fonction de sa clé
     *
     * @param key clé du type, non sensible a la casse
     * @return Direction liée au type
     * @throws IllegalArgumentException si clé invalide
     */
    public static ObstacleType getFromKey(String key) {
        return Arrays.stream(ObstacleType.values())
                .filter(type -> key.equalsIgnoreCase(type.key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid type key"));
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getLength() {
        return length;
    }
}