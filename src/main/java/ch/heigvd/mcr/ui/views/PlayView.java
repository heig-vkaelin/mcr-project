package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.Audio;
import ch.heigvd.mcr.assets.AudioManager;
import ch.heigvd.mcr.commands.LoadLevelCommand;
import ch.heigvd.mcr.commands.UndoCommand;
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

    private final FlatButton btnUndo;
    private final FlatButton btnMenu;
    private final FlatButton btnRestart;
    private final FlatButton btnCheat;

    private final FlatButton btnSound;
    private final MainFrame parent;

    private final BoardPanel boardPanel;

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
        btnSound = new FlatButton("Sound ON", new Color(180, 32, 42), Color.WHITE);

        registerHandlers();

        boardPanel = new BoardPanel(l.getSideSize(), l.getEntities(), l.getExitPos(), l.getExitSide());
        boardPanel.setBackground(Color.GRAY);

        btnsPanel.add(btnUndo);
        btnsPanel.add(btnMenu);
        btnsPanel.add(btnRestart);
        btnsPanel.add(btnCheat);
        btnsPanel.add(btnSound);
        add(boardPanel, BorderLayout.CENTER);
        add(btnsPanel, BorderLayout.PAGE_END);
    }

    public void refresh() {
        boardPanel.refresh();
    }

    private void registerHandlers() {
        btnMenu.addActionListener(e -> parent.openMenuView());

        btnCheat.addActionListener(e -> AudioManager.getInstance().play(AssetManager.audios.get("death")));

        btnUndo.addActionListener(e -> new UndoCommand().execute());

        btnRestart.addActionListener(e -> new LoadLevelCommand(GameController.getInstance().getState().getId()).execute());

        // Todo: create a button class with an sound icon
        btnSound.addActionListener(e -> {
            if (AudioManager.getInstance().isMuted()) {
                AudioManager.getInstance().setMuted(false);
                btnSound.setText("Sound ON");
            } else {
                AudioManager.getInstance().setMuted(true);
                btnSound.setText("Sound OFF");
            }
        });
    }
}
