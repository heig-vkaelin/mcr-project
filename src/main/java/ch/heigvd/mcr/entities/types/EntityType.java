package ch.heigvd.mcr.entities.types;

/**
 * Interface définissant les dimensions et le nom de chaque type d'entité
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public interface EntityType {
    /**
     * @return la clé du type d'entité
     */
    String getKey();

    /**
     * @return la catégorie d'entités dont fait parti le type
     */
    TypeCategory getCategory();

    /**
     * @return la largeur du type d'entité
     */
    int getWidth();

    /**
     * @return la longueur du type d'entité
     */
    int getLength();
}
