package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe permettant de lire et sérialiser des fichiers de niveau du jeu
 *
 * @author Nicolas Crausaz
 */
public class LevelParser {

    private final static int MIN_LEVEL_SIZE = 6;

    /**
     * Récupère la configuration d'un niveau depuis un fichier
     *
     * @param levelPath : chemin du fichier de niveau
     * @return l'état du niveau valide
     * @throws RuntimeException en cas de fichier non conforme
     */
    public static LevelState parseLevelFile(URL levelPath) throws RuntimeException {

        LevelState state = new LevelState(getIdFromFilename(levelPath));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(levelPath.openStream()))) {
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
                        // Difficulté
                        int enumIndex = Integer.parseInt(values[0]) - 1;
                        state.setDifficulty(Difficulty.values()[enumIndex]);
                    }
                    case 2 -> {
                        // Voiture du joueur
                        final int x = Integer.parseInt(values[0]);
                        final int y = Integer.parseInt(values[1]);
                        final Direction direction = Direction.getFromKey(values[2]);

                        if (isOutOfBounds(x, y, state.getSideSize())) {
                            throw new IllegalArgumentException("Entity coordinates must fit in the game dimensions");
                        }
                        state.addVehicle(x, y, direction, VehicleType.getFromKey(values[3]));

                        if (direction == Direction.UP || direction == Direction.DOWN) {
                            state.setExit(x, direction);
                        } else {
                            state.setExit(y, direction);
                        }
                    }
                    default -> {
                        final int x = Integer.parseInt(values[1]);
                        final int y = Integer.parseInt(values[2]);

                        if (isOutOfBounds(x, y, state.getSideSize())) {
                            throw new IllegalArgumentException("Entity coordinates must fit in the game dimensions");
                        }

                        final Direction direction = Direction.getFromKey(values[3]);

                        // Type d'entité
                        switch (values[0]) {
                            case "v" -> state.addVehicle(x, y, direction, VehicleType.getFromKey(values[4]));
                            case "o" -> state.addObstacle(x, y, direction, ObstacleType.getFromKey(values[4]));
                            case "p" -> state.addPedestrian(x, y, direction, PedestrianType.getFromKey(values[4]));
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

    /**
     * Vérifie si les coordonnées sont valides pour la taille du plateau
     *
     * @param x    Coordonnée axe x
     * @param y    Coordonnée axe y
     * @param size la taille du plateau de jeu
     * @return vrai si valide, sinon faux
     */
    private static boolean isOutOfBounds(int x, int y, int size) {
        return x < 0 || y < 0 || x >= size || y >= size;
    }

    /**
     * Récupère l'id du niveau depuis le nom du fichier
     *
     * @param levelPath : chemin du fichier de niveau
     * @return id du niveau
     * @throws RuntimeException si le nom du fichier n'est pas dans le format attentu
     */
    private static int getIdFromFilename(URL levelPath) throws RuntimeException {
        try {
            Path path = Paths.get(levelPath.toURI());
            return Integer.parseInt(path.getFileName().toString().split("\\.")[0]);
        } catch (Exception e) {
            throw new RuntimeException("Invalid level file name");
        }
    }
}
