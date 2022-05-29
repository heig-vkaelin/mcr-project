package ch.heigvd.mcr.entities;

/**
 * Classe représentant un obstacle qui est une pièce immobile dans le jeu
 *
 * @author Lazar Pavicevic
 */
public class Obstacle extends Entity {
    public Obstacle(int originX, int originY, Direction direction, ObstacleType type) {
        super(originX, originY, direction, type);
    }
}
