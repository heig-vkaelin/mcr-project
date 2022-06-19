package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant l'état actuel d'un niveau
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
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

    /**
     * Crée un nouvel état d'un niveau
     *
     * @param id : l'id du niveau
     */
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
     * @param position  : position du véhicule
     * @param direction : direction du véhicule
     * @param type      : type de véhicule
     */
    public void addVehicle(Position position, Direction direction, VehicleType type) {
        addEntity(new Vehicle(position, direction, type));
    }

    /**
     * Ajoute un véhicule du joueur au niveau
     *
     * @param position  : position du véhicule
     * @param direction : direction du véhicule
     * @param type      : type de véhicule
     */
    public void addPlayer(Position position, Direction direction, VehicleType type) {
        addEntity(new PlayerVehicle(position, direction, type));
    }

    /**
     * Ajoute un obstacle au niveau
     *
     * @param position  : position de l'obstacle
     * @param direction : direction de l'obstacle
     * @param type      : type de l'obstacle
     */
    public void addObstacle(Position position, Direction direction, ObstacleType type) {
        addEntity(new Obstacle(position, direction, type));
    }

    /**
     * Ajoute un piéton au niveau
     *
     * @param position  : position du piéton
     * @param direction : direction du piéton
     * @param type      : type de piéton
     */
    public void addPedestrian(Position position, Direction direction, PedestrianType type) {
        Pedestrian p = new Pedestrian(position, direction, type);
        addEntity(p);
        pedestrians.add(p);
    }

    /**
     * @return l'id du niveau
     */
    public int getId() {
        return id;
    }

    /**
     * @return la taille du plateau de jeu
     */
    public int getSideSize() {
        return sideSize;
    }

    /**
     * Définit la taille du plateau de jeu (carré)
     *
     * @param sideSize : taille souhaitée
     * @throws RuntimeException si la taille est invalide
     */
    public void setSideSize(int sideSize) throws RuntimeException {
        if (sideSize < 1) throw new RuntimeException("Invalid board size");

        this.sideSize = sideSize;
    }

    /**
     * @return la difficulté du niveau
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Définit la difficulté du niveau
     *
     * @param difficulty : difficulté souhaitée
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return les entités du niveau
     */
    public LinkedList<Entity> getEntities() {
        return entities;
    }

    /**
     * Définit la position et le côté de la sortie
     *
     * @param position  : position de la sortie
     * @param direction : côté de la sortie
     */
    public void setExit(int position, Direction direction) {
        this.exitPos = position;
        this.exitSide = direction;
    }

    /**
     * @return la position de la sortie
     */
    public int getExitPos() {
        return this.exitPos;
    }

    /**
     * @return le côté de la sortie
     */
    public Direction getExitSide() {
        return this.exitSide;
    }

    /**
     * Valide si l'ajout d'une nouvelle entité dans le jeu est cohérent
     *
     * @param newEntity : entité à valider
     * @return true si les contraintes sont respectées, false sinon
     */
    private boolean validateLevelCoherence(Entity newEntity) {
        final Rectangle bounds = newEntity.getBounds();
        Rectangle board = new Rectangle(0, 0, sideSize, sideSize);

        // Entité en dehors de la grille
        if (!board.contains(bounds))
            return false;

        // Collision avec une autre entité
        for (Entity e : getEntities())
            if (bounds.contains(e.getBounds()) || bounds.intersects(e.getBounds()))
                return false;

        return true;
    }

    /**
     * Ajoute une entité au niveau
     *
     * @param entity : entité à ajouter
     * @throws RuntimeException si l'ajoute d'une entité entraine une incohérence
     */
    public void addEntity(Entity entity) throws RuntimeException {
        if (validateLevelCoherence(entity)) {
            entities.add(entity);
        } else {
            throw new RuntimeException("Invalid entity configuration for this level state");
        }
    }

    /**
     * Supprime une entité du niveau
     *
     * @param entity : entité à supprimer
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * @return la liste des piétons du niveau
     */
    public List<Pedestrian> getPedestrians() {
        return pedestrians;
    }

    /**
     * Incrémente le nombre de mouvements effectués par le joueur
     */
    public void addMove() {
        nbMoves++;
    }

    /**
     * Décrémente le nombre de mouvements effectués par le joueur
     */
    public void cancelMove() {
        nbMoves--;
    }

    /**
     * @return le nombre de mouvements effectués par le joueur
     */
    public int getNbMoves() {
        return nbMoves;
    }

    /**
     * Sauvegarde l'état actuel du niveau
     */
    public void saveState() {
        descriptors.clear();
        for (Entity e : entities) {
            descriptors.add(e.getDescriptor());
        }
    }

    /**
     * Restaure l'état précédent du niveau
     */
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
        nbMoves = 0;
    }
}
