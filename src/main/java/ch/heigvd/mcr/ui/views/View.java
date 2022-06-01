package ch.heigvd.mcr.ui.views;

/**
 * Interface utilisée pour les différentes vues de l'application
 *
 * @author Valentin Kaelin
 */
public interface View {
    /**
     * Redessine la vue
     */
    void repaint();
    
    /**
     * Ferme la vue
     */
    void close();
    
    /**
     * Affiche la vue
     */
    void show();
}
