package ch.heigvd.mcr;

import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.levels.LevelParser;
import ch.heigvd.mcr.levels.LevelState;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // FOR TESTING PURPOSES ONLY !!! REMOVE THIS WHEN MERGING
        JFrame frame = new JFrame("MCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel label = new JLabel(new ImageIcon(ClassLoader.getSystemResource("images/logo.png")));
        frame.add(label);

        LevelState state = LevelParser.parseLevelFile("1.txt");

        System.out.println(state.getDifficulty());
        System.out.println(state.getSideSize());
        for (Entity e: state.getEntities()) {
            System.out.println(e);
        }
    }
}
