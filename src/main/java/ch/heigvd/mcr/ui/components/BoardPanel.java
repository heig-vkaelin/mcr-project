package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.Direction;
import ch.heigvd.mcr.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BoardPanel extends JPanel {

    private final int size;
    private final LinkedList<DraggableEntity> draggables;

    private int ratio;

    private int offset;

    private int exitPos;

    private Direction exitSide;

    public BoardPanel(int size, LinkedList<Entity> entities, int exitPos, Direction exitSide) {
        this.size = size;
        this.draggables = new LinkedList<>();
        this.offset = 1;
        this.ratio = 1;

        this.exitPos = exitPos;
        this.exitSide = exitSide;

        for (Entity e : entities) {
            DraggableEntity de = new DraggableEntity(e, ratio);
            this.draggables.add(de);
            add(de);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // TODO: Refactor
        super.paintComponent(g);

        ratio = Math.min(getWidth(), getHeight()) / (size + 2);
        offset = (getWidth() - ratio * (size + 2)) / 2;

        // Display first row
        drawTopSide(g, Direction.UP, "T");


        // Left borders
        // drawSide(g, Direction.LEFT, "L");
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


        drawBottomSide(g, Direction.DOWN, "B");
/*
        // Last row
        g.drawImage(AssetManager.sprites.get("board").get("BL"), offset, (size + 1) * ratio, ratio, ratio, null);

        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("B"), offset + i * ratio, (size + 1) * ratio, ratio, ratio, null);
        }

 */


        // update the scale of entities
        for (DraggableEntity e : draggables) {
            e.setRatio(ratio);
            e.setOffset(offset + ratio);
        }
    }

    private void drawTopSide(Graphics g, Direction side, String assetKey) {
        g.drawImage(AssetManager.sprites.get("board").get(assetKey + "L"), offset, 0, ratio, ratio, null);
        for (int i = 1; i <= size; ++i) {
            if (exitSide == side) {
                if (i - 1 == exitPos - 1) {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey + "H0"), offset + i * ratio, 0, ratio, ratio, null);
                } else if (i - 1 == exitPos) {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey + "H1"), offset + i * ratio, 0, ratio, ratio, null);
                } else if (i - 1 == exitPos + 1) {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey + "H2"), offset + i * ratio, 0, ratio, ratio, null);
                } else {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey), offset + i * ratio, 0, ratio, ratio, null);
                }
            } else {
                g.drawImage(AssetManager.sprites.get("board").get(assetKey), offset + i * ratio, 0, ratio, ratio, null);
            }
        }
        g.drawImage(AssetManager.sprites.get("board").get(assetKey + "R"), offset + (size + 1) * ratio, 0, ratio, ratio, null);
    }

    private void drawBottomSide(Graphics g, Direction side, String assetKey) {
        g.drawImage(AssetManager.sprites.get("board").get(assetKey + "L"), offset, (size + 1) * ratio, ratio, ratio, null);

        for (int i = 1; i <= size; ++i) {
            if (exitSide == side) {
                if (i - 1 == exitPos - 1) {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey + "H0"), offset + i * ratio, (size + 1) * ratio, ratio, ratio, null);
                } else if (i - 1 == exitPos) {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey + "H1"), offset + i * ratio, (size + 1) * ratio, ratio, ratio, null);
                } else if (i - 1 == exitPos + 1) {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey + "H2"), offset + i * ratio, (size + 1) * ratio, ratio, ratio, null);
                } else {
                    g.drawImage(AssetManager.sprites.get("board").get(assetKey), offset + i * ratio, (size + 1) * ratio, ratio, ratio, null);
                }
            } else {
                g.drawImage(AssetManager.sprites.get("board").get(assetKey), offset + i * ratio, (size + 1) * ratio, ratio, ratio, null);
            }
        }

        g.drawImage(AssetManager.sprites.get("board").get(assetKey + "R"), offset + (size + 1) * ratio, (size + 1) * ratio, ratio, ratio, null);
    }

    private void checkExit(Direction side, int i) {

    }
}
