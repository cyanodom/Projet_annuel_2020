package steinerGraphJava.algorithms.kruskal;


import java.util.Iterator;
import java.util.LinkedList;

import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;

public class Cycle {

	// ATTRIBUTS

	private int nb;
	private int length;
	private LinkedList<Integer> list[];
	private int nbArbre;


	// CONSTRUCTEUR

	public Cycle(Graph graph) {
		this.length = 0;
		this.nb = graph.getNodes().length;
		nbArbre = 0;
	}

	// REQUETES
	
	public int getNbArbre() {
		return nbArbre;
	}
	
	
	// COMMANDES
	
	public Boolean searchCycle(int v, Boolean visite[], int parent) {
		visite[v] = true;
		Integer i;

		Iterator<Integer> it = list[v].iterator();
		while (it.hasNext()) {
			i = it.next() - 1;

			if (!visite[i]) {
				if (searchCycle(i, visite, v)) {
					return true;
				}
			}

			else if (i != parent) {
				return true;
			}
		}
		return false;
	}


	public Boolean findCycle(LinkedList<Arc> kara, int l) {
		length = l;
		list = new LinkedList[nb];
		Boolean visite[] = new Boolean[nb];
		for (int i = 0; i < nb; ++i) {
			list[i] = new LinkedList<Integer>();
			visite[i] = false;
		}

		nbArbre = 0;
		
		createList(kara);

		for (int u = 0; u < nb; u++) {
			if (!visite[u]) {
				++nbArbre;
				if (searchCycle(u, visite, -1)) { // Rechercher depuis ce sommet
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * TAB
	 */

	public void createList(LinkedList<Arc> kara) {
		for (int i = 0; i < length; i++) {
			addList(kara.get(i));
		}
	}

	public void addList(Arc tab1) {
		list[tab1.getNodes()[0].getName() - 1].add(tab1.getNodes()[1].getName());
		list[tab1.getNodes()[1].getName() - 1].add(tab1.getNodes()[0].getName());
	}
}