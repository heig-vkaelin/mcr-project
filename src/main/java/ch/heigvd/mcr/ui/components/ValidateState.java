package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Position;

/**
 * Record représentant un état de jeu valide
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public record ValidateState(Position position, Entity collidedEntity,
                            boolean hasReachedExit) {
}
