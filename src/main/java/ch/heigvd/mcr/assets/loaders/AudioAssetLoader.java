package ch.heigvd.mcr.assets.loaders;

import ch.heigvd.mcr.assets.Audio;

import java.io.IOException;
import java.net.URL;

/**
 * Classe permettant de charger des sons
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class AudioAssetLoader extends AssetLoader<Audio> {
    /**
     * Crée un nouveau chargeur d'un fichier audio
     *
     * @param path : chemin du fichier audio à charger
     */
    public AudioAssetLoader(String path) {
        super(path);
    }

    @Override
    protected Audio loadAsset() throws IOException {
        return new Audio(getUrl());
    }
}
