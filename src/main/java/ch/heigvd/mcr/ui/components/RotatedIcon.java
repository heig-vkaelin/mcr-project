package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.entities.Direction;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 * The RotatedIcon allows you to change the orientation of an Icon by
 * rotating the Icon before it is painted. This class supports the following
 * orientations:
 * Source: https://tips4java.wordpress.com/2009/04/06/rotated-icon/
 *
 * @author Valentin Kaelin
 */
public class RotatedIcon implements Icon {
    private final Icon icon;

    private final Direction rotate;

    /**
     * Create a RotatedIcon
     *
     * @param icon   the Icon to rotate
     * @param rotate the direction of rotation
     */
    public RotatedIcon(Icon icon, Direction rotate) {
        this.icon = icon;
        this.rotate = rotate;
    }

    /**
     * Gets the width of this icon.
     *
     * @return the width of the icon in pixels.
     */
    @Override
    public int getIconWidth() {
        if (rotate == Direction.DOWN || rotate == Direction.UP)
            return icon.getIconWidth();
        else
            return icon.getIconHeight();
    }

    /**
     * Gets the height of this icon.
     *
     * @return the height of the icon in pixels.
     */
    @Override
    public int getIconHeight() {
        if (rotate == Direction.UP || rotate == Direction.DOWN)
            return icon.getIconHeight();
        else
            return icon.getIconWidth();
    }

    /**
     * Paint the icons of this compound icon at the specified location
     *
     * @param c The component on which the icon is painted
     * @param g the graphics context
     * @param x the X coordinate of the icon's top-left corner
     * @param y the Y coordinate of the icon's top-left corner
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();

        int cWidth = icon.getIconWidth() / 2;
        int cHeight = icon.getIconHeight() / 2;
        int xAdjustment = (icon.getIconWidth() % 2) == 0 ? 0 : -1;
        int yAdjustment = (icon.getIconHeight() % 2) == 0 ? 0 : -1;

        if (rotate == Direction.LEFT) {
            g2.translate(x + cHeight, y + cWidth);
            g2.rotate(Math.toRadians(90));
            icon.paintIcon(c, g2, -cWidth, yAdjustment - cHeight);
        } else if (rotate == Direction.RIGHT) {
            g2.translate(x + cHeight, y + cWidth);
            g2.rotate(Math.toRadians(-90));
            icon.paintIcon(c, g2, xAdjustment - cWidth, -cHeight);
        } else if (rotate == Direction.UP) {
            g2.translate(x + cWidth, y + cHeight);
            g2.rotate(Math.toRadians(180));
            icon.paintIcon(c, g2, xAdjustment - cWidth, yAdjustment - cHeight);
        } else if (rotate == Direction.DOWN) {
            icon.paintIcon(c, g2, x, y);
        }

        g2.dispose();
    }
}
