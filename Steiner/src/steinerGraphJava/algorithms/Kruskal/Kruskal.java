package steinerGraphJava.algorithms.Kruskal;

import java.util.LinkedList;

import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.Node;

public class Kruskal {
	// CONSTANTE

	private final static int NB_ELEMENT = 2;

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

	public LinkedList<Arc> trierList(int pos) {
		return quickSort.sort(graph.getShape(), 0, getLength() - 4);
	}

	public Arc[] kruskal() {
		int n = 0; // Controle pour ne pas avoir des tailles trop grandes de tableau avec des cases vides
		LinkedList<Arc> res = trierList(NB_ELEMENT);
//		quickSort.printArray(res);
		Arc kara[] = new Arc[getLength()];
		for (int i = 0; i < getLength(); i++) {
			kara[i + n] = res[i];
			System.out.println(kara[i + n].getNodes()[0] + "," + kara[i + n].getNodes()[1] + "," + kara[i + n].getWeight());
			if (cycle.findCycle(kara, i + 1 + n)) {
				System.out.println("CYCLE");
				vide(kara[i + n]);
				n--;
			} else {
				System.out.println("Not a CYCLE");
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
