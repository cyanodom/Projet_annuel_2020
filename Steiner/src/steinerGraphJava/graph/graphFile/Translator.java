package steinerGraphJava.graph.graphFile;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.IGraph;
import steinerGraphJava.graph.Node;

public class Translator {

	public static IGraph readGraph(File f) throws GraphFileException{
		IGraph graph = new Graph();
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String[] sommet = br.readLine().split(" ");		

			String[] sommetT = br.readLine().split(" ");
			if (sommet.length < sommetT.length) {
				throw new GraphFileException();
			}
			
			int i;
			for (i = 0; i < sommetT.length; ++i) {
				graph.getUserAssociatedNodeNames().put(new Node(i + 1), sommetT[i]);
			}
			for (int j = 0; j < sommet.length; j++) {
				if (!graph.getUserAssociatedNodeNames().containsValue(sommet[j])) {
					graph.getUserAssociatedNodeNames().put(new Node(i + 1), sommet[j]);
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
			
			Node[] nodeTab = new Node[sommet.length];
			for (int j = 0; j < sommet.length; j++) {
				nodeTab[j] = new Node(j + 1);	
			}
			
			graph.addData(sommetT.length, nodeTab);
			
			br.close();
		} catch (IOException e) {
			throw new GraphFileException();
		}
		return graph;
	}
	
	public static void writeGraph(IGraph graph, File file) throws GraphFileException {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			boolean first = true;
			for (Node n : graph.getNodes()) {
				if (first) {
					first = false;
				} else {
					out.write(' ');
				}
				out.write(graph.convertNodeToName(n));
			}
			first = true;
			out.write('\n');
			for (int i = 0; i <= graph.getMaxTerminalNodeId(); ++i) {
				if (first) {
					first = false;
				} else {
					out.write(' ');
				}
				out.write(graph.convertNodeToName(graph.getNodes()[i]));
			}
			for (Arc a : graph.getShape()) {
				out.write('\n');
				out.write('(');
				out.write(graph.convertNodeToName(a.getNodes()[0]));
				out.write(',');
				out.write(graph.convertNodeToName(a.getNodes()[1]));
				out.write(',');
				out.write(Integer.toString(a.getWeight()));
				out.write(')');
			}
			out.close();
		} catch (IOException e) {
			throw new GraphFileException();
		}
	}
	
	// OUTILS 
	
	public static int stringToInt(String car) {
		return Integer.parseInt(car);
	}
	
	public static Node changeValueHash(String tempo, Hashtable<Node, String> hash) {
		for (Node n : hash.keySet()) {
			if (hash.get(n).equals(tempo)) {
				return n;
			}
		}
		return null;
	}
}
