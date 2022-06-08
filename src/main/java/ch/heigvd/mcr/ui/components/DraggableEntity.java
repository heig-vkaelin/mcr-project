package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.Entity;

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

    private final Image image;

    public DraggableEntity(Entity entity) {
        this.entity = entity;
        this.image = AssetManager.sprites.get("cars").get(entity.getType().getKey()).getScaledInstance(100, 200, Image.SCALE_DEFAULT);

        setIcon(new ImageIcon(image));

        setOpaque(true);
    }
/*
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        System.out.println("repaint car");

        g.drawImage(image, 0, 0, 200, 100, null);
    }

 */
}
