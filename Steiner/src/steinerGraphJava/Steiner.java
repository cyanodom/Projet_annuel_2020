package steinerGraphJava;

import java.io.File;
import javax.swing.SwingUtilities;

import steinerGraphJava.algorithms.Genetique;
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


		// PARTIE Genetique
		
		gene = new Genetique(graph);
		gene.algoGene();
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
