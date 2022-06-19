package ch.heigvd.mcr.ui.components;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.commands.LoadLevelCommand;
import ch.heigvd.mcr.commands.MoveCommand;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.entities.Position;
import ch.heigvd.mcr.entities.types.TypeCategory;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Classe pour l'affichage et le déplacement d'entités sur le plateau de jeu
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public class DraggableEntity extends DrawableEntity {
    /**
     * Crée une nouvelle entité déplacable par le joueur
     *
     * @param entity    : entité de base
     * @param baseRatio : ratio initial de l'affichage
     */
    public DraggableEntity(Entity entity, int baseRatio) {
        super(entity, baseRatio);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        DragListener dragListener = createDragListener();
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
    }

    /**
     * @return un nouveau listener pour le drag and drop sur l'entité
     */
    private DragListener createDragListener() {
        return new DragListener() {
            ValidateState state;

            int startX, startY;

            @Override
            public void dragStarted(MouseEvent e) {
                startX = getEntity().getX();
                startY = getEntity().getY();
            }

            @Override
            public void dragEnded(MouseEvent e) {
                if (state == null) return;
                Entity collided = state.collidedEntity();
                if (collided != null && collided.getType().getCategory() == TypeCategory.PEDESTRIAN) {
                    // Perdu → reset du niveau
                    new LoadLevelCommand(GameController.getInstance().getState().getId()).execute();
                } else {
                    getEntity().setPosition(startX, startY);
                    GameController.getInstance().playTurn(new MoveCommand(getEntity(), state.position()));

                    if (state.hasReachedExit()) {
                        GameController.getInstance().endGame();
                    }
                }
            }

            @Override
            public void dragMoved(MouseEvent e) {
                Position position = new Position(
                        (int) Math.round((e.getX() - offsetX) / (double) getRatio() + getEntity().getX()),
                        (int) Math.round((e.getY() - offsetY) / (double) getRatio() + getEntity().getY())
                );
                if (position.x() != getEntity().getX() || position.y() != getEntity().getY()) {
                    state = GameController.getInstance().validatePosition(getEntity(), position);
                    getEntity().setPosition(state.position());
                    if (state.collidedEntity() != null) {
                        stopDragging(); // pour éviter les calls infinis
                        state.collidedEntity().onCrash();
                    }
                    repaint();
                }
            }
        };
    }
}
