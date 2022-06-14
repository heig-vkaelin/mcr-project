package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.assets.AudioManager;

import java.awt.*;

public class MuteButton extends FlatButton {
    // TODO: add a sound icon
    public MuteButton() {
        super(
                "Sound " + (AudioManager.getInstance().isMuted() ? "OFF" : "ON"),
                new Color(180, 32, 42),
                Color.WHITE
        );

        addActionListener(e -> {
            if (AudioManager.getInstance().isMuted()) {
                AudioManager.getInstance().setMuted(false);
                setText("Sound ON");
            } else {
                AudioManager.getInstance().setMuted(true);
                setText("Sound OFF");
            }
        });
    }
}
