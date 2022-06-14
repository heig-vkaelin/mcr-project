package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.entities.Direction;

import javax.swing.*;
import java.awt.*;

/**
 * Wrapper autour de la classe Icon native de Swing afin de pouvoir les pivoter
 * facilement.
 * Code initial: https://tips4java.wordpress.com/2009/04/06/rotated-icon/
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class RotatedIcon implements Icon {
    private final Icon icon;
    private final Direction direction;

    /**
     * Crée une nouvelle icône pivotée.
     *
     * @param icon      : l'Icon Swing à pivoter
     * @param direction : la direction dans laquelle pivoter l'Icon
     */
    public RotatedIcon(Icon icon, Direction direction) {
        this.icon = icon;
        this.direction = direction;
    }

    /**
     * @return la largeur de l'icône en pixels
     */
    @Override
    public int getIconWidth() {
        if (direction == Direction.DOWN || direction == Direction.UP)
            return icon.getIconWidth();
        else
            return icon.getIconHeight();
    }

    /**
     * @return la hauteur de l'icône en pixels
     */
    @Override
    public int getIconHeight() {
        if (direction == Direction.UP || direction == Direction.DOWN)
            return icon.getIconHeight();
        else
            return icon.getIconWidth();
    }

    /**
     * Dessine l'icône à la position souhaitée
     *
     * @param component : le Component sur lequel dessiner
     * @param graphics  : le contexte graphique
     * @param x         : la coordonnée X du coin haut-gauche de l'icône
     * @param y         : la coordonnée Y du coin haut-gauche de l'icône
     */
    @Override
    public void paintIcon(Component component, Graphics graphics, int x, int y) {
        Graphics2D g2 = (Graphics2D) graphics.create();

        int cWidth = icon.getIconWidth() / 2;
        int cHeight = icon.getIconHeight() / 2;
        int xAdjustment = (icon.getIconWidth() % 2) == 0 ? 0 : -1;
        int yAdjustment = (icon.getIconHeight() % 2) == 0 ? 0 : -1;

        if (direction == Direction.LEFT) {
            g2.translate(x + cHeight, y + cWidth);
            g2.rotate(Math.toRadians(90));
            icon.paintIcon(component, g2, -cWidth, yAdjustment - cHeight);
        } else if (direction == Direction.RIGHT) {
            g2.translate(x + cHeight, y + cWidth);
            g2.rotate(Math.toRadians(-90));
            icon.paintIcon(component, g2, xAdjustment - cWidth, -cHeight);
        } else if (direction == Direction.UP) {
            g2.translate(x + cWidth, y + cHeight);
            g2.rotate(Math.toRadians(180));
            icon.paintIcon(component, g2, xAdjustment - cWidth, yAdjustment - cHeight);
        } else if (direction == Direction.DOWN) {
            icon.paintIcon(component, g2, x, y);
        }

        g2.dispose();
    }
}
