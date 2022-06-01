package ch.heigvd.mcr.ui.views;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la vue de jeu dans un niveau spécifique
 *
 * @author Valentin Kaelin
 */
public class PlayView implements View {
    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 480;
    
    public final JFrame frame;
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
    public PlayView(int level) {
        // TODO: passer la structure complète du level et pas que son numéro une fois
        // la classe terminée (PR #7)
        
        frame = new JFrame();
        frame.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("DISIT - Niveau " + level);
        
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
        btnMenu.addActionListener(e -> {
            new MenuView().show();
            close();
        });
        btnRestart = new JButton("Recommencer");
        btnCheat = new JButton("Cheat");
        
        btnsPanel.add(btnUndo);
        btnsPanel.add(btnMenu);
        btnsPanel.add(btnRestart);
        btnsPanel.add(btnCheat);
        
        mainPanel.add(gamePanel);
        mainPanel.add(btnsPanel);
        
        frame.setContentPane(mainPanel);
//        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void repaint() {
    
    }
    
    @Override
    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }
    
    @Override
    public void show() {
        frame.setVisible(true);
    }
}
