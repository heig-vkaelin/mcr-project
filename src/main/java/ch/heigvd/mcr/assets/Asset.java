package ch.heigvd.mcr.assets;

import ch.heigvd.mcr.assets.loaders.AssetLoader;
import ch.heigvd.mcr.assets.registers.Register;
import ch.heigvd.mcr.levels.LevelState;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * The AssetManager is responsible for loading and storing assets.
 *
 * @author Maxime Scharwath
 */
public class Asset<A> {
    private static final List<Asset<?>> ASSETS = new LinkedList<>();


    public static Asset<Image> IMAGE_ASSET = createAsset("image");
    public static Asset<Audio> AUDIO_ASSET = createAsset("audio");

    public static Asset<LevelState> LEVEL_ASSET = createAsset("level");

    private final HashMap<String, AssetLoader<A>> assets = new HashMap<>();
    private final String name;

    private Asset(String name) {
        this.name = name;
    }

    private static <A> Asset<A> createAsset(String name) {
        Asset<A> asset = new Asset<>(name);
        ASSETS.add(asset);
        return asset;
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
            for (var asset : assets) {
                asset.load();
                loaded++;
                listener.onProgress((double) loaded / (double) total, loaded == total);
            }
        }).start();
    }

    private static Collection<AssetLoader<?>> getAllLoaders() {
        Collection<AssetLoader<?>> loaders = new LinkedList<>();
        for (Asset<?> asset : ASSETS) {
            loaders.addAll(asset.assets.values());
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