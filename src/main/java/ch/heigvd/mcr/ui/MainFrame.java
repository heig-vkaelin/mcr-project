package ch.heigvd.mcr.ui;

import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.views.HomeView;
import ch.heigvd.mcr.ui.views.MenuView;
import ch.heigvd.mcr.ui.views.PlayView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 480;

    private CardLayout currentView;

    private JPanel mainPanel;

    public MainFrame() {
        super("DISIT");
        setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        currentView = new CardLayout();
        // Init with start panel
        mainPanel = new JPanel(currentView);

        mainPanel.add(new HomeView(this), "home");
        mainPanel.add(new MenuView(this), "menu");


        add(mainPanel);

        // showHomeView();

        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    public void showHomeView() {
        System.out.println("home view called");
//        mainPanel.add(new HomeView(this));
//        getContentPane().revalidate();
//        getContentPane().repaint();


        currentView.show(mainPanel, "home");
        repaint();
    }

    public void openMenuView() {
        System.out.println("menu view called");
//        mainPanel.add(new MenuView(this));
//        getContentPane().revalidate();
//        getContentPane().repaint();

        currentView.show(mainPanel, "menu");

        repaint();
    }

    public void openLevelView(LevelState level) {


        System.out.println("play view called");
        mainPanel.add(new PlayView(this, level));
        getContentPane().revalidate();
        getContentPane().repaint();
    }
}
