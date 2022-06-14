package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.assets.AssetManager;

/**
 * Classe représentant un piéton pouvant se déplacer de manière autonome sur le board
 *
 * @author Lazar Pavicevic
 */
public class Pedestrian extends MovableEntity {
    public Pedestrian(Position position, Direction direction, PedestrianType type) {
        super(position, direction, type);
    }

    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public void onCrash() {
        AssetManager.audios.get("death").play();
    }
}
