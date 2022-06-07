package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private final int size;

    public BoardPanel(int size) {
        this.size = size;
    }

    @Override
    public void paint(Graphics g) {

        // TODO: Ajouter la sortie selon position et orientation de la voiture
        // TODO: Refactor

        final int side = Math.min(getWidth(), getHeight()) / (size + 2);
        final int offset = (getWidth() - side * (size + 2)) / 2;

        // Display first row
        g.drawImage(AssetManager.sprites.get("board").get("TL"), offset, 0, side, side, null);
        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("T"), offset + i * side, 0, side, side, null);
        }
        g.drawImage(AssetManager.sprites.get("board").get("TR"), offset + (size + 1) * side, 0, side, side, null);


        // Left borders
        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("L"), offset, i * side, side, side, null);
        }


        // Center
        for (int i = 1; i <= size; ++i) {
            for (int j = 1; j <= size; ++j) {
                g.drawImage(AssetManager.sprites.get("board").get("C"), offset + i * side, j * side, side, side, null);
            }
        }

        // Right borders
        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("R"), offset + (size + 1) * side, i * side, side, side, null);
        }


        // Last row
        g.drawImage(AssetManager.sprites.get("board").get("BL"), offset, (size + 1) * side, side, side, null);

        for (int i = 1; i <= size; ++i) {
            g.drawImage(AssetManager.sprites.get("board").get("B"), offset + i * side, (size + 1) * side, side, side, null);
        }


        g.drawImage(AssetManager.sprites.get("board").get("BR"), offset + (size + 1) * side, (size + 1) * side, side, side, null);
    }
}
