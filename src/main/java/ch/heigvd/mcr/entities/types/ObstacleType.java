package ch.heigvd.mcr.entities.types;

import java.util.Arrays;

/**
 * Enum spécifiant les types disponibles pour les obstacles
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
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

    /**
     * Crée un nouveau type d'obstacle
     *
     * @param key : clé associée au type
     */
    ObstacleType(String key) {
        this.key = key;
        this.width = 1;
        this.length = 1;
    }

    /**
     * Récupère un type d'obstacle en fonction de sa clé
     *
     * @param key : clé du type, non sensible à la casse
     * @return le type d'obstacles souhaité
     * @throws IllegalArgumentException si la clé est invalide
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
    public TypeCategory getCategory() {
        return TypeCategory.OBSTACLE;
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
