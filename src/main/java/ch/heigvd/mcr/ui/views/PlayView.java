package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.BoardPanel;
import ch.heigvd.mcr.ui.components.DraggableEntity;
import ch.heigvd.mcr.ui.components.FlatButton;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la vue de jeu dans un niveau spécifique
 *
 * @author Valentin Kaelin
 * @author Nicolas Crausaz
 */
public class PlayView extends JPanel {

    private final BoardPanel boardPanel;
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

        btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.WHITE);

        btnUndo = new FlatButton("Annuler", new Color(180, 32, 42), Color.WHITE);
        btnMenu = new FlatButton("Menu", new Color(180, 32, 42), Color.WHITE);

        btnRestart = new FlatButton("Recommencer", new Color(180, 32, 42), Color.WHITE);
        btnCheat = new FlatButton("Cheat", new Color(180, 32, 42), Color.WHITE);

        registerHandlers();

        boardPanel = new BoardPanel(level.getSideSize(), level.getEntities());
        boardPanel.setBackground(Color.GRAY);

        btnsPanel.add(btnUndo);
        btnsPanel.add(btnMenu);
        btnsPanel.add(btnRestart);
        btnsPanel.add(btnCheat);
        add(boardPanel, BorderLayout.CENTER);
        add(btnsPanel, BorderLayout.PAGE_END);
    }

    private void registerHandlers() {
        btnMenu.addActionListener(e -> {
            parent.openMenuView();
        });

        btnCheat.addActionListener(e -> {
            AssetManager.audios.get("death").play();
        });

        btnUndo.addActionListener(e -> {
        });

        btnRestart.addActionListener(e -> {
        });
    }
}
