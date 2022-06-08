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
        drawHSide(g, Direction.UP, "T", 0);
        drawLeftSide(g);
        drawRightSide(g);
        drawHSide(g, Direction.DOWN, "B", (size + 1) * ratio);

        // Center
        for (int i = 1; i <= size; ++i) {
            for (int j = 1; j <= size; ++j) {
                drawSprite(g, "C", offset + i * ratio, j * ratio);
            }
        }

        // update the scale of entities
        for (DraggableEntity e : draggables) {
            e.setRatio(ratio);
            e.setOffset(offset + ratio);
        }
    }

    private void drawHSide(Graphics g, Direction side, String assetKey, int fixedCoordinate) {
        drawSprite(g, assetKey + "L", offset, fixedCoordinate);
        for (int i = 1; i <= size; ++i) {
            if (exitSide == side) {
                if (i - 1 == exitPos - 1) {
                    drawSprite(g, assetKey + "H0", offset + i * ratio, fixedCoordinate);
                } else if (i - 1 == exitPos) {
                    drawSprite(g, assetKey + "H1", offset + i * ratio, fixedCoordinate);
                } else if (i - 1 == exitPos + 1) {
                    drawSprite(g, assetKey + "H2", offset + i * ratio, fixedCoordinate);
                } else {
                    drawSprite(g, assetKey, offset + i * ratio, fixedCoordinate);
                }
            } else {
                drawSprite(g, assetKey, offset + i * ratio, fixedCoordinate);
            }
        }
        drawSprite(g, assetKey + "R", offset + (size + 1) * ratio, fixedCoordinate);
    }

    private void drawLeftSide(Graphics g) {
        for (int i = 1; i <= size; ++i) {
            if (exitSide == Direction.LEFT) {
                if (i - 1 == exitPos - 1) {
                    drawSprite(g, "LH0", offset, i * ratio);
                } else if (i - 1 == exitPos) {
                    drawSprite(g, "LH1", offset, i * ratio);
                } else if (i - 1 == exitPos + 1) {
                    drawSprite(g, "LH2", offset, i * ratio);
                } else {
                    drawSprite(g, "L", offset, i * ratio);
                }
            } else {
                drawSprite(g, "L", offset, i * ratio);
            }
        }
    }

    private void drawRightSide(Graphics g) {
        for (int i = 1; i <= size; ++i) {
            if (exitSide == Direction.RIGHT) {
                if (i - 1 == exitPos - 1) {
                    drawSprite(g, "RH0", offset + (size + 1) * ratio, i * ratio);
                } else if (i - 1 == exitPos) {
                    drawSprite(g, "RH1", offset + (size + 1) * ratio, i * ratio);
                } else if (i - 1 == exitPos + 1) {
                    drawSprite(g, "RH2", offset + (size + 1) * ratio, i * ratio);
                } else {
                    drawSprite(g, "R", offset + (size + 1) * ratio, i * ratio);
                }
            } else {
                drawSprite(g, "R", offset + (size + 1) * ratio, i * ratio);
            }
        }
    }

    private void drawSprite(Graphics g, String key, int x, int y) {
        g.drawImage(AssetManager.sprites.get("board").get(key), x, y, ratio, ratio, null);
    }
}
