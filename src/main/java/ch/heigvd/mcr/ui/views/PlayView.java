package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.Event;
import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.Audio;
import ch.heigvd.mcr.assets.AudioManager;
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
 * @author Valentin Kaelin
 * @author Nicolas Crausaz
 */
public class PlayView extends JPanel {
    private final JLabel nbMovesLabel;

    private final FlatButton btnUndo;
    private final FlatButton btnMenu;
    private final FlatButton btnRestart;
    private final FlatButton btnCheat;

    private final FlatButton btnSound;
    private final MainFrame parent;

    private final BoardPanel boardPanel;
    private List<Event> events;

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

        nbMovesLabel = new JLabel("Nombre de coups : 0");

        final Color RED = new Color(180, 32, 42);
        btnUndo = new FlatButton("Annuler", RED, Color.WHITE);
        btnMenu = new FlatButton("Menu", RED, Color.WHITE);
        btnRestart = new FlatButton("Recommencer", RED, Color.WHITE);
        btnCheat = new FlatButton("Cheat", RED, Color.WHITE);
        btnSound = new MuteButton();

        events = new LinkedList<>();

        registerHandlers();

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

    public void refresh() {
        boardPanel.refresh();
    }

    public void update() {
        updateNbMoves();
    }

    private void registerHandlers() {
        btnMenu.addActionListener(e -> parent.openMenuView());

        btnCheat.addActionListener(e -> AudioManager.getInstance().play(AssetManager.audios.get("death")));

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
        nbMovesLabel.setText("Nombre de coups : " + GameController.getInstance().getState().getNbMoves());
    }
}
