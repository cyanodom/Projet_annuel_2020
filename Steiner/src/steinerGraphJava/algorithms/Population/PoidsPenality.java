package steinerGraphJava.algorithms.Population;

import java.util.LinkedList;

import steinerGraphJava.graph.Arc;

public class PoidsPenality {
	
	// ATTRIBUTS
	
	private int poids;
	private int penality;
	private LinkedList<Arc> arc;
	
	
	// CONSTRUCTEUR
	
	public PoidsPenality (LinkedList<Arc> arc, int poids, int penality) { 
		this.poids = poids;
		this.penality = penality;
		this.arc = arc;
	}
	
	
	// REQUETES 
	
	public int getPoids() {
		return poids;
	}
	
	public int getPenality() {
		return penality;
	}
	
	public LinkedList<Arc> getArc() {
		return arc;
	}
	
	// COMMANDES
	
	public void changePoids(int newPoids) {
		poids = newPoids;
	}
	
	public void changePenality(int newPenality) {
		penality = newPenality;
	}
	
	public void changeArc(LinkedList<Arc> newArc) {
		arc = newArc;
	}
	
	public void addPoids(int addPoids) {
		poids += addPoids;
	}
	
	public void addPenality(int addPenality) {
		penality += addPenality;
	}
}
