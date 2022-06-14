package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.commands.LoadLevelCommand;
import ch.heigvd.mcr.commands.MoveCommand;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Pedestrian;
import ch.heigvd.mcr.entities.Position;
import ch.heigvd.mcr.ui.MainFrame;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Classe pour l'affichage et le déplacement d'entités sur le plateau de jeu
 *
 * @author Nicolas Crausaz
 */
public class DraggableEntity extends DrawableEntity {
    public DraggableEntity(Entity entity, int baseRatio) {
        super(entity, baseRatio);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        DragListener dragListener = new DragListener() {
            @Nullable() ValidateState state;

            int startX, startY;

            @Override
            public void dragStarted(MouseEvent e) {
                startX = entity.getX();
                startY = entity.getY();
            }

            @Override
            public void dragEnded(MouseEvent e) {
                if (state == null) return;
                // On remet les coordonées d'origine pour les avoir pour le rollback.. on peut surement mieux faire

                // TODO: enlever avant dimanche
                // Perdu => reset
                if (state.collidedEntity() instanceof Pedestrian) {
                    new LoadLevelCommand(GameController.getInstance().getState().getId()).execute();
                } else {
                    entity.setPosition(startX, startY);
                    GameController.getInstance().playTurn(new MoveCommand(entity, state.position()));

                    if (state.hasReachedExit()) {
                        GameController.getInstance().endGame();
                    }
                }
            }

            @Override
            public void dragMoved(MouseEvent e) {
                Position position = new Position(
                        (int) Math.round((e.getX() - offsetX) / (double) getRatio() + entity.getX()),
                        (int) Math.round((e.getY() - offsetY) / (double) getRatio() + entity.getY())
                );
                if (position.x() != entity.getX() || position.y() != entity.getY()) {
                    state = GameController.getInstance().validatePosition(entity, position);
                    entity.setPosition(state.position());
                    if (state.collidedEntity() != null) {
                        stopDragging(); // to avoid infinite calls
                        state.collidedEntity().onCrash();
                        System.out.println("Entity[" + entity.getType() + "] Colliding with " + state.collidedEntity().getType());
                    }
                    repaint();
                }
            }
        };

        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
    }
}
