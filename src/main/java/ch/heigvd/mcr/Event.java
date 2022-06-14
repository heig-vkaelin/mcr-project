package ch.heigvd.mcr;

/**
 * Interface d'un événement auquel les observateurs peuvent se désabonner
 *
 * @author Jonathan Friedli
 * @author Lazar Pavicevic
 * @author Maxime Scharwath
 * @author Nicolas Crausaz
 * @author Valentin Kaelin
 */
public interface Event {
    void unsubscribe();
}
