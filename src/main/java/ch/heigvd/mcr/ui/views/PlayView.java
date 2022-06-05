package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la vue de jeu dans un niveau spécifique
 *
 * @author Valentin Kaelin
 */
public class PlayView extends JPanel {

    private final JPanel mainPanel;
    private final JPanel gamePanel;
    private final JPanel btnsPanel;

    private final JButton btnUndo;
    private final JButton btnMenu;
    private final JButton btnRestart;
    private final JButton btnCheat;

    /**
     * Constructeur permettant de pour construire la vue
     *
     * @param level : le niveau à afficher
     */
    public PlayView(MainFrame parent, LevelState level) {
        // super(parent);

        // setTitle("DISIT - Niveau " + level.getId());

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        gamePanel = new JPanel();
        gamePanel.setBackground(Color.GRAY);

        btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.WHITE);
        btnsPanel.setLayout(new BoxLayout(btnsPanel, BoxLayout.X_AXIS));

        btnUndo = new JButton("Annuler");
        btnMenu = new JButton("Menu");

        btnRestart = new JButton("Recommencer");
        btnCheat = new JButton("Cheat");

        registerHandlers();

        btnsPanel.add(btnUndo);
        btnsPanel.add(btnMenu);
        btnsPanel.add(btnRestart);
        btnsPanel.add(btnCheat);
        mainPanel.add(gamePanel);
        mainPanel.add(btnsPanel);
    }

    private void registerHandlers() {
        btnMenu.addActionListener(e -> {
            // getParent().openMenuView();
        });

        btnCheat.addActionListener(e -> {
            AssetManager.audios.get("death").play();
        });

        // ... add more handlers
    }

}
