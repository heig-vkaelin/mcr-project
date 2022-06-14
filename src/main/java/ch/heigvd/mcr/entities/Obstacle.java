package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;

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
