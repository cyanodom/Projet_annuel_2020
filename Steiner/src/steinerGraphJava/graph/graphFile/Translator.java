package steinerGraphJava.graph.graphFile;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.IGraph;
import steinerGraphJava.graph.Node;
//import util.Contract;

public class Translator {

	public static IGraph trans(File f){
		IGraph graph = new Graph();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			// Premi�re Ligne
			String temp = br.readLine();
			int nbSommet = stringToInt(temp);
			if (nbSommet <= 0) {
				//TODO Stop le programme
				br.close();
				return graph;
			}
			
			// Deuxi�me Ligne
			String[] sommet = br.readLine().split(" ");
			
			if (nbSommet != sommet.length) {
				//TODO Erreur dans le fichier STOP
				return null;
			}			
			
			// Troisi�me Ligne
			String[] sommetT = br.readLine().split(" ");
			if (nbSommet < sommetT.length) {
				//TODO Erreur dans le fichier STOP
				return null;
			}
			
			String[] nomSommets = new String[nbSommet];
			int i;
			for (i = 0; i < sommetT.length; ++i) {
				graph.getUserAssociatedNodeNames().put(new Node(i + 1), sommetT[i]);
				nomSommets[i] = sommetT[i];
			}
			for (int j = 0; j < sommet.length; j++) {
				if (!graph.getUserAssociatedNodeNames().containsKey(sommet[j])) {
					graph.getUserAssociatedNodeNames().put(new Node(i + 1), sommet[j]);
					nomSommets[i] = sommet[j];
					++i;
				}
			}
			
			// Quatri�me et plus ligne	
			String line;
			while (((line = br.readLine()) != null)) {
				String[] tempo = new String[3];
				line.trim();
				tempo = line.split(",");
				tempo[0] = tempo[0].replace('(', ' ').trim();
				tempo[2] = tempo[2].replace(')', ' ').trim();
				Node node1 = changeValueHash(tempo[0], graph.getUserAssociatedNodeNames());
				Node node2 = changeValueHash(tempo[1], graph.getUserAssociatedNodeNames());
				int weight = stringToInt(tempo[2]);
				Arc arc = new Arc(node1, node2, weight);
				graph.getShape().add(arc);
			}
			
			Node[] nodeTab = new Node[nbSommet];
			for (int j = 0; j < nbSommet; j++) {
				nodeTab[j] = new Node(j + 1);	
			}
			
			graph.addData(sommetT.length, nodeTab);
			br.close();
		} catch (IOException e) {
			// TODO Afficher un message d'erreur et demander de rechoisir
		}
		return graph;
	}
	
	// OUTILS 
	
	public static int stringToInt(String car) {
		//Contract.checkCondition(car != null);
		return Integer.parseInt(car);
	}
	
	public static Node changeValueHash(String tempo, Hashtable<Node, String> hash) {
		for (Node n : hash.keySet()) {
			if (hash.get(n) == tempo) {
				return n;
			}
		}
		return null;
	}
}
