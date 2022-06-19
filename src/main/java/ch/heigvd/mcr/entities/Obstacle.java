package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;
import ch.heigvd.mcr.entities.types.ObstacleType;

/**
 * Classe représentant un obstacle qui est une pièce immobile dans le jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class Obstacle extends Entity {
    /**
     * Crée un obstacle
     *
     * @param position  : position de l'obstacle
     * @param direction : direction de l'obstacle
     * @param type      : type de l'obstacle
     */
    public Obstacle(Position position, Direction direction, ObstacleType type) {
        super(position, direction, type);
    }

    @Override
    public boolean isInteractive() {
        return false;
    }

    @Override
    public void onCrash() {
        AudioManager.getInstance().play(AssetManager.audios.get("bonk"));
    }
}
