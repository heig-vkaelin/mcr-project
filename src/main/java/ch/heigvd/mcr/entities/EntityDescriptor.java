package ch.heigvd.mcr.entities;

import ch.heigvd.mcr.entities.types.EntityType;

/**
 * Record permettant de créer une nouvelle entité du type souhaité
 *
 * @param <T> : type de l'entité
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public record EntityDescriptor<T extends Entity>(Class<T> entityClass,
                                                 Position position,
                                                 Direction direction,
                                                 EntityType type) {
    /**
     * Crée une nouvelle entité
     *
     * @return l'entité créée avec les données précédemment sauvegardées
     */
    public T createEntity() {
        try {
            return entityClass
                    .getConstructor(position.getClass(), direction.getClass(), type.getClass())
                    .newInstance(position, direction, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
