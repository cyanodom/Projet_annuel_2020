package SteinerGraphe;

import java.util.Arrays;

import SteinerGraphe.Kruskal.Kruskal;

public class Genetique {

	// ATTRIBUTS

	private int complex;
	private StructFile struct;
	private Population population;
	private Kruskal kruskal;
	private Graph graph;


	// CONSTRUCTEURS

	public Genetique(StructFile struct, Graph graph) {
		this.struct = struct;
		this.complex = struct.getNbSommets() - struct.getNomSommetsT().length;
		this.population = new Population(complex);
		this.graph = graph;
	}


	// COMMANDES

	public void AlgoGene() {
		System.out.println("----------- GENERATIN POPULATION ----------");
		population.generatePopulation();
		System.out.println("------------ GENERATIN FINISHED -----------");

		for (int i = 0; i < complex; ++i) {
			Graph temp[] = graph[i];
		}

		for (int i = 0; i < complex; ++i) {
			for (int j = 0; j < complex; ++j) {
				if (population.getList()[i][j] == 0) {
					temp[k].removeNode(graph.getNode()[graph.getMaxTerminalNodeId() + j]);
				}
			}
		}

	}
}
