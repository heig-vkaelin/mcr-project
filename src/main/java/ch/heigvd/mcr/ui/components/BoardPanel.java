package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.Direction;
import ch.heigvd.mcr.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.function.Function;

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
        drawHSide(g, Direction.UP, "T", 0);
        drawVSide(g, Direction.LEFT, "L", 0);
        drawVSide(g, Direction.RIGHT, "R", size + 1);
        drawHSide(g, Direction.DOWN, "B", size + 1);

        // Center
        for (int i = 1; i <= size; ++i) {
            for (int j = 1; j <= size; ++j) {
                drawSprite(g, "C", i, j);
            }
        }

        // update the scale of entities
        for (DraggableEntity e : draggables) {
            e.setRatio(ratio);
            e.setOffset(offset + ratio);
        }
    }

    private void drawHSide(Graphics g, Direction side, String assetKey, int fixedCoordinate) {
        drawSprite(g, assetKey + "L", 0, fixedCoordinate);
        for (int i = 1; i <= size; ++i) {
            drawBorders(g, side, assetKey, i, fixedCoordinate, i);
        }
        drawSprite(g, assetKey + "R", (size + 1), fixedCoordinate);
    }

    private void drawVSide(Graphics g, Direction side, String assetKey, int fixedCoordinate) {
        for (int i = 1; i <= size; ++i) {
            drawBorders(g, side, assetKey, fixedCoordinate, i, i);
        }
    }

    private void drawBorders(Graphics g, Direction side, String assetKey, int x, int y, int exit) {
        if (exitSide == side) {
            if (exit - 1 == exitPos - 1) {
                drawSprite(g, assetKey + "H0", x, y);
            } else if (exit - 1 == exitPos) {
                drawSprite(g, assetKey + "H1", x, y);
            } else if (exit - 1 == exitPos + 1) {
                drawSprite(g, assetKey + "H2", x, y);
            } else {
                drawSprite(g, assetKey, x, y);
            }
        } else {
            drawSprite(g, assetKey, x, y);
        }
    }

    private void drawSprite(Graphics g, String key, int x, int y) {
        g.drawImage(
                AssetManager.sprites.get("board").get(key),
                offset + x * ratio,
                y * ratio,
                ratio,
                ratio,
                null
        );
    }
}
