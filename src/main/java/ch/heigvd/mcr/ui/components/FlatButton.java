package ch.heigvd.mcr.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Bouton dans le style flat design
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class FlatButton extends JButton {
    /**
     * Construit un bouton plat avec couleur de fond et couleur de texte
     *
     * @param text     : texte du bouton
     * @param bgColor  : couleur de fond
     * @param txtColor : couleur du texte
     */
    public FlatButton(String text, Color bgColor, Color txtColor) {
        super(text);
        setBackground(bgColor);
        setForeground(txtColor);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
