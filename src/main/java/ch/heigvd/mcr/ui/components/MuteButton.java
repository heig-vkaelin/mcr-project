package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;

import javax.swing.*;
import java.awt.*;

public class MuteButton extends JButton {
    public MuteButton(Color bgColor, Color txtColor) {
        super(new ImageIcon(AssetManager.sprites.get("icons").get("sound_on")));
        setBackground(bgColor);
        setForeground(txtColor);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addActionListener(e -> {
            if (AudioManager.getInstance().isMuted()) {
                AudioManager.getInstance().setMuted(false);
                setIcon(new ImageIcon(AssetManager.sprites.get("icons").get("sound_on")));
            } else {
                AudioManager.getInstance().setMuted(true);
                setIcon(new ImageIcon(AssetManager.sprites.get("icons").get("sound_off")));
            }
        });
    }
}
