package steinerGraphJava.algorithms.population;

public class Individu {
	
	// ATTRIBUTS
	
	private int[] individu;
	
	
	// CONSTRUCTEUR
	
	/*
	 * Cela repr�sente un individu
	 */
	public Individu(int[] individu) {
		this.individu = individu;
	}
	
	
	// REQUETES
	
	/*
	 * Permet de r�cup�rer un individu
	 */
	public int[] getIndividu() {
		return individu;
	}
}
