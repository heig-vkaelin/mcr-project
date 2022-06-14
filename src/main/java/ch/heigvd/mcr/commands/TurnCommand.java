package ch.heigvd.mcr.commands;

import ch.heigvd.mcr.GameController;

public class TurnCommand extends MacroCommand {
    @Override
    public void execute() {
        super.execute();
        GameController.getInstance().getState().addMove();
    }

    @Override
    public void rollback() {
        super.rollback();
        GameController.getInstance().getState().cancelMove();
    }
}
