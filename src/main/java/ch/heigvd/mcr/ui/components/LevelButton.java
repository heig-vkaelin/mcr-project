package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.entities.Difficulty;

import javax.swing.*;
import java.awt.*;

/**
 * Bouton permettant de sélectionner un niveau.
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class LevelButton extends JButton {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 160;
    private static final Color RED = new Color(220, 38, 38);
    private static final Color YELLOW = new Color(250, 204, 21);
    private static final Color GREEN = new Color(34, 197, 94);
    private static final int CIRCLE_DIAMETER = 30;
    private static final int HEIGHT_RECTANGLE = 30;

    private final int number;
    private final Difficulty difficulty;

    /**
     * Crée un nouveau bouton de niveau
     *
     * @param label      : le texte du bouton
     * @param number     : le numéro du niveau
     * @param difficulty : la difficulté du niveau
     */
    public LevelButton(String label, int number, Difficulty difficulty) {
        super(label);
        this.number = number;
        this.difficulty = difficulty;
        setVerticalAlignment(SwingConstants.TOP);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.GRAY);
        setFocusPainted(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension originalSize = super.getSize();
        Dimension center = new Dimension(originalSize.width / 2, originalSize.height / 2);

        int xCircle = center.width - CIRCLE_DIAMETER / 2;
        int yCircle = center.height - CIRCLE_DIAMETER / 2;

        // Numéro du niveau
        FontMetrics fontMetrics = g.getFontMetrics();
        int widthLevel = fontMetrics.stringWidth(String.valueOf(number));
        int xLevel = center.width - widthLevel / 2;
        int yLevel = center.height - fontMetrics.getHeight() / 2 + fontMetrics.getAscent();

        // Difficulté
        int widthRectangle = originalSize.width;
        int yRectangle = originalSize.height - HEIGHT_RECTANGLE;
        String difficultyLabel = getDifficultyText(difficulty);
        int widthDifficulty = fontMetrics.stringWidth(String.valueOf(difficultyLabel));
        int xDifficulty = center.width - widthDifficulty / 2;
        int yDifficulty = originalSize.height - 10;

        // Dessine tout
        g.setColor(RED);
        g.fillOval(xCircle, yCircle, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(number), xLevel, yLevel);
        g.setColor(getDifficultyColor(difficulty));
        g.fillRect(0, yRectangle, widthRectangle, HEIGHT_RECTANGLE);
        g.setColor(Color.WHITE);
        g.drawString(difficultyLabel, xDifficulty, yDifficulty);
    }

    /**
     * Retourne la couleur correspondante à la difficulté
     *
     * @param difficulty : la difficulté
     * @return la couleur correspondante à la difficulté
     */
    private Color getDifficultyColor(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> GREEN;
            case MEDIUM -> YELLOW;
            case HARD -> RED;
        };
    }

    /**
     * Retourne le texte correspondant à la difficulté
     *
     * @param difficulty : la difficulté
     * @return le texte correspondant à la difficulté
     */
    private String getDifficultyText(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> "Easy";
            case MEDIUM -> "Medium";
            case HARD -> "Difficult";
        };
    }
}
