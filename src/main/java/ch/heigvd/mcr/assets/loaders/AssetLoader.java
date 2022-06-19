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
     * @return true si l'asset a été chargé, false sinon
     */
    boolean isLoaded();

    /**
     * Charge l'asset
     */
    void load();

    /**
     * @return l'asset chargé
     */
    T get();
}
