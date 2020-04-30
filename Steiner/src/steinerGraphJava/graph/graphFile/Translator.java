package steinerGraphJava.graph.graphFile;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.Node;
import util.Contract;

public class Translator implements ITranslator, Serializable {

	// CONSTRUCTEUR

	public Translator() {}

	// COMMANDES

	public void trans(File f, Graph graph){
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			// Premi�re Ligne
			String temp = br.readLine();
			int nbSommet = stringToInt(temp);
			if (nbSommet <= 0) {
				//TODO Stop le programme
				br.close();
				return;
			}
			
			// Deuxi�me Ligne
			String[] sommet = br.readLine().split(" ");
			
			if (nbSommet != sommet.length) {
				//TODO Erreur dans le fichier STOP
			}			
			
			// Troisi�me Ligne
			String[] sommetT = br.readLine().split(" ");
			if (nbSommet < sommetT.length) {
				//TODO Erreur dans le fichier STOP
			}
			
			String[] nomSommets = new String[nbSommet];
			int i;
			for (i = 0; i < sommetT.length; ++i) {
				graph.getUserAssociatedNodeNames().put(sommetT[i], new Node(i + 1));
				nomSommets[i] = sommetT[i];
			}
			for (int j = 0; j < sommet.length; j++) {
				if (!graph.getUserAssociatedNodeNames().containsKey(sommet[j])) {
					graph.getUserAssociatedNodeNames().put(sommet[j], new Node(i + 1));
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
	}
	
	// OUTILS 
	
	public int stringToInt(String car) {
		Contract.checkCondition(car != null);
		return Integer.parseInt(car);
	}
	
	public Node changeValueHash(String tempo, Hashtable<String, Node> hash) {
		return hash.get(tempo);
	}
}