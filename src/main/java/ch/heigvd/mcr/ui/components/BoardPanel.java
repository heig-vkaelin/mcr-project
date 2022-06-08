package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BoardPanel extends JPanel {

    private final int size;
    private final LinkedList<DraggableEntity> draggables;

    private int ratio;

    private int offset;

    public BoardPanel(int size, LinkedList<Entity> entities) {
        this.size = size;
        this.draggables = new LinkedList<>();
        this.offset = 1;
        this.ratio = 1;

        for (Entity e: entities) {
            DraggableEntity de = new DraggableEntity(e, ratio);
            this.draggables.add(de);
            add(de);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // TODO: Ajouter la sortie selon position et orientation de la voiture
        // TODO: Refactor
        super.paintComponent(g);

        ratio = Math.min(getWidth(), getHeight()) / (size + 2);
        offset = (getWidth() - ratio * (size + 2)) / 2;

        // Display first row
        g.drawImage(AssetManager.sprites.get("board").get("TL"), offset, 0, ratio, ratio, null);
        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("T"), offset + i * ratio, 0, ratio, ratio, null);
        }
        g.drawImage(AssetManager.sprites.get("board").get("TR"), offset + (size + 1) * ratio, 0, ratio, ratio, null);

        // Left borders
        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("L"), offset, i * ratio, ratio, ratio, null);
        }

        // Center
        for (int i = 1; i <= size; ++i) {
            for (int j = 1; j <= size; ++j) {
                g.drawImage(AssetManager.sprites.get("board").get("C"), offset + i * ratio, j * ratio, ratio, ratio, null);
            }
        }

        // Right borders
        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("R"), offset + (size + 1) * ratio, i * ratio, ratio, ratio, null);
        }

        // Last row
        g.drawImage(AssetManager.sprites.get("board").get("BL"), offset, (size + 1) * ratio, ratio, ratio, null);

        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("B"), offset + i * ratio, (size + 1) * ratio, ratio, ratio, null);
        }

        g.drawImage(AssetManager.sprites.get("board").get("BR"), offset + (size + 1) * ratio, (size + 1) * ratio, ratio, ratio, null);


        // update the scale of entities
        for (DraggableEntity e : draggables) {
            e.setRatio(ratio);
        }
    }
}
