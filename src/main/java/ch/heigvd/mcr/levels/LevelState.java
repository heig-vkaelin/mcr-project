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

    private final LinkedList<Entity> entities;
    private final int id;
    private int sideSize;
    private Difficulty difficulty;

    public LevelState(int id) {
        this.id = id;
        this.entities = new LinkedList<>();
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public int getId() {
        return id;
    }

    public int getSideSize() {
        return sideSize;
    }

    public void setSideSize(int sideSize) {
        if (sideSize < 1) throw new RuntimeException("Invalid size");

        this.sideSize = sideSize;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }
}
