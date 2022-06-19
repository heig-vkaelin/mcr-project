package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.commands.LoadLevelCommand;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.LevelButton;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

/**
 * Classe représentant la vue permettant de choisir quel niveau choisir
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class MenuView extends AbstractView {
    private static final int COLUMNS = 4;
    private static final int PADDING = 20;

    /**
     * Crée une nouvelle vue de menu de sélection de niveau
     *
     * @param parent : la fenêtre parente
     */
    public MenuView(MainFrame parent) {
        super(parent);

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
            cards.add(btn);
        }
        add(cards);
    }

    @Override
    public String getTitle() {
        return "DISIT - Select level";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(AssetManager.images.get("menu_background").getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
    }
}
