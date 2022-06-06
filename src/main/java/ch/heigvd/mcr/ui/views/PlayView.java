package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.FlatButton;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la vue de jeu dans un niveau spécifique
 *
 * @author Valentin Kaelin
 */
public class PlayView extends JPanel {

    // private final JPanel mainPanel;
    private final JPanel gamePanel;
    private final JPanel btnsPanel;

    private final JButton btnUndo;
    private final JButton btnMenu;
    private final JButton btnRestart;
    private final JButton btnCheat;
    private final MainFrame parent;

    /**
     * Constructeur permettant de pour construire la vue
     *
     * @param level : le niveau à afficher
     */
    public PlayView(MainFrame parent, LevelState level) {
        super(new BorderLayout());
        this.parent = parent;

        parent.setTitle("DISIT - Niveau " + level.getId());

        setBackground(Color.WHITE);
        // setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        gamePanel = new JPanel();
        gamePanel.setBackground(Color.GRAY);

        btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.WHITE);

        btnUndo = new FlatButton("Annuler", new Color(180, 32, 42), Color.WHITE);
        btnMenu = new FlatButton("Menu", new Color(180, 32, 42), Color.WHITE);

        btnRestart = new FlatButton("Recommencer", new Color(180, 32, 42), Color.WHITE);
        btnCheat = new FlatButton("Cheat", new Color(180, 32, 42), Color.WHITE);

        buildGrid();

        registerHandlers();

        btnsPanel.add(btnUndo);
        btnsPanel.add(btnMenu);
        btnsPanel.add(btnRestart);
        btnsPanel.add(btnCheat);
        add(gamePanel, BorderLayout.CENTER);
        add(btnsPanel, BorderLayout.PAGE_END);
    }

    private void buildGrid() {
        gamePanel.add(new JLabel(new ImageIcon(AssetManager.sprites.get("board").get("C"))));
    }

    private void registerHandlers() {
        btnMenu.addActionListener(e -> {
            parent.openMenuView();
        });

        btnCheat.addActionListener(e -> {
            AssetManager.audios.get("death").play();
        });

        // ... add more handlers
    }

}
