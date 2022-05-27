package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.levels.Difficulty;
import ch.heigvd.mcr.ui.components.LevelButton;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Singleton représentant la vue permettant de choisir quel niveau choisir
 *
 * @author Valentin Kaelin
 */
public class MenuView implements View {
    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 480;
    private static final int COLUMNS = 4;
    private static final int PADDING = 20;
    private static MenuView instance;

    public final JFrame frame;
    private final JPanel panel;
    private final JPanel cards;

    private final List<JButton> levelButtons;

    /**
     * Constructeur privé pour construire la vue
     */
    private MenuView() {
        frame = new JFrame();
        frame.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Menu - sélectionnez un niveau");

        panel = new JPanel();
        panel.setBackground(Color.WHITE);

        // TODO: load levels from config
        levelButtons = new LinkedList<>();
        cards = new JPanel(new GridLayout(0, COLUMNS, PADDING, PADDING));
        cards.setBackground(Color.white);
        Difficulty[] difficulties = new Difficulty[]{
                Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD
        };
        for (int id = 0; id < 10; id++) {
            JButton btn = new LevelButton(
                    "Niveau", id, difficulties[(int) (Math.random() * difficulties.length)]);
            int currentId = id;
            // TODO: redirect to PlayView on click -> Command?
            btn.addActionListener(e -> {
                System.out.println("Click on level " + currentId);
            });
            levelButtons.add(btn);
            cards.add(btn);
        }

        panel.add(cards);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @return l'instance de la vue
     */
    public static MenuView getInstance() {
        if (instance == null)
            instance = new MenuView();
        return instance;
    }

    @Override
    public void repaint() {

    }
}
