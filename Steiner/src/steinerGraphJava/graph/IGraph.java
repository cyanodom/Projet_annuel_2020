package steinerGraphJava.graph;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;

public interface IGraph {
	public Node[] getNodes();
	
	public void removeNode(Node n);
	
	public LinkedList<Arc> getShape();
	
	public int getMaxTerminalNodeId();
	
	public void loadFile(File f, Graph graph);
	
	public Hashtable<String, Node> getUserAssociatedNodeNames();

	void addData(int length, Node[] nodeTab);
}
