package ch.heigvd.mcr;

import ch.heigvd.mcr.commands.Command;

import java.util.EventListener;

public interface CommandListener extends EventListener {
    void commandExecuted(Command command);
}
