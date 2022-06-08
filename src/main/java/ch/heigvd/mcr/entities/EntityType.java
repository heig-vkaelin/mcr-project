package ch.heigvd.mcr.entities;

/**
 * Interface définissant les dimensions et le nom de chaque type d'entité
 *
 * @author Lazar Pavicevic
 */
public interface EntityType {
    String getKey();

    String getCategoryKey();

    int getWidth();

    int getLength();
}
