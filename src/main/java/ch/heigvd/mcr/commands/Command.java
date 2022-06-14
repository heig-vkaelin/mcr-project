package ch.heigvd.mcr.commands;

public interface Command {
    void execute();

    void rollback();

    boolean isUndoable();
}
