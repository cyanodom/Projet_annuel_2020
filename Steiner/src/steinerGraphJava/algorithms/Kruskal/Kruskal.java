package steinerGraphJava.algorithms.Kruskal;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.Node;

public class Kruskal {

	// ATTRIBUTS

	private Graph graph;
	private QuickSort quickSort;
	private Cycle cycle;
	private int length;


	// CONSTRUCTEUR

	public Kruskal (Graph temp) {
		this.graph = temp;
		this.quickSort = new QuickSort();
		this.length = graph.getShape().size();
		this.cycle = new Cycle(this.graph);
	}


	// REQUETES

	public int getLength() {
		return length;
	}


	// COMMANDES

	public void trierList() {
		Collections.sort(graph.getShape(), new SortPerso());
	}

	public LinkedList<Arc> kruskal() {
		int n = 0; // Controle pour ne pas avoir des tailles trop grandes de tableau avec des cases vides
		trierList();
		quickSort.printArray(graph.getShape());
		LinkedList<Arc> kara = new LinkedList<Arc>();
		for (int i = 0; i < getLength(); i++) {
			kara.add(graph.getShape().get(i));
			if (cycle.findCycle(kara, i + 1 - n)) {
				kara.removeLast();
				++n;
			}
		}
		return kara;
	}

	public void vide(Arc kara) {
		kara.getNodes()[0] = new Node(0);
		kara.getNodes()[1] = new Node(0);
		kara.changeWeight(0);
	}
	
	// Class SortPerso
	
	public class SortPerso implements Comparator<Arc> {
	    @Override
	    public int compare(Arc e1, Arc e2) {
	        if(e1.getWeight() > e2.getWeight()){
	            return 1;
	        } else {
	            return -1;
	        }
	    }
	}
}
