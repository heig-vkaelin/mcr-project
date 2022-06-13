package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.assets.AssetManager;

/**
 * Classe représentant un véhicule pouvant se déplacer sur le board
 *
 * @author Lazar Pavicevic
 */
public class Vehicle extends MovableEntity {
    public Vehicle(int originX, int originY, Direction direction, VehicleType type) {
        super(originX, originY, direction, type);
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void onCrash() {
        AssetManager.audios.get("horn").play();
    }
}
