package steinerGraphJava.algorithms.population;

public class Population {

	// ATTRIBUTS

	private int nbIndividu;
	private int[][] list;


	// CONSTRUCTEUR
	
	/*
	 * Nous indiquons combien d'individu nous voulons par g�n�ration et par groupe
	 * 
	 * Ici, nous voulons nbIndividus individus dans nbIndividus groupes
	 */
	public Population(int nbIndividu) {
		this.nbIndividu = nbIndividu;
		list = new int[nbIndividu][nbIndividu];
	}


	// REQUETES

	/*
	 * Permet de r�cup�rer tous les groupes de la g�n�ration cr��
	 */
	public int[][] getList() {
		return list;
	}


	// COMMANDES

	
	/*
	 * Permet de g�n�rer la population que nous allons ensuite g�n�rer
	 * 
	 * nbRes indique le nombre de groupe que nous voulons garder de la g�n�ration pr�c�dente
	 * 
	 * Ici, on prend en compte que les groupes que vous voulez garder sont plac�s le plus haut possible dans le tableau
	 */
	public void generatePopulation(int nbRes) {
		for (int i = nbRes; i < nbIndividu; ++i) {
			for (int j = 0; j < nbIndividu; ++j) {
				list[i][j] = randomBit();
			}
		}
	}
	
	
	/*
	 * Permet de cr�er un bit al�atoirement, c�d un nombre compris entre 0 et 1
	 */
	public int randomBit() {
		return (int)Math.round(Math.random());
	}
}
