package ch.heigvd.mcr.assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SpriteSheet {
    private final BufferedImage source;
    private final Map<String, Image> sprites = new HashMap<>();

    public SpriteSheet(URL spriteSheetFile) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(spriteSheetFile.openStream()));
        String imagePath = reader.readLine();
        Path imagePathRelativeToSpriteSheet = Path.of(spriteSheetFile.toURI()).getParent().resolve(imagePath);
        source = ImageIO.read(imagePathRelativeToSpriteSheet.toFile());
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            String name = split[0];
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[2]);
            int width = Integer.parseInt(split[3]);
            int height = Integer.parseInt(split[4]);
            sprites.put(name, source.getSubimage(x, y, width, height));
        }
    }

    public Image source() {
        return source;
    }

    public Image get(String name) {
        return sprites.get(name);
    }
}
