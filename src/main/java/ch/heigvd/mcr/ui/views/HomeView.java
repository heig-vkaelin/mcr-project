package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.FlatButton;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {

    private final JButton play;
    private final JButton quit;

    public HomeView(MainFrame parent) {
        // super(parent, new BorderLayout());
        super(new BorderLayout());

        setBackground(Color.BLACK);
        JLabel picLabel = new JLabel(new ImageIcon(AssetManager.images.get("logo")));
        JPanel btnPanel = new JPanel();

        play = new FlatButton("Play !", new Color(180, 32, 42), Color.WHITE);
        quit = new FlatButton("Quit", new Color(180, 32, 42), Color.WHITE);

        play.setPreferredSize(new Dimension(200, 40));
        quit.setPreferredSize(new Dimension(200, 40));

        btnPanel.setBackground(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));

        btnPanel.add(quit);
        btnPanel.add(play);
        add(picLabel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);

        registerHandlers();
    }

    private void registerHandlers() {
        play.addActionListener(e -> { // getParent().openMenuView();
            System.out.println("click"); });

        quit.addActionListener(e -> System.exit(0));
    }
}
