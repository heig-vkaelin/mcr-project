package utils;

import java.util.AbstractMap;

/**
 * Helper pour créer des paires de valeurs (à refactor pour avoir des méthodes aux noms adéquats)
 * source : https://stackoverflow.com/a/15072119
 *
 * @author Lazar Pavicevic
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> extends AbstractMap.SimpleEntry<X, Y> {
    public Pair(X key, Y value) {
        super(key, value);
    }
}
