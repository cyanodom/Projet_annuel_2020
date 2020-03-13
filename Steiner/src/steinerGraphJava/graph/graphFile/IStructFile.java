package steinerGraphJava.graph.graphFile;

public interface IStructFile {

	// REQUETES

	/*
	 * Le nombre de sommets au total
	 */
    int getNbSommets();

    /*
     * Tous les noms des sommets
     */
	String[] getNomSommets();

	/*
	 * Nom des sommets Finaux
	 */
	String[] getNomSommetsT();


    /*
     * Tableau bidimentionel repr�sentant la Matrice de poids originale
     */
    int[][] getBase();


    // COMMANDES

    /*
     * Ajout d'un nombre de sommets
     * @pre
     * 		nbSommets >= 0
     */
    void addNbSommets(int nbSommets);

    /*
     * Ajout des noms de sommets
     * @pre
     * 		nomSommets.length >= 0
     */
    void addNomSommets(String[] nomSommets);

    /*
     * Ajout du nom de sommets de rendez-vous
     * @pre
     * 		nomSommetsRdv.length > 0
     */
    void addNomSommetsT(String[] nomSommetsT);

    /*
     * Ajout de la matrice des distances d'origine
     * @pre
     * 		storage != null
     */
    void addBase(int[][] storage);
}
