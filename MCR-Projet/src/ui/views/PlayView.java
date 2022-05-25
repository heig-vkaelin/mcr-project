package ui.views;

/**
 * Singleton représentant la vue de jeu dans un niveau spécifique
 */
public class PlayView implements View {
    private static PlayView instance;
    
    /**
     * @return l'instance de la vue permettant à l'utilisateur de jouer
     */
    public static PlayView getInstance() {
        if (instance == null)
            instance = new PlayView();
        return instance;
    }
    
    @Override
    public void repaint() {
    
    }
}
