package ch.heigvd.mcr.assets.registers;

import ch.heigvd.mcr.assets.AssetManager;
import ch.heigvd.mcr.assets.loaders.LevelAssetLoader;
import ch.heigvd.mcr.levels.LevelState;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Classe permettant d'enregistrer les différents fichiers de niveaux du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class LevelsRegister implements Register<LevelState> {
    @Override
    public void register(AssetManager<LevelState> assetManager) {
        try {
            URI uri = ClassLoader.getSystemResource("levels").toURI();
            if (uri.getScheme().equals("jar")) {//si on est dans un jar
                FileSystems.newFileSystem(uri, new HashMap<>());
            }
            Files.walk(Paths.get(uri)).filter(path -> !Files.isDirectory(path)).sorted().forEach(path -> {
                int id = Integer.parseInt(path.getFileName().toString().split("\\.")[0]);
                String relativePath = Paths.get(uri).getParent().relativize(path).toString();
                assetManager.register("level" + id, new LevelAssetLoader(relativePath));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
