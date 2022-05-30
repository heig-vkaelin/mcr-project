package ch.heigvd.mcr;

import ch.heigvd.mcr.ui.ImageManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // FOR TESTING PURPOSES ONLY !!! REMOVE THIS WHEN MERGING
        JFrame frame = new JFrame("MCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel labelProgress = new JLabel("Loading images...");
        frame.add(labelProgress);
        ImageManager.loadImages((progress, finished) -> {
            labelProgress.setText("Loading images... " + (int) (progress * 100) + "%");
            if (finished) {
                labelProgress.setText("Loading images... 100%");
                frame.remove(labelProgress);
                frame.add(new JLabel(new ImageIcon(ImageManager.TEST_IMAGE.getImage())));
                frame.revalidate();
            }
        });
    }
}
