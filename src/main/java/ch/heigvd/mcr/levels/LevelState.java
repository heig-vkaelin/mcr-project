package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;

import java.awt.*;
import java.util.LinkedList;

/**
 * Classe représentant l'état d'un niveau
 *
 * @author Nicolas Crausaz
 */
public class LevelState {

    private final int id;
    private int sideSize;
    private Difficulty difficulty;
    private final LinkedList<Entity> entities;

    private int exitPos;

    private Direction exitSide;

    public LevelState(int id) {
        this.id = id;
        this.entities = new LinkedList<>();
    }

    /**
     * Défini la taille du plateau de jeu (carré)
     *
     * @param sideSize taille du coté du tableau de jeu
     * @throws RuntimeException si la taille est invalide
     */
    public void setSideSize(int sideSize) throws RuntimeException {
        if (sideSize < 1) throw new RuntimeException("Invalid size");

        this.sideSize = sideSize;
    }

    /**
     * Défini la difficulté du niveau
     *
     * @param difficulty difficulté du niveau
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Ajoute un véhicule au niveau
     *
     * @param x         coordonnée du véhicule sur l'axe x
     * @param y         coordonnée du véhicule sur l'axe y
     * @param direction direction du véhicule
     * @param type      type de véhicule
     */
    public void addVehicle(int x, int y, Direction direction, VehicleType type) {
        addEntity(new Vehicle(x, y, direction, type));
    }

    /**
     * Ajoute un obstacle au niveau
     *
     * @param x         coordonnée de l'obstacle sur l'axe x
     * @param y         coordonnée de l'obstacle sur l'axe y
     * @param direction direction de l'obstacle
     * @param type      type de l'obstacle
     */
    public void addObstacle(int x, int y, Direction direction, ObstacleType type) {
        addEntity(new Obstacle(x, y, direction, type));
    }

    /**
     * Ajoute un piéton au niveau
     *
     * @param x         coordonnée du piéton sur l'axe x
     * @param y         coordonnée du piéton sur l'axe y
     * @param direction direction du piéton
     * @param type      type de piéton
     */
    public void addPedestrian(int x, int y, Direction direction, PedestrianType type) {
        addEntity(new Pedestrian(x, y, direction, type));
    }

    /**
     * Récupère l'id du niveau
     *
     * @return id du niveau
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère la taille du plateau de jeu
     *
     * @return taille du plateau de jeu
     */
    public int getSideSize() {
        return sideSize;
    }

    /**
     * Récupère la difficulté du niveau
     *
     * @return difficulté du niveau
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Récupère les entités du niveau
     *
     * @return entités du niveau
     */
    public LinkedList<Entity> getEntities() {
        return entities;
    }

    public void setExit(int pos, Direction dir) {
        this.exitPos = pos;
        this.exitSide = dir;
    }

    public int getExitPos () {
        return this.exitPos;
    }

    public Direction getExitSide () {
        return this.exitSide;
    }

    /**
     * Valide si l'ajout d'une nouvelle entité dans le jeu est cohérent
     *
     * @param newEntity entité à valider
     * @return vrai si les contraintes sont respectées, sinon faux
     */
    private boolean validateLevelCoherence(Entity newEntity) {
        final Rectangle bounds = newEntity.getBounds();

        for (Entity e : getEntities()) {
            if (bounds.contains(e.getBounds()) || bounds.intersects(e.getBounds())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ajoute une entité au niveau
     *
     * @param entity entité à ajouter
     * @throws RuntimeException si l'ajoute d'une entité entraine une incohérence
     */
    private void addEntity(Entity entity) throws RuntimeException {
        if (validateLevelCoherence(entity)) {
            this.entities.add(entity);
        } else {
            throw new RuntimeException("Invalid entity configuration for this level state");
        }
    }
}
