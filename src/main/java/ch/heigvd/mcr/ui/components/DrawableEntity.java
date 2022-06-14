package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.*;

import javax.swing.*;
import java.awt.*;

public class DrawableEntity extends JLabel {
    private final Entity entity;

    private Image image;

    private int ratio;

    private int offset;

    public DrawableEntity(Entity entity, int baseRatio) {
        this.entity = entity;
        this.ratio = baseRatio;

        Image tmp = AssetManager.sprites.get(entity.getType().getCategoryKey()).get(entity.getType().getKey());
        this.image = tmp.getScaledInstance(ratio * entity.getType().getWidth(), ratio * entity.getType().getLength(), Image.SCALE_DEFAULT);

        setIcon(new ImageIcon(image));
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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
