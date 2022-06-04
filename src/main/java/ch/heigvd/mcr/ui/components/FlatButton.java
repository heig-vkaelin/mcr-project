package ch.heigvd.mcr.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Flat looking button
 *
 * @author Nicolas Crausaz
 */
public class FlatButton extends JButton {

    public FlatButton(String text) {
        this(text, Color.WHITE, Color.BLACK);
    }

    public FlatButton(String text, Color bgColor) {
        this(text, bgColor, Color.BLACK);
    }

    public FlatButton(String text, Color bgColor, Color txtColor) {
        super(text);
        setBackground(bgColor);
        setForeground(txtColor);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
