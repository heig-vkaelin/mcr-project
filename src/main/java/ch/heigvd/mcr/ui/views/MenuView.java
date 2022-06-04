package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.components.LevelButton;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant la vue permettant de choisir quel niveau choisir
 *
 * @author Valentin Kaelin
 */
public class MenuView implements View {
    private static final int INITIAL_WIDTH = 640;
    private static final int INITIAL_HEIGHT = 480;
    private static final int COLUMNS = 4;
    private static final int PADDING = 20;

    public final JFrame frame;
    private final JPanel panel;
    private final JPanel cards;

    private final List<JButton> levelButtons;

    /**
     * Constructeur permettant de construire la vue
     */
    public MenuView() {
        frame = new JFrame();
        frame.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Menu - sélectionnez un niveau");

        panel = new JPanel();
        panel.setBackground(Color.WHITE);

        levelButtons = new LinkedList<>();
        cards = new JPanel(new GridLayout(0, COLUMNS, PADDING, PADDING));
        cards.setBackground(Color.white);
        for (LevelState level : AssetManager.levels.getAll()) {
            JButton btn = new LevelButton(
                    "Niveau",
                    level.getId(),
                    level.getDifficulty()
            );
            btn.addActionListener(e -> {
                System.out.println("Click on level " + level.getId());
                new PlayView(level).show();
                close();
            });
            levelButtons.add(btn);
            cards.add(btn);
        }

        panel.add(cards);
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
}
