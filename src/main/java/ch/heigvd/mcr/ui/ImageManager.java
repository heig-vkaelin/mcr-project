package ch.heigvd.mcr.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EventListener;


public enum ImageManager {
    TEST_IMAGE("images/logo.png"), TEST_IMAGE_2("images/logo.png"), TEST_IMAGE_3("images/logo.png"), TEST_IMAGE_4("images/logo.png"), TEST_IMAGE_5("images/logo.png"), TEST_IMAGE_6("images/logo.png");


    private final String path;
    private BufferedImage image;

    boolean loaded = false;

    ImageManager(String path) {
        this.path = path;
        this.image = null;
    }

    public Image getImage() {
        if (!loaded) throw new IllegalStateException("Image not loaded yet");
        return image;
    }

    private void load() {
        if (loaded) return;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource(path));
            loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface ImageManagerListener extends EventListener {
        void imageProgress(double progress, boolean finished);
    }

    static public void loadImages(ImageManagerListener listener) {
        new Thread(() -> {
            int total = ImageManager.values().length;
            int current = 0;
            for (ImageManager imageManager : ImageManager.values()) {
                { //random sleep to simulate loading
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                imageManager.load();
                current++;
                listener.imageProgress((double) current / total, current == total);
            }
        }).start();
    }
}