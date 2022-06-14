package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant l'état d'un niveau
 *
 * @author Nicolas Crausaz
 */
public class LevelState {
    private final int id;

    private final LinkedList<EntityDescriptor<?>> descriptors;
    private final LinkedList<Entity> entities;
    private final List<Pedestrian> pedestrians;
    private int sideSize;
    private Difficulty difficulty;
    private int exitPos;
    private Direction exitSide;
    private int nbMoves;


    public LevelState(int id) {
        this.id = id;
        entities = new LinkedList<>();
        descriptors = new LinkedList<>();
        pedestrians = new LinkedList<>();
        nbMoves = 0;
    }

    /**
     * Ajoute un véhicule au niveau
     *
     * @param position  position du véhicule
     * @param direction direction du véhicule
     * @param type      type de véhicule
     */
    public void addVehicle(Position position, Direction direction, VehicleType type) {
        addEntity(new Vehicle(position, direction, type));
    }

    /**
     * Ajoute un véhicule du joueur au niveau
     *
     * @param position  position du véhicule
     * @param direction direction du véhicule
     * @param type      type de véhicule
     */
    public void addPlayer(Position position, Direction direction, VehicleType type) {
        addEntity(new PlayerVehicle(position, direction, type));
    }

    /**
     * Ajoute un obstacle au niveau
     *
     * @param position  position de l'obstacle
     * @param direction direction de l'obstacle
     * @param type      type de l'obstacle
     */
    public void addObstacle(Position position, Direction direction, ObstacleType type) {
        addEntity(new Obstacle(position, direction, type));
    }

    /**
     * Ajoute un piéton au niveau
     *
     * @param position  position du piéton
     * @param direction direction du piéton
     * @param type      type de piéton
     */
    public void addPedestrian(Position position, Direction direction, PedestrianType type) {
        Pedestrian p = new Pedestrian(position, direction, type);
        addEntity(p);
        pedestrians.add(p);
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

    public List<Pedestrian> getPedestrians() {
        return pedestrians;
    }

    public void addMove() {
        nbMoves++;
    }

    public void cancelMove() {
        nbMoves--;
    }

    public int getNbMoves() {
        return nbMoves;
    }

    public void resetMoves() {
        nbMoves = 0;
    }

    public void saveState() {
        descriptors.clear();
        for (Entity e : entities) {
            descriptors.add(e.getDescriptor());
        }
    }

    public void loadState() {
        entities.clear();
        pedestrians.clear();
        for (EntityDescriptor<?> d : descriptors) {
            Entity e = d.createEntity();
            entities.add(e);

            // TODO: cacher avant dimanche
            if (e.getType() instanceof PedestrianType) {
                pedestrians.add((Pedestrian) e);
            }
        }
        resetMoves();
    }
}
