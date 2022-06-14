package ch.heigvd.mcr.entities;

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
    public PlayerVehicle(Position position, Direction direction, VehicleType type) {
        super(position, direction, type);
    }

    @Override
    public boolean isThePlayer() {
        return true;
    }
}
