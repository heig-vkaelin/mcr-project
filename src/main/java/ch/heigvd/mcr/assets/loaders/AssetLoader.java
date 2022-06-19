package ch.heigvd.mcr.assets.loaders;

import java.io.IOException;
import java.net.URL;

/**
 * Classe abstraite permettant de charger les différents types d'assets
 *
 * @param <T> : le type de l'asset à charger
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public abstract class AssetLoader<T> {
    private final URL url;
    private boolean loaded;
    private T asset;

    /**
     * Crée un nouveau chargeur pour un type de fichier donné
     *
     * @param path : chemin du fichier à charger
     */
    public AssetLoader(String path) {
        url = ClassLoader.getSystemResource(path);
        loaded = false;
    }

    /**
     * @return true si l'asset a été chargé, false sinon
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Charge l'asset
     */
    public void load() {
        if (loaded) return;
        try {
            asset = loadAsset();
            loaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return l'asset chargé
     */
    public T get() {
        return asset;
    }

    /**
     * @return l'URL de l'asset
     */
    protected URL getUrl() {
        return url;
    }

    /**
     * Charge l'asset à partir de l'URL
     *
     * @return l'asset chargé
     * @throws IOException si une erreur survient lors du chargement
     */
    protected abstract T loadAsset() throws IOException;
}
