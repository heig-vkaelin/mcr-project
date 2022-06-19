package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.entities.types.VehicleType;

/**
 * Entité de type véhicule
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class PlayerVehicle extends Vehicle {
    /**
     * Crée un véhicule représentant le véhicule du joueur
     *
     * @param position  : position initiale du véhicule
     * @param direction : direction du véhicule
     * @param type      : type de véhicule
     */
    public PlayerVehicle(Position position, Direction direction, VehicleType type) {
        super(position, direction, type);
    }

    @Override
    public boolean isThePlayer() {
        return true;
    }
}
