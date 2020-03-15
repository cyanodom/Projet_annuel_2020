package steinerGraphJava.algorithms;

import steinerGraphJava.algorithms.Kruskal.Kruskal;
import steinerGraphJava.graph.Graph;

public class Genetique {

	// ATTRIBUTS

	private int complex;
	private Population population;
	private Graph graph;


	// CONSTRUCTEURS

	public Genetique(Graph graph) {
		this.complex = graph.getMaxTerminalNodeId();
		this.population = new Population(complex);
		this.graph = graph;
	}


	// COMMANDES

	public void AlgoGene() {
		System.out.println("----------- GENERATIN POPULATION ----------");
		population.generatePopulation();
		System.out.println("------------ GENERATIN FINISHED -----------");
		
		Graph temp[] = null;
		
		for (int i = 0; i < complex; ++i) {
			temp[i] = graph;
		}

		for (int i = 0; i < complex; ++i) {
			for (int j = 0; j < complex; ++j) {
				if (population.getList()[i][j] == 0) {
					temp[i].removeNode(graph.getNodes()[graph.getMaxTerminalNodeId() + j]);
				}
			}
			// FAIRE KRUSKAL SUR temp[i]
			
			Kruskal tempKruskal = new Kruskal(temp[i]);
			tempKruskal.kruskal();
		}
	}
}
