package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.entities.Entity;

public record ValidateState(int x, int y, Entity collidedEntity) {
}
