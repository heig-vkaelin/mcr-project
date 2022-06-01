package ch.heigvd.mcr.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EventListener;
import java.util.Random;


public enum ImageManager {
    TEST_IMAGE("images/logo.png"),
    TEST_IMAGE_2("images/logo.png"),
    TEST_IMAGE_3("images/logo.png"),
    TEST_IMAGE_4("images/logo.png"),
    TEST_IMAGE_5("images/logo.png"),
    TEST_IMAGE_6("images/logo.png");


    private final String path;
    boolean loaded = false;
    private BufferedImage image;

    ImageManager(String path) {
        this.path = path;
        this.image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    static public void loadImages(ImageManagerListener listener) {
        new Thread(() -> {
            double total = ImageManager.values().length;
            double current = 0;
            Random random = new Random();
            for (ImageManager imageManager : ImageManager.values()) {
                { //random sleep to simulate loading
                    try {
                        Thread.sleep(random.nextInt(100));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                imageManager.load();
                current++;
                listener.imageProgress(current / total, current == total);
            }
        }).start();
    }

    public Image getImage() {
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
}