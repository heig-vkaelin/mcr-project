package ch.heigvd.mcr;

import ch.heigvd.mcr.commands.Command;

import java.util.EventListener;

/**
 * Interface définissant un listener écoutant les commandes
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 * @version 2022-06-19
 */
public interface CommandListener extends EventListener {
    void commandExecuted(Command command);
}
