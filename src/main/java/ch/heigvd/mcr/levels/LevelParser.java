package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe permettant de lire et sérialiser des fichiers de niveau du jeu
 *
 * @author Nicolas Crausaz
 */
public class LevelParser {

    // TODO: peut etre bouger les vérif dans LevelState avec le reste
    // TODO: Gérer toutes les exceptions

    private final static URL LEVELS_DIR = ClassLoader.getSystemResource("levels");

    private final static int MIN_LEVEL_SIZE = 6;

    /**
     * Charge tous les niveaux du jeu
     *
     * @return la liste des niveaux
     */
    public static List<LevelState> loadAllLevels() {
        LinkedList<LevelState> levels = new LinkedList<>();
        File folder = new File(LEVELS_DIR.getFile());
        File[] levelNames = folder.listFiles();

        if (levelNames != null) {
            Arrays.sort(levelNames, Comparator.comparing(File::getName));
            for (File levelName : levelNames) {
                levels.add(parseLevelFile(levelName.getName()));
            }
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

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(LEVELS_DIR.getFile(), filename)))) {
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
                    case 0 -> {
                        // Taille grille
                        final int size = Integer.parseInt(values[0]);
                        if (size < MIN_LEVEL_SIZE) {
                            throw new IllegalArgumentException("Level size cannot be less than " + MIN_LEVEL_SIZE);
                        }
                        state.setSideSize(size);
                    }
                    case 1 -> {
                        // Difficulté:
                        int enumIndex = Integer.parseInt(values[0]) - 1;
                        state.setDifficulty(Difficulty.values()[enumIndex]);
                    }
                    case 2 -> {
                        // Voiture du joueur
                        final int x = Integer.parseInt(values[0]);
                        final int y = Integer.parseInt(values[1]);
                        if (!validateCoordinates(x, y, state.getSideSize())) {
                            throw new IllegalArgumentException("Entity coordinates must fit in the game dimensions");
                        }
                        state.addEntity(new Vehicle(
                                x,
                                y,
                                Direction.getFromKey(values[2]),
                                VehicleType.RED_CAR
                        ));
                    }
                    default -> {
                        final int x = Integer.parseInt(values[1]);
                        final int y = Integer.parseInt(values[2]);
                        if (!validateCoordinates(x, y, state.getSideSize())) {
                            throw new IllegalArgumentException("Entity coordinates must fit in the game dimensions");
                        }
                        switch (values[0]) {
                            case "v" -> state.addEntity(new Vehicle(
                                    x,
                                    y,
                                    Direction.getFromKey(values[3]),
                                    VehicleType.getFromKey(values[4])
                            ));
                            case "o" -> state.addEntity(new Obstacle(
                                    x,
                                    y,
                                    Direction.getFromKey(values[3]),
                                    ObstacleType.getFromKey(values[4])));
                            case "p" -> state.addEntity(new Pedestrian(
                                    x,
                                    y,
                                    Direction.getFromKey(values[3]),
                                    PedestrianType.getFromKey(values[4])));
                            default -> throw new RuntimeException("Invalid entity type");
                        }
                    }
                }
                ++count;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    private static boolean validateCoordinates(int x, int y, int max) {
        return !(x < 0 || y < 0 || x >= max || y >= max);
    }
}
