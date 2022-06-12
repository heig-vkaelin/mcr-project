package ch.heigvd.mcr.ui;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.views.HomeView;
import ch.heigvd.mcr.ui.views.MenuView;
import ch.heigvd.mcr.ui.views.PlayView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 560;

    private static MainFrame instance;

    private CardLayout currentView;

    private final JPanel mainPanel;

    private MainFrame() {
        setMinimumSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentView = new CardLayout();
        // Init with start panel
        mainPanel = new JPanel(currentView);

        mainPanel.add(new HomeView(this), "home");
        mainPanel.add(new MenuView(this), "menu");

        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public void showHomeView() {
        currentView.show(mainPanel, "home");
    }

    public void openMenuView() {
        currentView.show(mainPanel, "menu");
    }

    public void openLevelView() {
        mainPanel.add(new PlayView(this), "level");
        currentView.show(mainPanel, "level");
    }
}
