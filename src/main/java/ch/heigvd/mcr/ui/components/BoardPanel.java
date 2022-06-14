package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.entities.Direction;
import ch.heigvd.mcr.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'afficher la grille de jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class BoardPanel extends JPanel {
    private final int size;
    private final List<DrawableEntity> drawables;
    private final int exitPos;
    private final Direction exitSide;
    private int ratio;
    private int offset;

    /**
     * Construit une nouvelle grille
     *
     * @param size     taille du coté de la grille (carrée)
     * @param entities entités à afficher sur la grille
     * @param exitPos  position de la sortie de la grille
     * @param exitSide coté de la sortie de la grille
     */
    public BoardPanel(int size, List<Entity> entities, int exitPos, Direction exitSide) {
        this.size = size;
        this.drawables = new ArrayList<>();
        this.offset = 1;
        this.ratio = 1;

        this.exitPos = exitPos;
        this.exitSide = exitSide;

        for (Entity e : entities) {
            DrawableEntity drawable = e.isInteractive() ?
                    new DraggableEntity(e, ratio) :
                    new DrawableEntity(e, ratio);

            drawables.add(drawable);
            add(drawable);
        }
    }

    public void refresh() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ratio = Math.min(getWidth(), getHeight()) / (size + 2);
        offset = (getWidth() - ratio * (size + 2)) / 2;

        // Affiche les bords
        drawHSide(g, Direction.UP, "T", 0);
        drawVSide(g, Direction.LEFT, "L", 0);
        drawVSide(g, Direction.RIGHT, "R", size + 1);
        drawHSide(g, Direction.DOWN, "B", size + 1);

        // Affiche les cases normales
        for (int i = 1; i <= size; ++i) {
            for (int j = 1; j <= size; ++j) {
                drawSprite(g, "C", i, j);
            }
        }

        // TODO: refactor this shit
        // Supprime les drawables non utilisés
        for (int i = 0; i < drawables.size(); ) {
            DrawableEntity e = drawables.get(i);
            if (!e.getEntity().isAlive()) {
                remove(e);
                drawables.remove(e);
            } else {
                i++;
            }
        }

        // Met à jour les dimensions des entités
        for (DrawableEntity e : drawables) {
            e.setRatio(ratio);
            e.setOffset(offset + ratio);
        }
    }

    /**
     * Affiche un bord horizontal de la grille, affiche également les coins
     *
     * @param g               utilitaire d'affichage
     * @param side            coté à afficher (haut ou bas)
     * @param assetKey        clé pour l'affichage d'assets
     * @param fixedCoordinate valeur de la coordonnée fixe (ici y)
     */
    private void drawHSide(Graphics g, Direction side, String assetKey, int fixedCoordinate) {
        drawSprite(g, assetKey + "L", 0, fixedCoordinate);
        for (int i = 1; i <= size; ++i) {
            drawBorders(g, side, assetKey, i, fixedCoordinate, i);
        }
        drawSprite(g, assetKey + "R", (size + 1), fixedCoordinate);
    }

    /**
     * Affiche un bord vertical de la grille, n'affiche pas les coins
     *
     * @param g               utilitaire d'affichage
     * @param side            coté à afficher (gauche ou droite)
     * @param assetKey        clé pour l'affichage de sprites
     * @param fixedCoordinate valeur de la coordonnée fixe (ici x)
     */
    private void drawVSide(Graphics g, Direction side, String assetKey, int fixedCoordinate) {
        for (int i = 1; i <= size; ++i) {
            drawBorders(g, side, assetKey, fixedCoordinate, i, i);
        }
    }

    /**
     * Affiche un bord ou une sortie sur les coordonnées souhaitées
     *
     * @param g        utilitaire d'affichage
     * @param side     coté du bord
     * @param assetKey clé pour l'affichage de sprites
     * @param x        position x
     * @param y        position y
     * @param exit     position à vérifier pour placer une sortie
     */
    private void drawBorders(Graphics g, Direction side, String assetKey, int x, int y, int exit) {
        if (exitSide == side) {
            if (exit == exitPos) {
                drawSprite(g, assetKey + "H0", x, y);
            } else if (exit == exitPos + 1) {
                drawSprite(g, assetKey + "H1", x, y);
            } else if (exit == exitPos + 2) {
                drawSprite(g, assetKey + "H2", x, y);
            } else {
                drawSprite(g, assetKey, x, y);
            }
        } else {
            drawSprite(g, assetKey, x, y);
        }
    }

    /**
     * Affiche un sprite de case selon la clé
     *
     * @param g   utilitaire d'affichage
     * @param key clé du sprite
     * @param x   position x d'affichage
     * @param y   position y d'affichage
     */
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
