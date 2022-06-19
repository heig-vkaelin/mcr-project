package ch.heigvd.mcr.assets.loaders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Classe permettant de charger des images
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class ImageAssetLoader implements AssetLoader<Image> {
    private final URL url;
    private BufferedImage image;
    boolean loaded;

    /**
     * Crée un nouveau chargeur d'image
     *
     * @param path : chemin de l'image à charger
     */
    public ImageAssetLoader(String path) {
        url = ClassLoader.getSystemResource(path);
        loaded = false;
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
