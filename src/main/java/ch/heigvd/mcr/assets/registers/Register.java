package ch.heigvd.mcr.assets.registers;

import ch.heigvd.mcr.assets.Asset;

public interface Register<A> {
    void register(Asset<A> asset);
}
