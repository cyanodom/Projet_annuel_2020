package steinerGraphJava.model;

import java.util.Observer;

/**
 * Une interface pour pallier le manque de spécification sur ce sujet dans
 *  l'API.
 * @inv <pre>
 *     countObservers() >= 0 </pre>
 */
@SuppressWarnings("deprecation")
public interface ObservableModel {
    
    // REQUETES
    
    /**
     * Le nombre d'observateurs enregistrés auprès de ce modèle.
     */
    int countObservers();

    // COMMANDES
    
    /**
     * Enregistre un observateur sur ce modèle s'il n'y est pas déjà.
     * @pre <pre>
     *     o != null </pre>
     * @post <pre>
     *     o est enregistré auprès du modèle
     *     si o n'était pas déjà enregistré alors
     *         countObservers() == old countObservers() + 1
     *     sinon
     *         rien n'a changé </pre>
     * @throws NullPointerException si o est null
     */
    void addObserver(Observer o);
    
    /**
     * Désinscrit un observateur de ce modèle.
     * Si o vaut null, cette méthode n'a pas d'effet.
     * @post <pre>
     *     o n'est pas enregistré auprès du modèle
     *     si o était enregistré alors
     *         countObservers() == old countObservers() - 1
     *     sinon
     *         rien n'a changé </pre>
     */
    void deleteObserver(Observer o);
    
    /**
     * Désinscrit tous les observateurs de ce modèle.
     * @post <pre>
     *     countObservers() == 0 </pre>
     */
    void deleteObservers();
    
    /**
     * Notifie tous les observateurs de ce modèle si nécessaire, c'est-à-dire
     *  s'il n'y a pas eu de notification depuis la dernière modification
     *  du modèle.
     * Chaque observateur enregistré exécute en séquence sa méthode update
     *  avec pour arguments ce modèle et null.
     */
    void notifyObservers();
    
    /**
     * Notifie tous les observateurs de ce modèle si nécessaire, c'est-à-dire
     *  s'il n'y a pas eu de notification depuis la dernière modification
     *  du modèle.
     * Chaque observateur enregistré exécute en séquence sa méthode update
     *  avec pour arguments ce modèle et arg.
     */
    void notifyObservers(Object arg);
}
