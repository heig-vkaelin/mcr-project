package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.entities.types.EntityType;

import java.awt.*;

/**
 * Classe abstraite représentant une entité du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public abstract class Entity {
    private final Direction direction;
    private final EntityType type;

    protected Position position;
    private boolean alive;

    /**
     * Crée une nouvelle entité
     *
     * @param position  : position initiale de l'entité
     * @param direction : direction de l'entité
     * @param type      : type de l'entité
     */
    public Entity(Position position, Direction direction, EntityType type) {
        this.direction = direction;
        this.type = type;
        this.position = position;
        this.alive = true;
    }

    /**
     * @return la coordonnée X de l'entité
     */
    public int getX() {
        return position.x();
    }

    /**
     * @return la coordonnée Y de l'entité
     */
    public int getY() {
        return position.y();
    }

    /**
     * @return la position complète de l'entité
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Modifie (sans vérification) la position de l'entité
     *
     * @param x : nouvelle coordonnée X
     * @param y : nouvelle coordonnée Y
     */
    public void setPosition(int x, int y) {
        position = new Position(x, y);
    }

    /**
     * Modifie (sans vérification) la position de l'entité
     *
     * @param position : nouvelle position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return l'orientation de l'entité
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return le type de l'entité
     */
    public EntityType getType() {
        return type;
    }

    /**
     * @return true si l'entité représente le joueur, false sinon
     */
    public boolean isThePlayer() {
        return false;
    }

    /**
     * Tue l'entité
     */
    public void kill() {
        alive = false;
    }

    /**
     * Fait revenir l'entité à la vie
     */
    public void revive() {
        alive = true;
    }

    /**
     * @return true si l'entité est vivante, false sinon
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @return la largeur de l'entité (en tenant compte de sa rotation)
     */
    public int getWidth() {
        if (direction.isVertical())
            return type.getWidth();

        return type.getLength();
    }

    /**
     * @return la hauteur de l'entité (en tenant compte de sa rotation)
     */
    public int getHeight() {
        if (direction.isVertical())
            return type.getLength();

        return type.getWidth();
    }

    /**
     * @return un Rectangle représentant le corps de l'entité
     */
    public Rectangle getBounds() {
        return new Rectangle(position.x(), position.y(), getWidth(), getHeight());
    }

    /**
     * Vérifie si l'entité entre en collision avec une autre à une position donnée
     *
     * @param other : autre entité
     * @param newX  : nouvelle coordonnée X à tester
     * @param newY  : nouvelle coordonnée Y à tester
     * @return true si les deux entités se touchent, false sinon
     */
    public boolean isColliding(Entity other, int newX, int newY) {
        Rectangle b = getBounds();
        b.setLocation(newX, newY);
        return b.intersects(other.getBounds());
    }

    /**
     * Stocke l'état actuel de l'entité afin de pouvoir le restaurer
     *
     * @return une copie de l'état actuel de l'entité
     */
    public EntityDescriptor<?> getDescriptor() {
        return new EntityDescriptor<>(getClass(), position, direction, type);
    }

    /**
     * Vérifie si le joueur peut intéragir avec l'entité
     *
     * @return true si le joueur peut intéragir avec l'entité, false sinon
     */
    public abstract boolean isInteractive();

    /**
     * Méthode appelée lorsque l'entité entre en collision avec une autre
     */
    public abstract void onCrash();
}
