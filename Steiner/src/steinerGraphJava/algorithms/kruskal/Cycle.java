package steinerGraphJava.algorithms.kruskal;


import java.util.Iterator;
import java.util.LinkedList;

import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.IGraph;

public class Cycle {

	// ATTRIBUTS

	private int nb;
	private int length;
	private LinkedList<Integer> list[];
	private int nbArbre;


	// CONSTRUCTEUR
	
	// L'algorithme qui permet de faire la recherche de Cycle
	public Cycle(IGraph graph) {
		this.length = 0;
		this.nb = graph.getNodes().length;
		nbArbre = 0;
	}

	// REQUETES
	
	/*
	 * Permet de récupérer le nombre d'arbre dans le dernier passage de findCycle
	 */
	public int getNbArbre() {
		return nbArbre;
	}
	
	
	// COMMANDES
	
	/*
	 * Utile pour findSearch
	 */
	public Boolean searchCycle(int v, Boolean visite[], int parent) {
		visite[v] = true;
		Integer i;

		Iterator<Integer> it = list[v].iterator();
		while (it.hasNext()) { // recherche sur tous les suivants
			i = it.next() - 1;

			if (!visite[i]) {
				if (searchCycle(i, visite, v)) { // recherche plus en profondeur
					return true;
				}
			}

			else if (i != parent) {
				return true;
			}
		}
		return false;
	}

	/*
	 * La fonction de recherche de cycle dans un graph grâce à une liste d'Arcs
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Boolean findCycle(LinkedList<Arc> kara, int l) {
		length = l;
		list = new LinkedList[nb];
		Boolean visite[] = new Boolean[nb];
		for (int i = 0; i < nb; ++i) {
			list[i] = new LinkedList<Integer>();
			visite[i] = false;
		}

		nbArbre = 0; // remise à 0 de la pénalité
		
		createList(kara);

		for (int u = 0; u < nb; u++) { // Passage sur tous les sommets 
			if (!visite[u]) {
				++nbArbre; // Ajout d'une pénalité si plusieurs arbres
				if (searchCycle(u, visite, -1)) { // Rechercher depuis ce sommet
					return true;
				}
			}
		}
		return false;
	}

	
	// OUTILS 
	
	/*
	 * Permet de rajouter tous les arcs dans "list"
	 */
	public void createList(LinkedList<Arc> kara) {
		for (int i = 0; i < length; i++) {
			addList(kara.get(i));
		}
	}
	
	/*
	 * Permet d'ajouter à "list" un Arc
	 */
	public void addList(Arc tab1) {
		list[tab1.getNodes()[0].getName() - 1].add(tab1.getNodes()[1].getName());
		list[tab1.getNodes()[1].getName() - 1].add(tab1.getNodes()[0].getName());
	}
}
