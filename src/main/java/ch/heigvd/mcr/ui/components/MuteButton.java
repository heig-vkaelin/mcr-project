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
    /**
     * Crée un bouton permettant d'activer / désactiver le son du jeu
     *
     * @param bgColor  : couleur de fond
     * @param txtColor : couleur du texte
     */
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

    /**
     * Met à jour l'icone du bouton
     */
    private void refreshIcon() {
        String name = AudioManager.getInstance().isMuted() ? "sound_off" : "sound_on";
        setIcon(new ImageIcon(AssetManager.sprites.get("icons").get(name)));
    }
}
