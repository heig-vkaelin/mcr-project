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
    private final LinkedList<Entity> entities;
    private int sideSize;
    private Difficulty difficulty;
    private int exitPos;

    private Direction exitSide;

    public LevelState(int id) {
        this.id = id;
        this.entities = new LinkedList<>();
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
     * Récupère la difficulté du niveau
     *
     * @return difficulté du niveau
     */
    public Difficulty getDifficulty() {
        return difficulty;
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
     * Récupère les entités du niveau
     *
     * @return entités du niveau
     */
    public LinkedList<Entity> getEntities() {
        return entities;
    }

    /**
     * Défini la position et le coté de la sortie
     *
     * @param pos position de la sortie
     * @param dir coté de la sortie
     */
    public void setExit(int pos, Direction dir) {
        this.exitPos = pos;
        this.exitSide = dir;
    }

    /**
     * Récupère la position de la sortie
     *
     * @return position de la sortie
     */
    public int getExitPos() {
        return this.exitPos;
    }

    /**
     * Récupère le côté de la sortie
     *
     * @return côté de la sortie
     */
    public Direction getExitSide() {
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
        Rectangle board = new Rectangle(0, 0, sideSize, sideSize);

        if (!board.contains(bounds))
            return false;

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
