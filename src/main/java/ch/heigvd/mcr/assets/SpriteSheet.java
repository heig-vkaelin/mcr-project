package ch.heigvd.mcr.assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant une SpriteSheet contenant plusieurs sprites
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public class SpriteSheet {
    private final Map<String, Image> sprites = new HashMap<>();

    /**
     * Crée une nouvelle SpriteSheet à partir d'une image et extrait les
     * différents sprites
     *
     * @param spriteSheetFile : chemin vers la spritesheet
     * @throws IOException si le fichier n'est pas trouvé ou est invalide
     */
    public SpriteSheet(URL spriteSheetFile) throws IOException {
        BufferedImage source = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(spriteSheetFile.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                if (source == null) {
                    Path imagePathRelativeToSpriteSheet = Path.of(spriteSheetFile.toURI()).getParent().resolve(line);
                    if (spriteSheetFile.getProtocol().equals("jar")) {
                        String imagePath = imagePathRelativeToSpriteSheet.toString().substring(1);
                        source = ImageIO.read(ClassLoader.getSystemResource(imagePath));
                    } else {
                        source = ImageIO.read(imagePathRelativeToSpriteSheet.toFile());
                    }
                    continue;
                }
                String[] parts = line.split(" ");
                String name = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int width = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                sprites.put(name, source.getSubimage(x, y, width, height));
            }
        } catch (Exception e) {
            throw new IOException("Error while reading sprite sheet file " + spriteSheetFile, e);
        }
    }

    /**
     * Récupère l'image correspondant à la clé donnée
     *
     * @param name : nom de l'image
     * @return l'image souhaitée
     */
    public Image get(String name) {
        return sprites.get(name);
    }
}
