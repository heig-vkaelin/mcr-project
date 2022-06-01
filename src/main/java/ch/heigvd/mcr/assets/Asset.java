package ch.heigvd.mcr.assets;

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


    public static Asset<Image> IMAGE_ASSET = createAsset();
    public static Asset<Audio> AUDIO_ASSET = createAsset();

    public static Asset<LevelState> LEVEL_ASSET = createAsset();

    private final HashMap<String, AssetLoader<A>> assets = new HashMap<>();

    private static <A> Asset<A> createAsset() {
        Asset<A> asset = new Asset<>();
        ASSETS.add(asset);
        return asset;
    }

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

    public void register(String key, AssetLoader<A> asset) {
        assets.put(key, asset);
    }

    public A get(String key) {
        if (!assets.containsKey(key)) {
            throw new IllegalArgumentException("No asset registered with key " + key);
        }
        if (!assets.get(key).isLoaded()) {
            throw new IllegalStateException("Asset " + key + " is not loaded");
        }
        return assets.get(key).get();
    }

    public Collection<A> getAll() {
        Collection<A> assets = new LinkedList<>();
        for (AssetLoader<A> asset : this.assets.values()) {
            assets.add(asset.get());
        }
        return assets;
    }

    public interface ProgressListener extends EventListener {
        void onProgress(double progress, boolean done);
    }
}