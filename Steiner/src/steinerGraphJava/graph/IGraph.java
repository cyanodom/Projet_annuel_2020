package steinerGraphJava.graph;

import java.util.Hashtable;
import java.util.LinkedList;

import steinerGraphJava.graphics.PointTime;

public interface IGraph {
	Node[] getNodes();

	void removeNode(String n) throws GraphException;

	LinkedList<Arc> getShape();

	int getMaxTerminalNodeId();

	Hashtable<Node, String> getUserAssociatedNodeNames();

	String convertNodeToName(Node node);

	Node convertNameToNode(String node);

	void addData(int length, Node[] nodeTab);

	void addTerminalNode(String nodeName) throws GraphException;

	void addNode(String nodeName) throws GraphException;

	LinkedList<String> getIntersectionWith(Graph graph);

	void makeUnionWith(IGraph iGraph) throws GraphException;

	void addArc(String firstNode, String secondNode, int weight) throws GraphException;

	void removeRelatedArc(Node n);

	void renameNode(String nodeNameSource, String nodeNameDest) throws GraphException;

	Hashtable<String, PointTime[]> getListSucc();

	void makeRemove(IGraph trans);

	void empty();

	void removeArc(String firstNode, String secondNode) throws GraphException;

}
