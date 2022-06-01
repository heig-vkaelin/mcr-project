package ch.heigvd.mcr.entities;

/**
 * Classe représentant un véhicule pouvant se déplacer sur le board
 *
 * @author Lazar Pavicevic
 */
public class Vehicle extends MovableEntity {
    public Vehicle(int originX, int originY, Direction direction, VehicleType type) {
        super(originX, originY, direction, type);
    }
}
