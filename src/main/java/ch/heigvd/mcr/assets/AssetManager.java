package ch.heigvd.mcr.assets;

import ch.heigvd.mcr.assets.loaders.AssetLoader;
import ch.heigvd.mcr.assets.registers.Register;
import ch.heigvd.mcr.levels.LevelState;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Classe chargeant et stockant les différents assets du jeu.
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class AssetManager<A> {
    private static final List<AssetManager<?>> ASSET_MANAGERS = new LinkedList<>();
    public static final AssetManager<Image> images = createAsset("image");
    public static final AssetManager<SpriteSheet> sprites = createAsset("sprite");
    public static final AssetManager<Audio> audios = createAsset("audio");
    public static final AssetManager<LevelState> levels = createAsset("level");

    private final HashMap<String, AssetLoader<A>> assets;
    private final String name;

    /**
     * Crée un nouveau manager d'assets
     *
     * @param name : nom du manager
     */
    private AssetManager(String name) {
        this.name = name;
        assets = new HashMap<>();
    }

    /**
     * Crée un nouveau manager d'assets et l'ajoute à la liste des managers
     *
     * @param name : nom du manager
     * @param <A>  : type des assets
     * @return : le manager créé
     */
    private static <A> AssetManager<A> createAsset(String name) {
        AssetManager<A> assetManager = new AssetManager<>(name);
        ASSET_MANAGERS.add(assetManager);
        return assetManager;
    }

    /**
     * Charge toutes les assets dans un thread séparé
     *
     * @param listener : listener à informer de l'avancement du chargement
     */
    public static void loadAll(ProgressListener listener) {
        new Thread(() -> {
            Collection<AssetLoader<?>> assets = getAllLoaders();
            int total = assets.size();
            int loaded = 0;
            for (AssetLoader<?> asset : assets) {
                asset.load();
                loaded++;
                listener.onProgress((double) loaded / (double) total, loaded == total);
            }
        }).start();
    }

    /**
     * @return : la liste des loaders d'assets
     */
    private static Collection<AssetLoader<?>> getAllLoaders() {
        Collection<AssetLoader<?>> loaders = new LinkedList<>();
        for (AssetManager<?> assetManager : ASSET_MANAGERS) {
            loaders.addAll(assetManager.assets.values());
        }
        return loaders;
    }

    /**
     * Enregistre un loader pour un asset
     *
     * @param key   : la clé à utiliser pour identifier l'asset
     * @param asset : l'asset à charger
     */
    public void register(String key, AssetLoader<A> asset) {
        assets.put(key, asset);
    }

    /**
     * Enregistre un register pour un type d'assets
     *
     * @param register : le register à utiliser
     */
    public void register(Register<A> register) {
        register.register(this);
    }

    /**
     * Récpère un asset selon sa clé
     *
     * @param key : la clé de l'assets à récupérer
     * @return l'asset souhaité
     * @throws IllegalArgumentException si la clé est invalide
     * @throws IllegalStateException    si l'asset n'a pas été chargé
     */
    public A get(String key) {
        if (!assets.containsKey(key)) {
            throw new IllegalArgumentException("No asset registered with key " + key);
        }
        if (!assets.get(key).isLoaded()) {
            throw new IllegalStateException("Asset " + key + " is not loaded");
        }
        return assets.get(key).get();
    }

    /**
     * Récupère toutes les assets enregistrées (et chargées) dans ce manager
     * ordonnées par clé
     *
     * @return la liste des assets
     */
    public Collection<A> getAll() {
        Collection<A> assets = new LinkedList<>();
        List<Map.Entry<String, AssetLoader<A>>> list = new ArrayList<>(this.assets.entrySet());
        list.sort(Map.Entry.comparingByKey());
        for (Map.Entry<String, AssetLoader<A>> entry : list) {
            if (entry.getValue().isLoaded()) {
                assets.add(entry.getValue().get());
            }
        }
        return assets;
    }

    /**
     * Listener pour l'avancement du chargement des assets
     */
    public interface ProgressListener extends EventListener {
        /**
         * Informe de l'avancement du chargement
         *
         * @param progress : l'avancement du chargement (0 à 1)
         * @param done     : si le chargement est terminé
         */
        void onProgress(double progress, boolean done);
    }
}
