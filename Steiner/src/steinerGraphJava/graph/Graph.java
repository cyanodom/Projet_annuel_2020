package steinerGraphJava.graph;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;

import steinerGraphJava.graph.graphFile.Translator;

public class Graph implements IGraph {
	
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
	
	
	// COMMANDES
	
	@Override
	public void loadFile(File f, Graph graph) {
		translator.trans(f, graph);
	}
	
	@Override
	public void removeNode(Node n) {
		//TODO
	}
	
	@Override
	public Hashtable<String, Node> getUserAssociatedNodeNames() {
		return userAssociatedNodeNames;
	}
	
	@Override
	public void addData(int length, Node[] nodeTab) {
		maxTerminalNodeId = length;
		nodes = nodeTab;
	}
}
