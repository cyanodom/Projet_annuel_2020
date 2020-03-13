package SteinerGraphe;

import java.util.Arrays;

import SteinerGraphe.Kruskal.Kruskal;

public class Genetique {
	
	// ATTRIBUTS
	
	private int complex;
	private StructFile struct;
	private Population population;
	private Kruskal kruskal;
	
	// CONSTRUCTEURS
	
	public Genetique(StructFile struct) {
		this.struct = struct;
		this.complex = struct.getNbSommets() - struct.getNomSommetsT().length;
		this.population = new Population(complex);
	}
	
	
	// COMMANDES
	
	public void AlgoGene() {
		System.out.println("----------- GENERATIN POPULATION ----------");
		population.generatePopulation();
		System.out.println("------------ GENERATIN FINISHED -----------");
		
		String[] res = createV();
	}
	
	public String[] createV() {
		Arrays.sort(struct.getNomSommets());
		Arrays.sort(struct.getNomSommetsT());
		String[] res = new String[complex];
		int n = 0;
		for (int i = 0; i < struct.getNbSommets(); i++) {
			if (!(struct.getNomSommets()[i].equals(struct.getNomSommetsT()[i - n]))) {
				int k = Integer.parseInt(struct.getNomSommets()[i]);
				++n;
			}
		}
		return res;
	}
}
