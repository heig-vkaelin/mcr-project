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
 */
public abstract class AbstractView extends JPanel {
    private final MainFrame parent;

    public AbstractView(MainFrame parent, java.awt.LayoutManager layout) {
        super(layout);
        this.parent = parent;
    }

    public AbstractView(MainFrame parent) {
        this(parent, new FlowLayout());
    }

    protected MainFrame getFrame() {
        return parent;
    }

    public abstract String getTitle();
}
