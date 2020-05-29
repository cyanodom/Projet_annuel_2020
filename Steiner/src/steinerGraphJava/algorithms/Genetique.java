package steinerGraphJava.algorithms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import steinerGraphJava.algorithms.kruskal.Kruskal;
import steinerGraphJava.algorithms.population.PoidsPenality;
import steinerGraphJava.algorithms.population.Population;
import steinerGraphJava.algorithms.population.QuickSort;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.IGraph;

public class Genetique {

	// ATTRIBUTS

	private int complex;
	private Population population;
	private IGraph graph;
	private PoidsPenality[] res;
	private boolean remplacement;
	private QuickSort quicksort;
	

	// CONSTRUCTEURS

	public Genetique(IGraph graph2, boolean remplacement) {
		this.complex = graph2.getNodes().length - graph2.getMaxTerminalNodeId();
		this.population = new Population(complex);
		this.graph = graph2;
		this.remplacement = remplacement;
		this.res = new PoidsPenality[complex];
		for (int i = 0; i < complex; ++i) {
			this.res[i] = new PoidsPenality(null, 0, 0);
		}
		this.quicksort = new QuickSort();
	}


	// COMMANDES

	public void algoGene() {
		switch(graph.getMaxTerminalNodeId()) {
		case 1 :
			System.out.println("Arbre Final : " + graph.getNodes()[0]);
			System.out.println("Poids de 0");
			break;
		case 2 :
			// DIJKSTRA
			break;
		default :
			if (graph.getShape().size() == graph.getMaxTerminalNodeId()) {
				// SI S = T
				Kruskal kruskal = new Kruskal(graph);
				kruskal.kruskal();
				System.out.println("Le poids de notre arbre est : "); // TODO finir poids
			} else {
				algoGeneNormal(0); // Aucun élément à garder
				for (int i = 0; i < complex - 1; ++i) {
					if (remplacement) {
						// do Elitiste Algo
						algoGeneNormal(1); // On garde un élément
					} else {
						// do Gerenationnel Algo
						algoGeneNormal((complex - (complex%2))/2);
					}
				}
			}
			break;
		}
	}
	
	public PoidsPenality getRes() {
		return res[0];
	}
	
	public void algoGeneNormal(int nbRes) {
		population.generatePopulation(nbRes);

		trierListArc(graph.getShape());
		
		
		IGraph[] temp = new Graph[complex-nbRes];
		
		for (int i = 0; i < complex - nbRes; ++i) {
			try {
				temp[i] = newObject(graph);
			} catch (ClassNotFoundException e) {
				// TODO IHM MISSING CLASS, SPOILED APPLI, TRY DOWNLOADING AGAIN
				e.printStackTrace();
			} catch (IOException e) {
				// TODO ERROR DURING CONVERTION, TRY AGAIN 
				e.printStackTrace();
			}
		}
		
		for (int i = nbRes; i < complex; ++i) {
			res[i].changePoids(0);
			res[i].changePenality(0);
			for (int j = 0; j < complex; ++j) {
				if (population.getList()[i][j] == 0) {
					res[i].addPenality(-1); 
					temp[i-nbRes].removeRelatedArc(graph.getNodes()[graph.getMaxTerminalNodeId() + j]);
				}
			}
			
			Kruskal tempKruskal = new Kruskal(temp[i - nbRes]);
			res[i].changeArc(tempKruskal.kruskal());
			
			res[i].addPenality(tempKruskal.getNbArbre());
		}
		
		additionner(nbRes);
		quicksort.sortPoids(res, 0, complex - 1);
	}

	
	// OUTILS
	
	public void additionner(int nbRes) {
		for (int j = nbRes; j < res.length; ++j) {
			for (int i = 0; i < res[j].getArc().size(); ++i) {
				res[j].addPoids(res[j].getArc().get(i).getWeight());  
			}	
		}
	}
	
	public void trierListArc(LinkedList<Arc> arc) {
		Collections.sort(arc, new SortPerso());
	}
	
	public IGraph newObject(IGraph graph2) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(graph2);
		oos.flush();
		oos.close();
		bos.close();
		byte[] byteData = bos.toByteArray();

		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
		graph2 = (IGraph) new ObjectInputStream(bais).readObject();
		return graph2;
	}
	
	// Sort perso (possibilité d'utiliser le quicksort dans la class QuickSort)
	
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

	public class SortPersoPenality implements Comparator<PoidsPenality> {
	    @Override
	    public int compare(PoidsPenality e1, PoidsPenality e2) {
	        if(e1.getPenality() > e2.getPenality()){
	            return 1;
	        } else {
	            return -1;
	        }
	    }
	}
	
	public class SortPersoPoids implements Comparator<PoidsPenality> {
	    @Override
	    public int compare(PoidsPenality e1, PoidsPenality e2) {
	        if(e1.getPoids() < e2.getPoids()){
	            return 1;
	        } else {
	            return -1;
	        }
	    }
	}
	
	
	// TEST
	
	@SuppressWarnings("unused")
	private void printPoids() {
		for (int i = 0; i < res.length; ++i) {
			System.out.println("Poids " + i + " :" + res[i].getPoids() + " et la Penality : " + res[i].getPenality());
		}
	}
}
