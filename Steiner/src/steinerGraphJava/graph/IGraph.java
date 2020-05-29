package steinerGraphJava.graph;

import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;

import steinerGraphJava.graphics.PointTime;

public interface IGraph {
	public Node[] getNodes();
	
	public void removeNode(String n) throws GraphException;
	
	public LinkedList<Arc> getShape();
	
	public int getMaxTerminalNodeId();
	
	public Hashtable<Node, String> getUserAssociatedNodeNames();

	public String convertNodeToName(Node node);

	public Node convertNameToNode(String node);

	void addData(int length, Node[] nodeTab);

	void addTerminalNode(String nodeName) throws GraphException;

	void addNode(String nodeName) throws GraphException;

	LinkedList<String> getIntersectionWith(Graph graph);

	void makeUnionWith(IGraph iGraph) throws GraphException;

	void addArc(String firstNode, String secondNode, int weight);

	void removeRelatedArc(Node n);

	void renameNode(String nodeNameSource, String nodeNameDest) throws GraphException;

	Hashtable<String, PointTime[]> getListSucc();
}
