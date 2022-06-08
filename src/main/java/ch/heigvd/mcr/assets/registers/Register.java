package ch.heigvd.mcr.assets.registers;

import ch.heigvd.mcr.assets.AssetManager;

public interface Register<A> {
    void register(AssetManager<A> assetManager);
}
