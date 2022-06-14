package ch.heigvd.mcr.entities;

import java.awt.*;

/**
 * Classe abstraite représentant une entité du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public abstract class Entity {
    private final Direction direction;
    private final EntityType type;
    protected Position position;

    public Entity(Position position, Direction direction, EntityType type) {
        this.position = position;
        this.direction = direction;
        this.type = type;
    }

    public int getX() {
        return position.x();
    }

    public int getY() {
        return position.y();
    }

    public void setPosition(int x, int y) {
        this.position = new Position(x, y);
    }

    public Direction getDirection() {
        return direction;
    }

    public EntityType getType() {
        return type;
    }

    public int getWidth() {
        if (Direction.isVertical(direction))
            return type.getWidth();

        return type.getLength();
    }

    public int getHeight() {
        if (Direction.isVertical(direction))
            return type.getLength();

        return type.getWidth();
    }

    public Rectangle getBounds() {
        return new Rectangle(position.x(), position.y(), getWidth(), getHeight());
    }

    public boolean isColliding(Entity entity) {
        return getBounds().intersects(entity.getBounds());
    }

    public boolean isColliding(Entity e, int x, int y) {
        Rectangle b = getBounds();
        b.setLocation(x, y);//to test if the entity is colliding with the new position
        return b.intersects(e.getBounds());
    }

    public abstract boolean isInteractive();

    public abstract void onCrash();

    public EntityDescriptor<?> getDescriptor() {
        return new EntityDescriptor<>(getClass(), position, direction, type);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isThePlayer() {
        return false;
    }
}
