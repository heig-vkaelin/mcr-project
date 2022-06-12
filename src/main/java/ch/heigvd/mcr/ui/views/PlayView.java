package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.commands.LoadLevelCommand;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.BoardPanel;
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

    private final JButton btnUndo;
    private final JButton btnMenu;
    private final JButton btnRestart;
    private final JButton btnCheat;
    private final MainFrame parent;

    /**
     * Constructeur permettant de pour construire la vue
     */
    public PlayView(MainFrame parent) {
        super(new BorderLayout());
        this.parent = parent;

        System.out.println("new PlayView !");

        LevelState l = GameController.getInstance().getState();
        parent.setTitle("DISIT - Niveau " + l.getId());

        setBackground(Color.WHITE);

        JPanel btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.WHITE);

        btnUndo = new FlatButton("Annuler", new Color(180, 32, 42), Color.WHITE);
        btnMenu = new FlatButton("Menu", new Color(180, 32, 42), Color.WHITE);

        btnRestart = new FlatButton("Recommencer", new Color(180, 32, 42), Color.WHITE);
        btnCheat = new FlatButton("Cheat", new Color(180, 32, 42), Color.WHITE);

        registerHandlers();

        BoardPanel boardPanel = new BoardPanel(l.getSideSize(), l.getEntities(), l.getExitPos(), l.getExitSide());
        boardPanel.setBackground(Color.GRAY);

        btnsPanel.add(btnUndo);
        btnsPanel.add(btnMenu);
        btnsPanel.add(btnRestart);
        btnsPanel.add(btnCheat);
        add(boardPanel, BorderLayout.CENTER);
        add(btnsPanel, BorderLayout.PAGE_END);
    }

    private void registerHandlers() {
        btnMenu.addActionListener(e -> parent.openMenuView());

        btnCheat.addActionListener(e -> {
            AssetManager.audios.get("death").play();
        });


        btnUndo.addActionListener(e -> {
        });

        btnRestart.addActionListener(e -> {
            new LoadLevelCommand(GameController.getInstance().getState().getId()).execute();
        });
    }
}
