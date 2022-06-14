package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.Event;
import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;
import ch.heigvd.mcr.commands.CheatCommand;
import ch.heigvd.mcr.commands.Command;
import ch.heigvd.mcr.commands.LoadLevelCommand;
import ch.heigvd.mcr.commands.UndoCommand;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.BoardPanel;
import ch.heigvd.mcr.ui.components.FlatButton;
import ch.heigvd.mcr.ui.components.MuteButton;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant la vue de jeu dans un niveau spécifique
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class PlayView extends AbstractView {
    private final JLabel nbMovesLabel;

    private final JButton btnUndo;
    private final JButton btnMenu;
    private final JButton btnRestart;
    private final JButton btnCheat;

    private final BoardPanel boardPanel;
    private final List<Event> events;

    /**
     * Constructeur permettant de pour construire la vue
     */
    public PlayView(MainFrame parent) {
        super(parent, new BorderLayout());


        setBackground(Color.WHITE);

        JPanel btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.WHITE);

        nbMovesLabel = new JLabel("Number of moves : 0");

        final Color RED = new Color(180, 32, 42);
        btnUndo = new FlatButton("Undo", RED, Color.WHITE);
        btnMenu = new FlatButton("Menu", RED, Color.WHITE);
        btnRestart = new FlatButton("Restart", RED, Color.WHITE);
        btnCheat = new FlatButton("Cheat", RED, Color.WHITE);
        JButton btnSound = new MuteButton(RED, Color.WHITE);

        events = new LinkedList<>();

        registerHandlers();

        LevelState l = GameController.getInstance().getState();
        boardPanel = new BoardPanel(l.getSideSize(), l.getEntities(), l.getExitPos(), l.getExitSide());
        boardPanel.setBackground(Color.GRAY);

        btnsPanel.add(btnUndo);
        btnsPanel.add(btnMenu);
        btnsPanel.add(btnRestart);
        btnsPanel.add(btnCheat);
        btnsPanel.add(btnSound);
        btnsPanel.add(nbMovesLabel);
        add(boardPanel, BorderLayout.CENTER);
        add(btnsPanel, BorderLayout.PAGE_END);
    }

    @Override
    public String getTitle() {
        return "DISIT - Level " + GameController.getInstance().getState().getId();
    }

    public void update() {
        updateNbMoves();
    }

    private void registerHandlers() {
        btnMenu.addActionListener(e -> getFrame().openMenuView());

        btnCheat.addActionListener(e -> GameController.getInstance().addCommand(new CheatCommand()));

        btnUndo.addActionListener(e -> GameController.getInstance().addCommand(new UndoCommand()));

        btnRestart.addActionListener(e -> new LoadLevelCommand(GameController.getInstance().getState().getId()).execute());

        // Écoute des événements
        events.add(GameController.getInstance().onCommand((Command c) -> update()));
    }

    public void onHide() {
        for (Event e : events)
            e.unsubscribe();
    }

    private void updateNbMoves() {
        nbMovesLabel.setText("Number of moves : " + GameController.getInstance().getState().getNbMoves());
    }

    public void refresh() {
        boardPanel.refresh();
    }
}
