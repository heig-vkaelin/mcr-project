package ch.heigvd.mcr.ui.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Listener pour les évènements de drag and drop de la souris
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public abstract class DragListener implements MouseListener, MouseMotionListener {
    protected int offsetX;
    protected int offsetY;
    protected boolean isDragged;

    /**
     * Arrête le drag and drop
     */
    public void stopDragging() {
        isDragged = false;
    }

    @Override
    final public void mouseClicked(MouseEvent e) {
    }

    @Override
    final public void mousePressed(MouseEvent e) {
        offsetX = e.getX();
        offsetY = e.getY();
        isDragged = true;
        dragStarted(e);
    }

    @Override
    final public void mouseReleased(MouseEvent e) {
        if (!isDragged)
            return; // avoid calling dragEnded when the mouse is not dragged
        isDragged = false;
        dragEnded(e);
    }

    @Override
    final public void mouseEntered(MouseEvent e) {
    }

    @Override
    final public void mouseExited(MouseEvent e) {
    }

    @Override
    final public void mouseDragged(MouseEvent e) {
        if (!isDragged) return;
        dragMoved(e);
        if (!isDragged) {
            dragEnded(e);
        }
    }

    @Override
    final public void mouseMoved(MouseEvent e) {
    }

    abstract public void dragStarted(MouseEvent e);

    abstract public void dragEnded(MouseEvent e);

    abstract public void dragMoved(MouseEvent e);
}
