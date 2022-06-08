package ch.heigvd.mcr.ui.views;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.levels.LevelState;
import ch.heigvd.mcr.ui.MainFrame;
import ch.heigvd.mcr.ui.components.FlatButton;
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

    private final MainFrame parent;


    /**
     * Constructeur permettant de construire la vue
     */
    public MenuView(MainFrame parent) {
        this.parent = parent;

        parent.setTitle("DISIT - Select level");

        levelButtons = new LinkedList<>();
        cards = new JPanel(new GridLayout(0, COLUMNS, PADDING, PADDING));

        cards.setOpaque(false);
        for (LevelState level : AssetManager.levels.getAll()) {
            JButton btn = new LevelButton(
                    "Niveau",
                    level.getId(),
                    level.getDifficulty()
            );
            btn.addActionListener(e -> {
                parent.openLevelView(level);
            });
            levelButtons.add(btn);
            cards.add(btn);
        }

//        FlatButton goBack = new FlatButton("Home", new Color(180, 32, 42), Color.WHITE);
//        JPanel btnPanel = new JPanel();
//        btnPanel.add(goBack);

        add(cards);
//        add(btnPanel, BorderLayout.PAGE_END);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(AssetManager.images.get("menu_background").getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
    }
}
