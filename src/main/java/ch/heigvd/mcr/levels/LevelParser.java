package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;
import ch.heigvd.mcr.entities.types.ObstacleType;
import ch.heigvd.mcr.entities.types.PedestrianType;
import ch.heigvd.mcr.entities.types.VehicleType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe permettant de lire et sérialiser des fichiers de niveau du jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class LevelParser {
    private final static int MIN_LEVEL_SIZE = 6;
    private final static int MAX_LEVEL_SIZE = 20;

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
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");

                // Ignore si ligne vide
                if (line.trim().isEmpty()) {
                    continue;
                }

                if (values.length < 1) {
                    throw new RuntimeException("Malformed file");
                }

                switch (lineNumber) {
                    case 0 -> { // Taille grille
                        final int size = Integer.parseInt(values[0]);
                        if (size < MIN_LEVEL_SIZE || size > MAX_LEVEL_SIZE) {
                            throw new RuntimeException("Level size cannot be less than " + MIN_LEVEL_SIZE +
                                    " or greater than " + MAX_LEVEL_SIZE);
                        }
                        state.setSideSize(size);
                    }
                    case 1 -> { // Difficulté
                        int enumIndex = Integer.parseInt(values[0]) - 1;
                        state.setDifficulty(Difficulty.values()[enumIndex]);
                    }
                    case 2 -> { // Voiture du joueur
                        final Position position = new Position(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                        final Direction direction = Direction.getFromKey(values[2]);
                        state.addPlayer(position, direction, VehicleType.getFromKey(values[3]));

                        if (direction == Direction.UP || direction == Direction.DOWN) {
                            state.setExit(position.x(), direction);
                        } else {
                            state.setExit(position.y(), direction);
                        }
                    }
                    default -> {
                        final Position position = new Position(Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                        final Direction direction = Direction.getFromKey(values[3]);

                        // Type d'entité
                        switch (values[0]) {
                            case "v" -> state.addVehicle(position, direction, VehicleType.getFromKey(values[4]));
                            case "o" -> state.addObstacle(position, direction, ObstacleType.getFromKey(values[4]));
                            case "p" -> state.addPedestrian(position, direction, PedestrianType.getFromKey(values[4]));
                            default -> throw new RuntimeException("Invalid entity type");
                        }
                    }
                }
                ++lineNumber;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        state.saveState();
        return state;
    }

    /**
     * Récupère l'id du niveau depuis le nom du fichier
     *
     * @param levelPath : chemin du fichier de niveau
     * @return l'id du niveau
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
