package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.AudioManager;

import javax.swing.*;
import java.awt.*;

/**
 * Bouton permettant d'activer / désactiver le son du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class MuteButton extends JButton {
    public MuteButton(Color bgColor, Color txtColor) {
        super("");
        refreshIcon();
        setBackground(bgColor);
        setForeground(txtColor);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addActionListener(e -> {
            AudioManager.getInstance().setMuted(!AudioManager.getInstance().isMuted());
            refreshIcon();
        });
    }

    private void refreshIcon() {
        if (AudioManager.getInstance().isMuted()) {
            setIcon(new ImageIcon(AssetManager.sprites.get("icons").get("sound_off")));
        } else {
            setIcon(new ImageIcon(AssetManager.sprites.get("icons").get("sound_on")));
        }
    }
}
