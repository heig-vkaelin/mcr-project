package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
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
public class MenuView extends JPanel {
    private static final int COLUMNS = 4;
    private static final int PADDING = 20;

    private final JPanel cards;

    private final List<JButton> levelButtons;

    /**
     * Constructeur permettant de construire la vue
     */
    public MenuView(MainFrame parent) {
        // super(parent);

        // setTitle("DISIT - Select level");

        setBackground(Color.WHITE);

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
                // getParent().openLevelView(level);
                // close();
            });
            levelButtons.add(btn);
            cards.add(btn);
        }

        add(cards);
    }
}
