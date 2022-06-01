package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.Difficulty;
import ch.heigvd.mcr.entities.Entity;

import java.awt.*;
import java.util.LinkedList;

/**
 * Classe représentant l'état d'un niveau
 *
 * @author Nicolas Crausaz
 */
public class LevelState {
    
    private int id;
    private int sideSize;
    private Difficulty difficulty;
    private final LinkedList<Entity> entities;

    public LevelState(int id) {
        this.id = id;
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
        if (validateLevelCoherence(entity)) {
            this.entities.add(entity);
        } else {
            throw new RuntimeException("Invalid entity configuration for this level state");
        }

    }
    
    public int getId() {
        return id;
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

    /**
     * Valide si l'ajout d'une nouvelle entité dans le jeu
     * @param newEntity entité à valider
     * @return vrai si les contraintes sont respectées, sinon faux
     */
    private boolean validateLevelCoherence(Entity newEntity) {
        final Rectangle bounds = newEntity.getBounds();
        System.out.println(newEntity.getType() + " " + newEntity.getDirection() + " " + newEntity.getBounds());

        for (Entity e : getEntities()) {

            if (bounds.contains(e.getBounds()) || bounds.intersects(e.getBounds())) {
                return false;
            }
        }
        return true;
    }
}
