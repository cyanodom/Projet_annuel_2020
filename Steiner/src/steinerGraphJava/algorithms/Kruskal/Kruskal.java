package steinerGraphJava.algorithms.Kruskal;

import java.util.LinkedList;

import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.Node;

public class Kruskal {

	// ATTRIBUTS

	private Graph graph;
	private Cycle cycle;
	private int length;
	private int arbre;


	// CONSTRUCTEUR

	public Kruskal (Graph temp) {
		this.graph = temp;
		this.length = graph.getShape().size();
		this.cycle = new Cycle(this.graph);
		this.arbre = 1;
	}


	// REQUETES

	public int getLength() {
		return length;
	}
	
	public int getNbArbre() {
		return arbre;
	}

	// COMMANDES

	public LinkedList<Arc> kruskal() {
		int n = 0;
		LinkedList<Arc> kara = new LinkedList<Arc>();
		for (int i = 0; i < getLength(); i++) {
			kara.add(graph.getShape().get(i));
			if (cycle.findCycle(kara, i + 1 - n)) {
				kara.removeLast();
				++n;
			} else {
				arbre = cycle.getNbArbre();
			}
		}
		return kara;
	}
	
	public void vide(Arc kara) {
		kara.getNodes()[0] = new Node(0);
		kara.getNodes()[1] = new Node(0);
		kara.changeWeight(0);
	}
}
