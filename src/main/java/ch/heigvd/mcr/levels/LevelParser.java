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
                        state.addEntity(new Car(
                                Integer.parseInt(values[0]),
                                Integer.parseInt(values[1]),
                                Direction.getFromKey(values[2]),
                                Color.RED
                        ));
                        break;

                    default:
                        switch (values[0]) {
                            case "c":
                                state.addEntity(new Car(
                                        Integer.parseInt(values[1]),
                                        Integer.parseInt(values[2]),
                                        Direction.getFromKey(values[3]),
                                        Color.getFromKey(values[4])
                                ));
                                break;
                            case "t":
                                state.addEntity(new Truck(
                                        Integer.parseInt(values[1]),
                                        Integer.parseInt(values[2]),
                                        Direction.getFromKey(values[3]),
                                        Color.getFromKey(values[4])
                                ));
                                break;
                            case "o":
                                state.addEntity(new Cone(Integer.parseInt(values[1]), Integer.parseInt(values[2]), Direction.UP, Color.ORANGE));
                                break;
                            case "p":
                                state.addEntity(new Pedestrian(Integer.parseInt(values[1]), Integer.parseInt(values[2]), Direction.UP, Color.ORANGE));
                                break;
                            default:
                                throw new RuntimeException("Invalid entity type");
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
