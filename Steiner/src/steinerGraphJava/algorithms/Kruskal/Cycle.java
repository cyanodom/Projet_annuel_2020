package steinerGraphJava.algorithms.Kruskal;


import java.util.Iterator;
import java.util.LinkedList;

import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;

public class Cycle {

	// ATTRIBUTS

	private int nb;
	private int length;
	private LinkedList<Integer> list[];
	private Graph graph;


	// CONSTRUCTEUR

	public Cycle(Graph graph) {
		this.length = graph.getShape().size();
		this.nb = graph.getNodes().length;
	}

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


	public Boolean findCycle(Arc[] kara, int l) {
		length = l;
		list = new LinkedList[nb];
		for (int i = 0; i < nb; ++i) {
			list[i] = new LinkedList<Integer>();
		}

		Boolean visite[] = new Boolean[nb];

//		Arc[] tab1 = trans(kara, l);
//
//		String[][] res = newTab();
//
//		transNewTab(tab1, res);

		createList(kara);

		for (int u = 0; u < nb; u++) {
			if (!visite[u]) { // Si tu ne l'as toujours pas visit�, alors
				if (searchCycle(u, visite, -1)) { // Rechercher depuis ce sommet
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * RES
	 */

//	public Arc[] trans(Arc[] kara, int i) {
//		Arc[] tab1 = new Arc[i];
//		for (int j = 0; j < i; j++) {
//			tab1[j] = kara[j];
//		}
//		return tab1;
//	}
//
//	public String[][] newTab() {
//		String[][] res = new String[nb][2];
//		for (int i = 0; i < nb; i++) {
//			res[i][0] = graph.getNomSommets()[i];
//			res[i][1] = Integer.toString(i + 1);
//		}
//		return res;
//	}
//
//	public void transNewTab(Arc[] tab1, String[][] res) {
//		for(int i = 0; i < length; i++) {
//			Node j = tab1[i].getNodes()[0];
//			Node k = tab1[i].getNodes()[1];
//			tab1[i].getNodes()[0] = passToNewTab(j, res);
//			tab1[i].getNodes()[1] = passToNewTab(k, res);
//		}
//	}
//
//	public Node passToNewTab(Node j, String[][] res) {
//		int k = 0;
//		while(!Integer.toString(j).equals(res[k][0])) {
//			k++;
//		}
//		return Integer.parseInt(res[k].[1]);
//	}

	/*
	 * TAB
	 */

	public void createList(Arc[] tab1) {
		for (int i = 0; i < length; i++) {
			addList(tab1[i]);
		}
	}

	public void addList(Arc tab1) {
		list[tab1.getNodes()[0].getName() - 1].add(tab1.getNodes()[1].getName());
		list[tab1.getNodes()[1].getName() - 1].add(tab1.getNodes()[0].getName());
	}


	// PRINT FUNCTIONS

	// INT
	public void printArray(int[][] js) {
		int n = js.length;
		for (int i=0; i<n; ++i) {
			System.out.print("(" + js[i][0] + "," + js[i][1] + "," + js[i][2] + ")");
			System.out.println();
		}
	}

	// STRING
	public void printArrayS(String[][] js) {
		int n = js.length;
		for (int i=0; i<n; ++i) {
			System.out.print("(" + js[i][0] + "," + js[i][1] + ")");
			System.out.println();
		}
	}
}
