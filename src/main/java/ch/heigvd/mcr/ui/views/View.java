package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nicolas Crausaz
 */
public abstract class View extends JPanel {

    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 480;

    private final MainFrame parent;

    protected View(MainFrame parent) {
        this(parent, new BorderLayout());
    }

    protected View(MainFrame parent, LayoutManager layout) {
        super(layout);
        this.parent = parent;
        // setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        revalidate();
    }

    public void setTitle(String title) {
        parent.setTitle(title);
    }

    public MainFrame getParent() {
        return parent;
    }
}
