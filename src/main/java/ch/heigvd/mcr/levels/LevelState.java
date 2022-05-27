package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.Difficulty;
import ch.heigvd.mcr.entities.Entity;

import java.util.LinkedList;

/**
 * Classe représentant l'état d'un niveau
 *
 * @author Nicolas Crausaz
 */
public class LevelState {

    private int sideSize;
    private Difficulty difficulty;
    private final LinkedList<Entity> entities;

    public LevelState() {
        this.entities = new LinkedList<>();
    }

    public void setSideSize(int sideSize) {
        if (sideSize < 1) throw new RuntimeException("Invalid size");

        this.sideSize = sideSize;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public int getSideSize() {
        return sideSize;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }
}
