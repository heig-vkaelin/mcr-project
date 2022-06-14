package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Position;

public record ValidateState(Position position, Entity collidedEntity) {
}
