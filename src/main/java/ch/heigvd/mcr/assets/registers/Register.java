package ch.heigvd.mcr.assets.registers;

import ch.heigvd.mcr.assets.AssetManager;

/**
 * Interface permettant d'enregistrer des assets
 *
 * @param <A> : type des l'assets
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public interface Register<A> {
    void register(AssetManager<A> assetManager);
}
