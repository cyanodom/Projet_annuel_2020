package steinerGraphJava.algorithms.population;

public class Individu {
	
	// ATTRIBUTS
	
	private int[] individu;
	
	
	// CONSTRUCTEUR
	
	/*
	 * Cela représente un individu
	 */
	public Individu(int[] individu) {
		this.individu = individu;
	}
	
	
	// REQUETES
	
	/*
	 * Permet de récupérer un individu
	 */
	public int[] getIndividu() {
		return individu;
	}
}
