package ch.heigvd.mcr.entities;

/**
 * Interface définissant les dimensions et le nom de chaque type d'entité
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public interface EntityType {
    String getKey();

    String getCategoryKey();

    int getWidth();

    int getLength();
}
