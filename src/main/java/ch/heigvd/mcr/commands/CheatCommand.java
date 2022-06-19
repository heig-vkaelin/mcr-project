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
        List<Entity> entities = GameController.getInstance().getState().getEntities();
        if (entities.size() < 2) return;

        Entity[] array = new Entity[entities.size()];
        entities.toArray(array);

        int j = rand.nextInt(array.length);

        for (int i = 0; i < array.length; ++i) {
            if (i == j) {
                if (!array[i].isThePlayer()) {
                    deletedEntity = array[i];
                } else  {
                    deletedEntity = array[i + 1];
                }
            }
        }

        if (deletedEntity != null) {
            System.out.println(deletedEntity.getType());
            GameController.getInstance().getState().removeEntity(deletedEntity);
            deletedEntity.kill();
            MainFrame.getInstance().refreshPlayView();
        }
    }

    @Override
    public void rollback() {
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
