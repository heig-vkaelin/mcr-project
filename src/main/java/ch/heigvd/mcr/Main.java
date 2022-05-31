package ch.heigvd.mcr;

import ch.heigvd.mcr.ui.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Main {
    public static void main(String[] args) {
        // FOR TESTING PURPOSES ONLY !!! REMOVE THIS WHEN MERGING
        JFrame frame = new JFrame("MCR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        //create progress bar
        JProgressBar progressBar = new JProgressBar();
        frame.add(progressBar);
        ImageManager.loadImages((progress, finished) -> {
            progressBar.setValue((int) (progress * 100));
            if (finished) {
                frame.remove(progressBar);
                JLabel image = new JLabel(new ImageIcon(ImageManager.TEST_IMAGE.getImage()));
                //drag image
                image.addMouseMotionListener(
                        new MouseMotionListener() {
                            @Override
                            public void mouseDragged(MouseEvent e) {
                                image.setLocation(
                                        image.getX() + e.getX() - image.getWidth() / 2,
                                        image.getY() + e.getY() - image.getHeight() / 2);
                            }

                            @Override
                            public void mouseMoved(MouseEvent e) {

                            }
                        }
                );
                frame.add(image);
                frame.revalidate();
            }
        });
    }
}
