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
 * @version 2022-06-19
 */
public class ImageAssetLoader extends AssetLoader<Image> {
    /**
     * Crée un nouveau chargeur d'image
     *
     * @param path : chemin de l'image à charger
     */
    public ImageAssetLoader(String path) {
        super(path);
    }

    @Override
    protected Image loadAsset() throws IOException {
        return ImageIO.read(getUrl());
    }
}
