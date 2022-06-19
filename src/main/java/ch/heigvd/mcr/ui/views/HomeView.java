package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.FlatButton;
import ch.heigvd.mcr.ui.components.MuteButton;

import javax.swing.*;
import java.awt.*;

/**
 * Vue de la page d'accueil
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class HomeView extends AbstractView {
    private final JLabel logoLabel;
    private final JButton play;
    private final JButton quit;
    private final JButton sound;

    /**
     * Crée la vue de la page d'accueil du jeu
     *
     * @param parent : la fenêtre parente
     */
    public HomeView(MainFrame parent) {
        super(parent, new BorderLayout());

        setBackground(Color.BLACK);

        logoLabel = new JLabel(new ImageIcon(AssetManager.images.get("logo")));
        JPanel btnPanel = new JPanel();

        play = new FlatButton("Play !", getBtnColor(), Color.WHITE);
        quit = new FlatButton("Quit", getBtnColor(), Color.WHITE);
        sound = new MuteButton(getBtnColor(), Color.WHITE);

        play.setPreferredSize(new Dimension(200, 40));
        quit.setPreferredSize(new Dimension(200, 40));
        sound.setPreferredSize(new Dimension(100, 40));

        btnPanel.setBackground(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));

        btnPanel.add(quit);
        btnPanel.add(play);
        btnPanel.add(sound);
        add(logoLabel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);

        registerHandlers();

        AudioManager.getInstance().loop(AssetManager.audios.get("menu"));
    }

    @Override
    public String getTitle() {
        return "DISIT";
    }

    /**
     * Enregistre les handlers sur les éléments de l'UI
     */
    private void registerHandlers() {
        play.addActionListener(e -> getFrame().openMenuView());

        quit.addActionListener(e -> System.exit(0));

        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AudioManager.getInstance().stop(AssetManager.audios.get("menu"));
                AudioManager.getInstance().loop(AssetManager.audios.get("sw"));
            }
        });
    }
}
