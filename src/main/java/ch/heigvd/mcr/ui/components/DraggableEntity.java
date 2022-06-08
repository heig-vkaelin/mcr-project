package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.*;

import javax.swing.*;
import java.awt.*;

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

    public DraggableEntity(Entity entity, int baseRatio) {
        this.entity = entity;
        this.ratio = baseRatio;

        Image tmp = AssetManager.sprites.get(entity.getType().getCategoryKey()).get(entity.getType().getKey());

        this.image = tmp.getScaledInstance(ratio * entity.getType().getWidth(), ratio * entity.getType().getLength(), Image.SCALE_DEFAULT);

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

        Icon icon = null;
        switch (entity.getDirection()) {
            case UP ->
                    icon = new RotatedIcon(new ImageIcon(image), RotatedIcon.Rotate.UPSIDE_DOWN);
            case DOWN -> icon = new ImageIcon(image);
            case LEFT ->
                    icon = new RotatedIcon(new ImageIcon(image), RotatedIcon.Rotate.DOWN);
            case RIGHT ->
                    icon = new RotatedIcon(new ImageIcon(image), RotatedIcon.Rotate.UP);
        }

        icon.paintIcon(this, g, 0, 0);
    }
}
