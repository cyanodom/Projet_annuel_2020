package SteinerGraphe.Kruskal;

import java.util.Iterator;
import java.util.LinkedList;

import SteinerGraphe.StructFile;

public class Cycle {
	
	// ATTRIBUTS
	
	private int nb;
	private int length;
	private LinkedList<Integer> list[]; 
	private StructFile struct;
	
	
	// CONSTRUCTEUR

	public Cycle(int length, StructFile struct) {
		this.struct = struct;
		this.length = length;
		this.nb = struct.getNbSommets();
	}

	// A recursive function that uses visited[] and parent to detect 
	// cycle in subgraph reachable from vertex v. 
	public Boolean searchCycle(int v, Boolean visite[], int parent) {
		visite[v] = true;
		Integer i; 
		
		// Recur for all the vertices adjacent to this vertex 
		Iterator<Integer> it = list[v].iterator(); 
		while (it.hasNext()) { 
			i = it.next() - 1;
			// If an adjacent is not visited, then recur for that 
			// adjacent 
			if (!visite[i]) { 
				if (searchCycle(i, visite, v)) {
					return true;
				}
			}

			// If an adjacent is visited and not parent of current 
			// vertex, then there is a cycle. 
			else if (i != parent) {
				return true;
			}
		}
		return false; 
	} 

	// Returns true if the graph contains a cycle, else false. 
	public Boolean findCycle(int[][] tab, int l) { 
		length = l;
		list = new LinkedList[nb]; 
		for (int i = 0; i < nb; ++i) {
			list[i] = new LinkedList<Integer>();
		}
		// Mark all the vertices as not visited and not part of 
		// recursion stack
		Boolean visite[] = new Boolean[nb]; 
		for (int i = 0; i < nb; i++) {
			visite[i] = false;
		}
		
		int[][] tab1 = trans(tab, l);
		
		String[][] res = newTab();
		
		transNewTab(tab1, res);
		
		createList(tab1);
		
		for (int u = 0; u < nb; u++) {
			if (!visite[u]) { // Si tu ne l'as toujours pas visité, alors
				if (searchCycle(u, visite, -1)) { // Rechercher depuis de sommet
					return true;
				}
			}
		}
		return false; 
	}

	/*
	 * RES
	 */
	
	public int[][] trans(int[][] tab, int i) {
		int[][] tab1 = new int[i][2];
		for (int j = 0; j < i; j++) {
			tab1[j] = tab[j]; 
		}
		return tab1;
	}
	
	public String[][] newTab() {
		String[][] res = new String[nb][2];
		for (int i = 0; i < nb; i++) {
			res[i][0] = struct.getNomSommets()[i];
			res[i][1] = Integer.toString(i + 1);
		}
		return res;
	}	

	public void transNewTab(int[][]tab, String[][] res) {
		for(int i = 0; i < length; i++) {
			int j = tab[i][0];
			int k = tab[i][1];
			tab[i][0] = passToNewTab(j, res);
			tab[i][1] = passToNewTab(k, res);
		}
	}
	
	public int passToNewTab(int i, String[][] res) {
		int k = 0;
		while(!Integer.toString(i).equals(res[k][0])) {
			k++;
		}
		return Integer.parseInt(res[k][1]);
	}

	/*
	 * TAB
	 */
	
	public void createList(int[][] tab) {
		for (int i = 0; i < length; i++) {
			addList(tab[i]);
		}
	}
	
	public void addList(int[] tab) {
		list[tab[0] - 1].add(tab[1]);
		list[tab[1] - 1].add(tab[0]);
	}
	
	
	/* A utility function to print array of size n */
	public void printArray(int[][] js) { 
		int n = js.length; 
		for (int i=0; i<n; ++i) { 
			System.out.print("(" + js[i][0] + "," + js[i][1] + "," + js[i][2] + ")"); 
			System.out.println(); 
		}
	}
	
	public void printArrayS(String[][] js) { 
		int n = js.length; 
		for (int i=0; i<n; ++i) { 
			System.out.print("(" + js[i][0] + "," + js[i][1] + ")"); 
			System.out.println(); 
		}
	}
}
