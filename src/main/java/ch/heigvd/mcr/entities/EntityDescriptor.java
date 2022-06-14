package ch.heigvd.mcr.entities;

public record EntityDescriptor<T extends Entity>(Class<T> entityClass, Position position, Direction direction,
                                                 EntityType type) {
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
