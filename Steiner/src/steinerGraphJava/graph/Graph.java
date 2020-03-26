package steinerGraphJava.graph;

import java.io.File;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedList;

import steinerGraphJava.graph.graphFile.Translator;

public class Graph implements IGraph, Serializable {
	
	// ATTRIBUTS
	
	private int maxTerminalNodeId;
	private LinkedList<Arc> shape;
	private Node[] nodes;
	private Hashtable<String, Node> userAssociatedNodeNames;
	
	private Translator translator;
	
	// CONSTRUCTEUR
	
	public Graph() {
		translator = new Translator();
		userAssociatedNodeNames = new Hashtable<String, Node>();
		maxTerminalNodeId = 0;
		shape = new LinkedList<Arc>();
	}
	
	
	// REQUETES
	
	@Override
	public Node[] getNodes() {
		return nodes;
	}
	
	@Override
	public LinkedList<Arc> getShape() {
		return shape;
	}
	
	@Override
	public int getMaxTerminalNodeId() {
		return maxTerminalNodeId;
	}
	
	@Override
	public Hashtable<String, Node> getUserAssociatedNodeNames() {
		return userAssociatedNodeNames;
	}
	
	
	// COMMANDES
	
	@Override
	public void loadFile(File f, Graph graph) {
		translator.trans(f, graph);
	}
	
	@Override
	public void removeNode(Node n) {
		int j = 0;
		for (int i = 0; i < shape.size(); ++i) {
			if (shape.get(i - j).getNodes()[0].getName() == n.getName() || shape.get(i - j).getNodes()[1].getName() == n.getName()) {
				shape.remove(i - j);
				j++;
			}
		}
	}
	
	@Override
	public void addData(int length, Node[] nodeTab) {
		maxTerminalNodeId = length;
		nodes = nodeTab;
	}
	
	public void addHash(Hashtable<String, Node> newHash) {
		userAssociatedNodeNames = newHash;
	}
	
	public void changeShape(LinkedList<Arc> shape) {
		this.shape = shape;
	}
}
