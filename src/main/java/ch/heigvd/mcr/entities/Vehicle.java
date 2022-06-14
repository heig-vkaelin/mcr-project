package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;

/**
 * Classe représentant un véhicule pouvant se déplacer sur le board
 *
 * @author Lazar Pavicevic
 */
public class Vehicle extends MovableEntity {
    public Vehicle(Position position, Direction direction, VehicleType type) {
        super(position, direction, type);
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void onCrash() {
        AudioManager.getInstance().play(AssetManager.audios.get("horn"));
    }
}
