package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.ui.components.FlatButton;

import javax.swing.*;
import java.awt.*;

public class HomeView implements View {

    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 480;

    private final JFrame frame;

    private final JButton play;
    private final JButton quit;

    public HomeView() {
        frame = new JFrame();
        frame.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("DISIT");

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        JLabel picLabel = new JLabel(new ImageIcon(AssetManager.images.get("logo")));
        JPanel btnPanel = new JPanel();
        play = new FlatButton("Play !", new Color(180, 32, 42), Color.WHITE);
        quit = new FlatButton("Quit", new Color(180, 32, 42), Color.WHITE);

        play.setPreferredSize(new Dimension(200, 40));
        quit.setPreferredSize(new Dimension(200, 40));

        btnPanel.setBackground(Color.BLACK);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));

        btnPanel.add(quit);
        btnPanel.add(play);
        panel.add(picLabel, BorderLayout.NORTH);
        panel.add(btnPanel, BorderLayout.SOUTH);

        registerHandlers();

        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
        frame.pack();
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

    private void registerHandlers() {
        play.addActionListener(e -> new MenuView().show());

        quit.addActionListener(e -> System.exit(0));
    }
}
