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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image tmp = AssetManager.sprites.get(entity.getType().getCategoryKey()).get(entity.getType().getKey());

        this.image = tmp.getScaledInstance(ratio * entity.getType().getWidth(), ratio * entity.getType().getLength(), Image.SCALE_DEFAULT);

        setIcon(new ImageIcon(image));
    }
}
