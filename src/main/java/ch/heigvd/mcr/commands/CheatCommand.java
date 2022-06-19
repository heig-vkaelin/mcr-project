package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;
import ch.heigvd.mcr.entities.Entity;
import ch.heigvd.mcr.ui.MainFrame;

import java.util.*;

public class CheatCommand implements Command {
    private static final Random rand = new Random();
    private Entity deletedEntity;

    @Override
    public void execute() {
        final LinkedList<Entity> entities = GameController.getInstance().getState().getEntities();
        int j = rand.nextInt(entities.size());
        ListIterator<Entity> it = entities.listIterator(j);

        while (it.hasNext()) {
            final Entity e = it.next();
            if (!e.isThePlayer()) {
                deletedEntity = e;
            } else if (it.nextIndex() - 1 != j) {
                deletedEntity = it.next();
            }
        }

        if (deletedEntity != null) {
            GameController.getInstance().getState().removeEntity(deletedEntity);
            deletedEntity.kill();
            MainFrame.getInstance().refreshPlayView();
        }
    }

    @Override
    public void rollback() {
        // TODO: implement rollback psk les entités crevées ne reviennent po lors
        //  d'un undo d'un move par ex
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
