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
public class DraggableEntity extends JPanel { // Idealement un jlabel

    // TODO: Implementer drag & drop et modifier les coordonnées des entités

    private final Entity entity;

    private final Image image;

    public DraggableEntity(Entity entity) {
        this.entity = entity;
        this.image = AssetManager.sprites.get("cars").get(entity.getType().getKey());

        setOpaque(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, 0, 0, null);
    }
}
