package ch.heigvd.mcr.assets.loaders;

/**
 * Interface for asset loaders.
 *
 * @param <T> The type of asset to load.
 * @author Maxime Scharwath
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
