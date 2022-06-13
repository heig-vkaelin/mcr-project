package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.Audio;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.FlatButton;

import javax.swing.*;
import java.awt.*;

/**
 * Vue de la page d'accueil
 *
 * @author Nicolas Crausaz
 */
public class HomeView extends JPanel {

    private final FlatButton play;
    private final FlatButton quit;

    private final FlatButton sound;

    private final MainFrame parent;

    public HomeView(MainFrame parent) {
        super(new BorderLayout());
        this.parent = parent;
        parent.setTitle("DISIT");

        setBackground(Color.BLACK);

        JLabel picLabel = new JLabel(new ImageIcon(AssetManager.images.get("logo")));
        JPanel btnPanel = new JPanel();

        play = new FlatButton("Play !", new Color(180, 32, 42), Color.WHITE);
        quit = new FlatButton("Quit", new Color(180, 32, 42), Color.WHITE);
        sound = new FlatButton("Sound ON", new Color(180, 32, 42), Color.WHITE);

        play.setPreferredSize(new Dimension(200, 40));
        quit.setPreferredSize(new Dimension(200, 40));
        sound.setPreferredSize(new Dimension(100, 40));

        btnPanel.setBackground(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));

        btnPanel.add(quit);
        btnPanel.add(play);
        btnPanel.add(sound);
        add(picLabel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);

        registerHandlers();
    }

    private void registerHandlers() {
        play.addActionListener(e -> parent.openMenuView());

        quit.addActionListener(e -> System.exit(0));

        sound.addActionListener(e -> {
            Audio a = AssetManager.audios.get("menu");
            if (a.isPlaying()) {
                a.stop();
                sound.setText("Sound OFF");
            } else {
                a.play();
                sound.setText("Sound ON");
            }
        });
    }
}
