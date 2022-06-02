package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe permettant de lire et sérialiser des fichiers de niveau du jeu
 *
 * @author Nicolas Crausaz
 */
public class LevelParser {

    // TODO: faire des validations plus strictes
    // TODO: Gérer toutes les exceptions

    private final static URL LEVELS_DIR = ClassLoader.getSystemResource("levels");

    /**
     * Charge tous les niveaux du jeu
     *
     * @return la liste des niveaux
     */
    public static List<LevelState> loadAllLevels() {
        //liste des niveaux
        List<LevelState> levels = new LinkedList<>();
        try {
            URI uri = ClassLoader.getSystemResource("levels").toURI();
            if (uri.getScheme().equals("jar")) {//si on est dans un jar
                FileSystems.newFileSystem(uri, new HashMap<>());
            }
            Files.walk(Paths.get(uri)).filter(path -> !Files.isDirectory(path)).sorted().forEach(path -> {
                LevelState l = parseLevelFile(path.getFileName().toString());
                levels.add(l);
                System.out.println("Loaded level " + l.getId());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return levels;
    }

    public static LevelState parseLevelFile(String filename) {
        int id;
        try {
            id = Integer.parseInt(filename.split("\\.")[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid level file name");
        }

        LevelState state = new LevelState(id);
        //need to use ClassLoader.getSystemResourceAsStream...
        InputStream stream = ClassLoader.getSystemResourceAsStream("levels/" + filename);
        if (stream == null) {
            throw new RuntimeException("Level file not found");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            int count = 0;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");

                // Ignore si ligne vide
                if (line.trim().isEmpty()) {
                    continue;
                }

                if (values.length < 1) {
                    throw new RuntimeException("Malformed file");
                }

                switch (count) {
                    case 0:
                        // Taille grille
                        state.setSideSize(Integer.parseInt(values[0]));
                        break;

                    case 1:
                        // Difficulté:
                        int enumIndex = Integer.parseInt(values[0]) - 1;
                        state.setDifficulty(Difficulty.values()[enumIndex]);
                        break;

                    case 2:
                        // Voiture du joueur
                        state.addEntity(new Vehicle(
                                Integer.parseInt(values[0]),
                                Integer.parseInt(values[1]),
                                Direction.getFromKey(values[2]),
                                VehicleType.RED_CAR
                        ));
                        break;

                    default:
                        switch (values[0]) {
                            case "v" -> state.addEntity(new Vehicle(
                                    Integer.parseInt(values[1]),
                                    Integer.parseInt(values[2]),
                                    Direction.getFromKey(values[3]),
                                    VehicleType.getFromKey(values[4])
                            ));
                            case "o" -> state.addEntity(new Obstacle(
                                    Integer.parseInt(values[1]),
                                    Integer.parseInt(values[2]),
                                    Direction.getFromKey(values[3]),
                                    ObstacleType.getFromKey(values[4])));
                            case "p" -> state.addEntity(new Pedestrian(
                                    Integer.parseInt(values[1]),
                                    Integer.parseInt(values[2]),
                                    Direction.getFromKey(values[3]),
                                    PedestrianType.getFromKey(values[4])));
                            default -> throw new RuntimeException("Invalid entity type");
                        }
                }
                ++count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }
}
