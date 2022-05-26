import entities.Entity;
import levels.LevelParser;
import levels.LevelState;

/**
 * Point d'entrée de l'application
 *
 * @author Nicolas Crausaz
 */
public class Main {
    public static void main(String[] args) {
        // TMP: test du parser
        LevelState state = LevelParser.parseLevelFile("1.txt");

        System.out.println(state.getDifficulty());
        System.out.println(state.getSideSize());
        for (Entity e: state.getEntities()) {
            System.out.println(e);
        }
    }
}
