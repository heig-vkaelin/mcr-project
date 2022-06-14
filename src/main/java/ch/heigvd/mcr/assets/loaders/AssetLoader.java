package ch.heigvd.mcr.assets.loaders;

/**
 * Interface commune à tous les loaders d'assets
 *
 * @param <T> : le type de l'asset à charger
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public interface AssetLoader<T> {
    /**
     * Returns whether the asset has been loaded.
     *
     * @return true if the asset has been loaded, false otherwise.
     */
    boolean isLoaded();

    /**
     * Loads the asset.
     */
    void load();

    /**
     * Returns the loaded asset.
     *
     * @return The loaded asset.
     */
    T get();
}
