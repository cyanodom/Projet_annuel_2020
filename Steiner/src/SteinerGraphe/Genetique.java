package SteinerGraphe;

import java.util.Arrays;

import SteinerGraphe.Kruskal.Kruskal;

public class Genetique {
	
	// ATTRIBUTS
	
	private int compil;
	private StructFile struct;
	private Population population;
	private Kruskal kruskal;
	
	// CONSTRUCTEURS
	
	public Genetique(StructFile struct) {
		this.struct = struct;
		this.compil = struct.getNomSommets().length - struct.getNomSommetsT().length;
		this.population = new Population(compil);
	}
	
	
	// COMMANDES
	
	public void AlgoGene() {
		System.out.println("----------- GENERATIN POPULATION ----------");
		population.generatePopulation();
		System.out.println("------------ GENERATIN FINISHED -----------");
		
//		for (int i = 0; i < compil; i++) {
//			int[][] res = createV();
//		}
	}
	
//	public int[][] createV() {
//		Arrays.sort(struct.getNomSommets());
//		Arrays.sort(struct.getNomSommetsT());
//		int[][] res = ;
//		int n = 0;
//		for (int i = 0; i < struct.getNbSommets(); i++) {
//			if (!(struct.getNomSommets()[i].equals(struct.getNomSommetsT()[i - n]))) {
//				int k = Integer.parseInt(struct.getNomSommets()[i]);
//				++n;
//			}
//		}
//	}
}
