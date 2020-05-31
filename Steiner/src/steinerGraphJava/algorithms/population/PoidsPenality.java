package steinerGraphJava.algorithms.population;

import java.util.LinkedList;

import steinerGraphJava.graph.Arc;

public class PoidsPenality {
	
	// ATTRIBUTS
	
	private int poids;
	private int penality;
	private LinkedList<Arc> arc;
	
	
	// CONSTRUCTEUR
	
	/*
	 * Permet de cr�er un PoidsPenality, c'est � dire un Objet qui contient les r�sultats pour un groupe d'individus
	 * 
	 * poids : la somme de tous les poids de chaques Arcs
	 * penality : le nombre d'arbres
	 * arc : la liste de tous les arcs de ce groupe
	 */
	public PoidsPenality (LinkedList<Arc> arc, int poids, int penality) { 
		this.poids = poids;
		this.penality = penality;
		this.arc = arc;
	}
	
	
	// REQUETES 
	
	/*
	 * Permet de r�cup�rer le poids de ce groupe
	 */
	public int getPoids() {
		return poids;
	}
	
	/*
	 * Permet de r�cup�rer la p�nalit� de ce groupe
	 */
	public int getPenality() {
		return penality;
	}
	
	/*
	 * Permet de r�cup�rer la liste d'Arc de ce groupe
	 */
	public LinkedList<Arc> getArc() {
		return arc;
	}
	
	// COMMANDES
	
	/*
	 * Change le poids de ce groupe avec un nouveau
	 */
	public void changePoids(int newPoids) {
		poids = newPoids;
	}
	
	/*
	 * Change la p�nalit�e de ce groupe avec une nouvelle
	 */
	public void changePenality(int newPenality) {
		penality = newPenality;
	}
	
	/*
	 * Change la liste d'Arc de ce groupe avec une nouvelle
	 */
	public void changeArc(LinkedList<Arc> newArc) {
		arc = newArc;
	}
	
	/*
	 * Permet de rajouter un poids au poids actuel
	 */
	public void addPoids(int addPoids) {
		poids += addPoids;
	}
	
	/*
	 * Permet de rajouter une p�nalit�e � la p�nalit�e actuelle
	 */
	public void addPenality(int addPenality) {
		penality += addPenality;
	}
}
