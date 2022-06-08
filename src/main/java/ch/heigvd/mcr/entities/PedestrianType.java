package ch.heigvd.mcr.entities;

import java.util.Arrays;

/**
 * Enum spécifiant les types disponibles pour les piétons
 *
 * @author Lazar Pavicevic
 * @author Nicolas Crausaz
 */
public enum PedestrianType implements EntityType {
    BOY("boy"),
    GIRL("girl"),
    DOG("dog"),
    CAT("cat"),
    BIRD("bird"),
    MAN("man"),
    MAN2("man2"),
    MAN3("man3"),
    GRANDPA("grandpa"),
    BIGMAN("bigman"),
    WOMAN("woman"),
    MAN4("man4"),
    WEIRDMAN("weirdman"),
    MAN5("man5"),
    FATDOG("fatdog"),
    GANDALF("gandalf"),
    GIRL2("girl2"),
    GRANDMA("grandma"),
    GRANDMA2("grandma2"),
    GRANDMA3("grandma3"),
    FITGIRL("fitgirl");

    private final String key;
    private final int width;
    private final int length;

    PedestrianType(String key) {
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
    public static PedestrianType getFromKey(String key) {
        return Arrays.stream(PedestrianType.values())
                .filter(type -> key.equalsIgnoreCase(type.key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid type key"));
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getCategoryKey() {
        return "pedestrians";
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
