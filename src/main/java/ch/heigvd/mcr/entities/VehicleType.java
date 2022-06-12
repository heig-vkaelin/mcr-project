package ch.heigvd.mcr.entities;

import java.util.Arrays;

/**
 * Enum spécifiant les types disponibles pour les véhicules
 *
 * @author Lazar Pavicevic
 * @author Nicolas Crausaz
 */
public enum VehicleType implements EntityType {
    RED_CAR("red", 1, 2),
    BLUE_CAR("blue", 1, 2),
    STRIPED_CAR("striped", 1, 2),
    YELLOW_CAR("yellow", 1, 2),
    GRAY_CAR("gray", 1, 2),
    TAXI("taxi", 1, 2),
    POLICE("police", 1, 2),
    VAN("van", 1, 2),
    TRUCK("truck", 1, 3),
    SCHOOL_BUS("school_bus", 1, 3),
    BUS("bus", 1, 4);

    private final String key;
    private final int width;
    private final int length;

    VehicleType(String key, int width, int length) {
        this.key = key;
        this.width = width;
        this.length = length;
    }

    /**
     * Récupère un type en fonction de sa clé
     *
     * @param key clé du type, non sensible a la casse
     * @return Direction liée au type
     * @throws IllegalArgumentException si clé invalide
     */
    public static VehicleType getFromKey(String key) {
        return Arrays.stream(VehicleType.values())
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
        return "cars";
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
