package steinerGraphJava.algorithms.Population;

public class Population {

	// ATTRIBUTS

	private int nbIndividu;
	private int[][] list;


	// CONSTRUCTEUR

	public Population(int nbIndividu) {
		this.nbIndividu = nbIndividu;
		list = new int[nbIndividu][nbIndividu];
	}


	// REQUETES

	public int[][] getList() {
		return list;
	}


	// COMMANDES

	public void generatePopulation(int nbRes) {
		for (int i = nbRes; i < nbIndividu; ++i) {
			for (int j = 0; j < nbIndividu; ++j) {
				list[i][j] = randomBit();
			}
		}
	}

	public int randomBit() {
		return (int)Math.round(Math.random());
	}
}
