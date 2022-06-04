package ch.heigvd.mcr.assets.loaders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * A class that loads images from the file system from resources.
 *
 * @author Maxime Scharwath
 */
public class ImageAssetLoader implements AssetLoader<Image> {
    private final URL url;
    boolean loaded = false;
    private BufferedImage image;

    public ImageAssetLoader(String path) {
        url = ClassLoader.getSystemResource(path);
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        if (loaded) return;
        try {
            image = ImageIO.read(url);
            loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image get() {
        return image;
    }
}
