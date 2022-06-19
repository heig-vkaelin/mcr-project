package ch.heigvd.mcr.entities.types;

/**
 * Enum représentant les différentes catégories de types d'entités
 */
public enum TypeCategory {
    OBSTACLE("obstacles"), PEDESTRIAN("pedestrians"), VEHICLE("cars");

    private final String key;

    /**
     * Crée une nouvelle catégorie de types d'entités
     *
     * @param key : clé associée à la catégorie
     */
    TypeCategory(String key) {
        this.key = key;
    }

    /**
     * @return la clé associée à la catégorie
     */
    public String getKey() {
        return key;
    }
}
