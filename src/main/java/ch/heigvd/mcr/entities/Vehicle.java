package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;
import ch.heigvd.mcr.entities.types.VehicleType;

/**
 * Classe représentant un véhicule pouvant se déplacer sur le board
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
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
