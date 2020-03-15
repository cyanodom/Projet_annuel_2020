package steinerGraphJava;

import java.io.File;
import javax.swing.SwingUtilities;

import steinerGraphJava.algorithms.Genetique;
import steinerGraphJava.algorithms.Kruskal.Kruskal;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;

public class Steiner {

	// ATTRIBUTS

	private File file;

	private Genetique gene;
	
	private Graph graph;


	// CONSTRUCTEUR

	public Steiner () {
		runAlgo();
	}


	// COMMANDES

	public void runAlgo() {
		file = new File("C:\\Users\\tisda\\eclipse-workspace2\\Steiner\\src\\SteinerGraphe\\text.txt");

		// NEW PARTIE LOAD
		
		graph = new Graph();
		graph.loadFile(file,graph);
		
		// ============ TEST ==============
		System.out.println("Nb Terminal : " + graph.getMaxTerminalNodeId());
		
		System.out.println("Tous les Sommets :");
		for (int i = 0; i < graph.getNodes().length; i++) {
			System.out.println(graph.getNodes()[i].getName());
		}

		System.out.println();
		
		System.out.println("Tous les sommets Terminaux :");
		for (int i = 0; i < graph.getMaxTerminalNodeId(); i++) {
			System.out.println(graph.getNodes()[i].getName());
		}
		
		System.out.println();
		
		System.out.println("Tous les Arcs :");
		for (int i = 0; i < graph.getShape().size(); ++i) {
			System.out.println("(" + graph.getShape().get(i).getNodes()[0].getName() + "," 
								   + graph.getShape().get(i).getNodes()[1].getName() + ","
								   + graph.getShape().get(i).getWeight() + ")");
		}
		
		
		
		
		
		
		
		
		
		// ============ TEST ==============
		
		// PARTIE HashTable

		/*
		 * Faire la HashTable
		 */

		// PARTIE g�n�tique
//
//		switch(graph.getMaxTerminalNodeId()) {
//		case 1 :
//			System.out.println("Arbre Final : " + graph.getNodes()[0]);
//			System.out.println("Poids de 0");
//			break;
//		case 2 :
//			// DIJKSTRA
//			break;
//		default :
//			if (graph.getShape().length == graph.getMaxTerminalNodeId()) {
//				// SI S = T
//				Kruskal kruskal = new Kruskal(graph);
//				Arc[] tab = kruskal.kruskal();
//			} else {
//				gene = new Genetique(graph);
//				gene.AlgoGene();
//			}
//			break;
//		}
	}

	// MAIN

	public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Steiner();
            }
        });
    }
}
