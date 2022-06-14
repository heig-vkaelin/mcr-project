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
        int it = rand.nextInt(GameController.getInstance().getState().getEntities().size());
        int i = 0;

        for (Entity e : GameController.getInstance().getState().getEntities()) {
            if (it == i) {
                if (!e.isThePlayer()) {
                    System.out.println("delete" + e);
                    deletedEntity = e;
                } else {
                    // si c'est le player
                    System.out.println("c'est le player");
                    // todo: prendre le suivante ou celui
                }
            }
            ++i;
        }

        if (deletedEntity != null) {
            GameController.getInstance().removeEntity(deletedEntity);
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
