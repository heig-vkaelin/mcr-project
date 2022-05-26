package levels;

import entities.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Classe permettant de lire et sérialiser des fichiers de niveau du jeu
 */
public class LevelParser {

    private final static String LEVELS_DIR = "./src/levels/data/";

    public static void parseLevelFile(String filename) {
        // TODO: gerer le pieton
        // TODO: faire des validations strictes
        // TODO: direction & couleurs

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(LEVELS_DIR, filename)))) {
            String line;
            int count = 0;
            LevelState state = new LevelState();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");

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
                                //Direction.valueOf(values[2]),
                                Direction.UP,
                                Color.RED
                        ));
                        break;

                    default:
                        switch (values[0]) {
                            case "c":
                                state.addEntity(new Car(
                                        Integer.parseInt(values[1]),
                                        Integer.parseInt(values[2]),
                                        Direction.UP,
                                        Color.RED
                                ));
                                break;
                            case "t":
                                state.addEntity(new Truck(
                                        Integer.parseInt(values[1]),
                                        Integer.parseInt(values[2]),
                                        Direction.UP,
                                        Color.RED
                                ));
                                break;
                            case "p":
                                // state.addEntity(new Pedestrian());
                                break;
                            default:
                                throw new RuntimeException("Invalid entity type");
                        }
                }
                ++count;
            }

            System.out.println(state.getSideSize());
            System.out.println(state.getDifficulty());
            for (Entity e: state.getEntities()) {
                System.out.println(e.getDirection());
            }

            // Taille grille

        } catch (Exception e) {
            e.printStackTrace();
        }


        // Difficulté
    }

}
