package steinerGraphJava.algorithms.kruskal;

import java.util.LinkedList;

import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.IGraph;
import steinerGraphJava.graph.Node;

public class Kruskal {

	// ATTRIBUTS

	private IGraph graph;
	private Cycle cycle;
	private int length;
	private int arbre;


	// CONSTRUCTEUR
	
	/*
	 * L'algorithme de Kruskal
	 */
	public Kruskal (IGraph graph2) {
		this.graph = graph2;
		this.length = graph.getShape().size();
		this.cycle = new Cycle(this.graph);
		this.arbre = 1;
	}


	// REQUETES
	
	/*
	 * Permet de renvoyer le poids de l'arbre à la fin de l'algo de kruskal
	 */
	public int getLength() {
		return length;
	}
	
	/*
	 * Permet de renvoyer la pénalité de l'arbre à la fin de l'algo de kruskal
	 */
	public int getNbArbre() {
		return arbre;
	}

	// COMMANDES
	
	/*
	 * L'algorithme de Kruskal
	 */
	public LinkedList<Arc> kruskal() {
		int n = 0;
		// Initialisation de notre liste d'Arc
		LinkedList<Arc> kara = new LinkedList<Arc>();
		for (int i = 0; i < getLength(); i++) {
			kara.add(graph.getShape().get(i)); // Ajout d'un Arc
			// Recherche d'un cycle dans la nouvelle liste
			if (cycle.findCycle(kara, i + 1 - n)) { // Présence d'un cycle
				kara.removeLast();  // On retire l'Arc que l'on vient de rajouter
				++n;
			} else { // On garde l'Arc
				arbre = cycle.getNbArbre(); // On met à jour la pénalité avec le nouvelle arbre
			}
		}
		return kara;
	}
	
	/*
	 * Permet de remettre à zéro un Arc déjà initialisé
	 */
	public void vide(Arc kara) {
		kara.getNodes()[0] = new Node(0);
		kara.getNodes()[1] = new Node(0);
		kara.changeWeight(0);
	}
}
