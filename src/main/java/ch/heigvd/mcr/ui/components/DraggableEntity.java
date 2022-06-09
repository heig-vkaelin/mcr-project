package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.Direction;
import ch.heigvd.mcr.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Classe pour l'affichage et le déplacement d'entités sur le plateau de jeu
 *
 * @author Nicolas Crausaz
 */
public class DraggableEntity extends JLabel {

    // TODO: Implementer drag & drop et modifier les coordonnées des entités

    private final Entity entity;

    private Image image;

    private int ratio;

    private int offset;

    private int offsetX;
    private int offsetY;
    private boolean isDragged;

    public DraggableEntity(Entity entity, int baseRatio) {
        this.entity = entity;
        this.ratio = baseRatio;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Image tmp = AssetManager.sprites.get(entity.getType().getCategoryKey()).get(entity.getType().getKey());

        this.image = tmp.getScaledInstance(ratio * entity.getType().getWidth(), ratio * entity.getType().getLength(), Image.SCALE_DEFAULT);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        DragListener dragListener = new DragListener() {
            ValidateState state;

            @Override
            public void dragStarted(MouseEvent e) {
                System.out.println("dragStarted at " + entity.getX() + " " + entity.getY());
            }

            @Override
            public void dragEnded(MouseEvent e) {
                System.out.println("dragEnded at " + state.x() + " " + state.y() + " collision: " + state.collidedEntity());
            }

            @Override
            public void dragMoved(MouseEvent e) {
                int x = (int) Math.round((e.getX() - offsetX) / (double) ratio + entity.getX());
                int y = (int) Math.round((e.getY() - offsetY) / (double) ratio + entity.getY());
                if (x != entity.getX() || y != entity.getY()) {
                    state = GameController.getInstance().validatePosition(entity, x, y);
                    entity.setX(state.x());
                    entity.setY(state.y());
                    if (state.collidedEntity() != null) {
                        stopDragging(); // to avoid infinite calls
                        AssetManager.audios.get("horn").play();
                        System.out.println("Entity[" + entity.getType() + "] Colliding with " + state.collidedEntity().getType());
                    }
                    repaint();
                }
            }
        };
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
        setIcon(new ImageIcon(image));
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getEntityDefaultWidth() {
        return entity.getType().getWidth();
    }

    public int getEntityDefaultLenght() {
        return entity.getType().getLength();
    }

    public int getCurrentX() {
        return entity.getX();
    }

    public int getCurrentY() {
        return entity.getX();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image tmp = AssetManager.sprites.get(entity.getType().getCategoryKey()).get(entity.getType().getKey());

        this.image = tmp.getScaledInstance(ratio * entity.getType().getWidth(), ratio * entity.getType().getLength(), Image.SCALE_DEFAULT);

        if (entity.getDirection() == Direction.UP || entity.getDirection() == Direction.DOWN)
            setBounds(offset + entity.getX() * ratio, ratio + entity.getY() * ratio, ratio * entity.getType().getWidth(), ratio * entity.getType().getLength());
        else
            setBounds(offset + entity.getX() * ratio, ratio + entity.getY() * ratio, ratio * entity.getType().getLength(), ratio * entity.getType().getWidth());

        Icon icon = new RotatedIcon(new ImageIcon(image), entity.getDirection());
        icon.paintIcon(this, g, 0, 0);
    }
}
