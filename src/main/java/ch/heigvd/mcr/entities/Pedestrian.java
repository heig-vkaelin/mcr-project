package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;
import ch.heigvd.mcr.entities.types.PedestrianType;

/**
 * Classe représentant un piéton pouvant se déplacer de manière aléatoire et autonome
 * sur le board
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class Pedestrian extends MovableEntity {
    /**
     * Crée un piéton
     *
     * @param position  : position initiale du piéton
     * @param direction : direction du piéton
     * @param type      : type de piéton
     */
    public Pedestrian(Position position, Direction direction, PedestrianType type) {
        super(position, direction, type);
    }

    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public void onCrash() {
        AudioManager.getInstance().play(AssetManager.audios.get("death"));
    }
}
