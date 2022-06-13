package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.commands.LoadLevelCommand;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.LevelButton;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
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

    private final MainFrame parent;


    /**
     * Constructeur permettant de construire la vue
     */
    public MenuView(MainFrame parent) {
        this.parent = parent;

        parent.setTitle("DISIT - Select level");

        List<JButton> levelButtons = new LinkedList<>();
        JPanel cards = new JPanel(new GridLayout(0, COLUMNS, PADDING, PADDING));

        cards.setOpaque(false);

        List<LevelState> levels = AssetManager.levels.getAll().stream()
                .sorted(Comparator.comparingInt(LevelState::getId))
                .toList();

        for (LevelState level : levels) {
            JButton btn = new LevelButton(
                    "Niveau",
                    level.getId(),
                    level.getDifficulty()
            );
            btn.addActionListener(e -> new LoadLevelCommand(level.getId()).execute());
            levelButtons.add(btn);
            cards.add(btn);
        }
        add(cards);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(AssetManager.images.get("menu_background").getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
    }
}
