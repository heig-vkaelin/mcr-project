package ch.heigvd.mcr.ui;

import ch.heigvd.mcr.ui.views.HomeView;
import ch.heigvd.mcr.ui.views.MenuView;
import ch.heigvd.mcr.ui.views.PlayView;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la fenêtre principale du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class MainFrame extends JFrame {
    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 560;
    private static MainFrame instance;

    private final JPanel mainPanel;
    private final CardLayout currentView;
    private final MenuView menuView;
    private PlayView currentPlayView;

    /**
     * Crée la fenêtre principale du jeu
     */
    private MainFrame() {
        setMinimumSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentView = new CardLayout();
        mainPanel = new JPanel(currentView);

        HomeView homeView = new HomeView(this);
        menuView = new MenuView(this);
        mainPanel.add(homeView, "home");
        mainPanel.add(menuView, "menu");

        add(mainPanel);
        setTitle(homeView.getTitle());

        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    /**
     * @return l'instance unique de la fenêtre principale du jeu
     */
    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    /**
     * Ouvre le menu de sélection des niveaux
     */
    public void openMenuView() {
        currentView.show(mainPanel, "menu");
        setTitle(menuView.getTitle());
    }

    /**
     * Ouvre la vue du niveau souhaité
     */
    public void openLevelView() {
        if (currentPlayView != null) {
            currentPlayView.onHide();
        }
        currentPlayView = new PlayView(this);
        mainPanel.add(currentPlayView, "level");
        currentView.show(mainPanel, "level");
        setTitle(currentPlayView.getTitle());
    }

    /**
     * Met à jour la vue du niveau courant
     */
    public void refreshPlayView() {
        if (currentPlayView != null) {
            currentPlayView.refresh();
        }
    }
}
