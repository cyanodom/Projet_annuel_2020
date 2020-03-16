package steinerGraphJava.graph;

import java.io.File;
import java.util.Hashtable;

public class Graph implements IGraph {
	
	private int maxTerminalNodeId;
	private Arc[] shape;
	private Node[] nodes;
	private Hashtable<Node, String> userAssociatedNodeNames;
	
	public Graph() {}
	
	@Override
	public Node[] getNodes() {
		return nodes;
	}
	
	@Override
	public void removeNode(Node n) {
		//TODO
	}
	
	@Override
	public Arc[] getShape() {
		return shape;
	}
	
	@Override
	public int getMaxTerminalNodeId() {
		return maxTerminalNodeId;
	}
	
	@Override
	public void loadFile(File f) {
		//TODO
	}
	
	@Override
	public Hashtable<Node, String> getUserAssociatedNodeNames() {
		return userAssociatedNodeNames;
	}
	
}
