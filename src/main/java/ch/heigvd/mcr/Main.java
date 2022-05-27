package ch.heigvd.mcr;

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
    }
}
