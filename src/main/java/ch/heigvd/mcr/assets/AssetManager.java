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
 */
public class AssetManager<A> {
    private static final List<AssetManager<?>> ASSET_MANAGERS = new LinkedList<>();
    public static AssetManager<Image> images = createAsset("image");
    public static AssetManager<SpriteSheet> sprites = createAsset("sprite");
    public static AssetManager<Audio> audios = createAsset("audio");
    public static AssetManager<LevelState> levels = createAsset("level");

    private final HashMap<String, AssetLoader<A>> assets;
    private final String name;

    private AssetManager(String name) {
        this.name = name;
        assets = new HashMap<>();
    }

    private static <A> AssetManager<A> createAsset(String name) {
        AssetManager<A> assetManager = new AssetManager<>(name);
        ASSET_MANAGERS.add(assetManager);
        return assetManager;
    }

    /**
     * Load all assets.
     *
     * @param listener The listener to notify when the assets are loaded.
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

    private static Collection<AssetLoader<?>> getAllLoaders() {
        Collection<AssetLoader<?>> loaders = new LinkedList<>();
        for (AssetManager<?> assetManager : ASSET_MANAGERS) {
            loaders.addAll(assetManager.assets.values());
        }
        return loaders;
    }

    /**
     * Register an asset loader.
     *
     * @param key   The key to register the loader with.
     * @param asset The asset to load.
     */
    public void register(String key, AssetLoader<A> asset) {
        System.out.println("Registering in " + name + ": " + key);
        assets.put(key, asset);
    }

    /**
     * Register an asset with Register interface.
     *
     * @param register The register to register the asset with.
     */
    public void register(Register<A> register) {
        register.register(this);
    }

    /**
     * Get the asset with the given key.
     *
     * @param key The key of the asset to get.
     * @return The asset with the given key.
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
     * Get all the assets registered in this asset manager.
     * Order by key.
     * Get only the loaded assets.
     *
     * @return A list of assets.
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
     * Progress listener for the AssetManager.
     */
    public interface ProgressListener extends EventListener {
        void onProgress(double progress, boolean done);
    }
}
