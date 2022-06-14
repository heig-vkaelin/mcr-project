package ch.heigvd.mcr.entities;

public class PlayerVehicle extends Vehicle {
    public PlayerVehicle(Position position, Direction direction, VehicleType type) {
        super(position, direction, type);
    }

    @Override
    public boolean isThePlayer() {
        return true;
    }
}
