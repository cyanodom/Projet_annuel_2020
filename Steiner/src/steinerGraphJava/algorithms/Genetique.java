package steinerGraphJava.algorithms;

import java.util.LinkedList;

import steinerGraphJava.algorithms.Kruskal.Kruskal;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;

public class Genetique {

	// ATTRIBUTS

	private int complex;
	private Population population;
	private Graph graph;


	// CONSTRUCTEURS

	public Genetique(Graph graph) {
		this.complex = graph.getNodes().length - graph.getMaxTerminalNodeId();
		this.population = new Population(complex);
		this.graph = graph;
	}


	// COMMANDES

	public void algoGene() {
		switch(graph.getMaxTerminalNodeId()) {
		case 1 :
			System.out.println("Arbre Final : " + graph.getNodes()[0]);
			System.out.println("Poids de 0");
			break;
		case 2 :
			// DIJKSTRA
			break;
		default :
			if (graph.getShape().size() == graph.getMaxTerminalNodeId()) {
				// SI S = T
				Kruskal kruskal = new Kruskal(graph);
				LinkedList<Arc> tab = kruskal.kruskal();
				for (int i = 0; i < tab.size(); ++i) {
					System.out.println("(" + tab.get(i).getNodes()[0].getName() + "," 
										   + tab.get(i).getNodes()[1].getName() + ","
										   + tab.get(i).getWeight() + ")");
				}
			} else {
				algoGeneNormal();
			}
			break;
		}
	}
	
	public void algoGeneNormal() {
		System.out.println("----------- GENERATIN POPULATION ----------");
		population.generatePopulation();
		System.out.println("------------ GENERATIN FINISHED -----------");
		
		Graph temp[] = new Graph[complex];
		
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
			LinkedList<Arc> arcTab = tempKruskal.kruskal();
			for (int k = 0; k < arcTab.size(); ++k) {
				System.out.println("(" + arcTab.get(k).getNodes()[0].getName() + "," 
									   + arcTab.get(k).getNodes()[1].getName() + ","
									   + arcTab.get(k).getWeight() + ")");
			}
		}
	}
}
