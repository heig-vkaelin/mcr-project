package ch.heigvd.mcr.levels;

import ch.heigvd.mcr.entities.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

/**
 * Classe permettant de lire et sérialiser des fichiers de niveau du jeu
 *
 * @author Nicolas Crausaz
 */
public class LevelParser {

    // TODO: faire des validations plus strictes
    // TODO: Gérer toutes les exceptions

    private final static URL LEVELS_DIR = ClassLoader.getSystemResource("levels");

    public static LevelState parseLevelFile(String filename) {
        LevelState state = new LevelState();

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
