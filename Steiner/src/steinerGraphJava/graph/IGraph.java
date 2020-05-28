package steinerGraphJava.graph;

import java.io.File;
import java.util.Hashtable;

public interface IGraph {
	public Node[] getNodes();
	
	public void removeNode(Node n);
	
	public Arc[] getShape();
	
	public int getMaxTerminalNodeId();
	
	public void loadFile(File f);
	
	public Hashtable<Node, String> getUserAssociatedNodeNames();

	public String convertNodeToName(Node node);

	public Node convertNameToNode(String node);
}
