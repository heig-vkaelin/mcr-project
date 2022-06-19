package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Vue abstraite représentant toutes les vues du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public abstract class AbstractView extends JPanel {
    private final MainFrame parent;
    private final static Color BTN_COLOR = new Color(180, 32, 42);

    /**
     * Crée une nouvelle vue
     *
     * @param parent : la fenêtre parente
     * @param layout : le layout de la vue
     */
    public AbstractView(MainFrame parent, java.awt.LayoutManager layout) {
        super(layout);
        this.parent = parent;
    }

    /**
     * Crée une nouvelle vue avec comme layout le FlowLayout
     *
     * @param parent : la fenêtre parente
     */
    public AbstractView(MainFrame parent) {
        this(parent, new FlowLayout());
    }

    /**
     * @return le titre de la vue
     */
    public abstract String getTitle();

    /**
     * @return la fenêtre parente
     */
    protected MainFrame getFrame() {
        return parent;
    }

    /**
     * @return la couleur utilisée pour les boutons sur les différentes vues
     */
    protected static Color getBtnColor() {
        return BTN_COLOR;
    }
}
