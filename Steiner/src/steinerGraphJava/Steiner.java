package steinerGraphJava;

import java.io.File;
import java.util.LinkedList;

import javax.swing.SwingUtilities;

import steinerGraphJava.algorithms.Genetique;
import steinerGraphJava.algorithms.Population.PoidsPenality;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;

public class Steiner {

	// ATTRIBUTS

	private File file;

	private Genetique gene;
	
	private Graph graph;
	
	private PoidsPenality res;


	// CONSTRUCTEUR

	public Steiner () {
		runAlgo();
	}


	// COMMANDES

	public void runAlgo() {
		file = new File("C:\\Users\\tisda\\AppData\\Local\\Packages\\CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\\LocalState\\rootfs\\home\\tisdam\\Projet_annuel_2020\\Steiner\\misc\\text.txt");

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
		
		// True = elitiste / false = generationnel
		gene = new Genetique(graph, true);
		gene.algoGene();
		
		res = gene.getRes();
		
		
		System.out.println("\nOn sort de l'algo génétique en ayant obtenue les valeurs : ");
		System.out.println("Poids " + ": " + res.getPoids() + " et la Penality : " + res.getPenality());
		System.out.println("Les arcs restants sont :");
		printArray(res.getArc());
	}
	
	// OUTILS
	
	public void printArray(LinkedList<Arc> res) {
		System.out.println("Début Print du tab");
		for (int i = 0; i < res.size(); ++i) {
			System.out.println("(" + res.get(i).getNodes()[0].getName() + "," 
								   + res.get(i).getNodes()[1].getName() + ","
								   + res.get(i).getWeight() + ")");
		}
		System.out.println("Fin Print du tab\n");
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
