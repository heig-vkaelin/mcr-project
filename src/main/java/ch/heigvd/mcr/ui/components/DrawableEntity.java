package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.*;

import javax.swing.*;
import java.awt.*;

/**
 * Entité pouvant être dessinée
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class DrawableEntity extends JLabel {
    private final Entity entity;
    private Image image;
    private int ratio;
    private int offset;

    /**
     * Crée une nouvelle entité pouvant être dessinée
     *
     * @param entity    : entité de base
     * @param baseRatio : ratio initial de l'affichage
     */
    public DrawableEntity(Entity entity, int baseRatio) {
        this.entity = entity;
        this.ratio = baseRatio;

        updateImage();

        setIcon(new ImageIcon(image));
    }

    /**
     * @return le ratio actuel de l'affichage
     */
    public int getRatio() {
        return ratio;
    }

    /**
     * Modifie le ratio actuel de l'affichage
     *
     * @param ratio : nouveau ratio
     */
    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    /**
     * Modifie le décalage à gauche de l'affichage
     *
     * @param offset : nouveau décalage
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return l'entité associée
     */
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateImage();

        setBounds(offset + entity.getX() * ratio,
                ratio + entity.getY() * ratio, ratio * entity.getWidth(),
                ratio * entity.getHeight()
        );

        Icon icon = new RotatedIcon(new ImageIcon(image), entity.getDirection());
        icon.paintIcon(this, g, 0, 0);
    }

    /**
     * Met à jour l'image de l'entité
     */
    private void updateImage() {
        Image tmp = AssetManager.sprites.get(entity.getType().getCategory().getKey())
                .get(entity.getType().getKey());

        image = tmp.getScaledInstance(
                ratio * entity.getType().getWidth(),
                ratio * entity.getType().getLength(), Image.SCALE_DEFAULT
        );
    }
}
