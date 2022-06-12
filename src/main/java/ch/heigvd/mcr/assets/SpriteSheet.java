package ch.heigvd.mcr.assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SpriteSheet {
    private final Map<String, Image> sprites = new HashMap<>();
    private BufferedImage source = null;

    public SpriteSheet(URL spriteSheetFile) throws IOException {
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
                    }else{
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

    public Image source() {
        return source;
    }

    public Image get(String name) {
        return sprites.get(name);
    }

    public Collection<Image> getAll() {
        return sprites.values();
    }

    @Override
    public String toString() {
        return "SpriteSheet{" +
                sprites.keySet() +
                '}';
    }
}
