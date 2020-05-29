package steinerGraphJava.graph;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedList;

import steinerGraphJava.graphics.PointTime;

public class Graph implements IGraph, Serializable {
	
	// ATTRIBUTS
	
	private int maxTerminalNodeId;
	private LinkedList<Arc> shape;
	private Node[] nodes;
	private Hashtable<Node, String> userAssociatedNodeNames;
	
	// CONSTRUCTEUR
	
	public Graph() {
		userAssociatedNodeNames = new Hashtable<Node, String>();
		maxTerminalNodeId = -1;
		shape = new LinkedList<Arc>();
		nodes = new Node[0];
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
	public Hashtable<Node, String> getUserAssociatedNodeNames() {
		return userAssociatedNodeNames;
	}
	
	
	// COMMANDES
	
	//TO SEE
	@Override
	public void removeRelatedArc(Node n) {
		int j = 0;
		for (int i = 0; i < shape.size(); ++i) {
			if (shape.get(i - j).getNodes()[0].getName() == n.getName() || shape.get(i - j).getNodes()[1].getName() == n.getName()) {
				shape.remove(i - j);
				j++;
			}
		}
	}
	
	//REMOVE
	@Override
	public void addData(int length, Node[] nodeTab) {
		maxTerminalNodeId = length;
		nodes = nodeTab;
	}
	
	@Override
	public void addTerminalNode(String nodeName) throws GraphException {
		insertNode(maxTerminalNodeId + 1, nodeName);
		maxTerminalNodeId += 1;
	}
	
	private void insertNode(int node, String name) throws GraphException {
		if (userAssociatedNodeNames.containsValue(name)) {
			throw new GraphException("Ce noeud ne peut être ajouté : il est déja présent !");
		}
		Node[] new_nodes = new Node[nodes.length + 1];

		if (node == nodes.length) {
			new_nodes[nodes.length] = new Node(node);
			userAssociatedNodeNames.put(new_nodes[nodes.length], name);
			for (int i = 0; i < nodes.length; ++i) {
				new_nodes[i] = nodes[i];
			}
			nodes = new_nodes;
			return;
		}

		for (int i = 0; i < nodes.length; ++i) {
			new_nodes[i] = nodes[i];
			if (i == node) {
				new_nodes[nodes.length] = new Node(nodes.length);
				userAssociatedNodeNames.put(new_nodes[nodes.length], convertNodeToName(nodes[i]));
				userAssociatedNodeNames.replace(nodes[i], name);
			}
		}
		
		nodes = new_nodes;
	}
	
	@Override
	public void addNode(String nodeName) throws GraphException {
		insertNode(nodes.length, nodeName);
	}
	
	@Override
	public void renameNode(String nodeNameSource, String nodeNameDest) throws GraphException {
		if (!userAssociatedNodeNames.containsValue(nodeNameSource)) {
			throw new GraphException("noeud non trouvé");
		}
		
		userAssociatedNodeNames.replace(convertNameToNode(nodeNameSource), nodeNameDest);
	}
	
	public void changeShape(LinkedList<Arc> shape) {
		this.shape = shape;
	}
	
	@Override
	public String convertNodeToName(Node node) {
		return userAssociatedNodeNames.get(node);
	}
	
	@Override
	public Node convertNameToNode(String name) {
		for (Node n : userAssociatedNodeNames.keySet()) {
			if (userAssociatedNodeNames.get(n).equals(name)) {
				return n;
			}
		}
		return null;
	}

	@Override
	public LinkedList<String> getIntersectionWith(Graph graph) {
		LinkedList<String> result = new LinkedList<String>();
		for (int i = 0; i <= maxTerminalNodeId; ++i) {
			for (int j = 0; j <= graph.getMaxTerminalNodeId(); ++j) {
				if (graph.convertNodeToName(new Node (j)) == convertNodeToName(new Node(i))) {
					result.add(convertNodeToName(new Node(i)));
				}
			}
		}
		for (int i = maxTerminalNodeId + 1; i < nodes.length; ++i) {
			for (int j = graph.getMaxTerminalNodeId() + 1; j < graph.getShape().size(); ++j) {
				if (graph.convertNodeToName(new Node (j)) == convertNodeToName(new Node(i))) {
					result.add(convertNodeToName(new Node(i)));
				}
			}
		}
		return result;
	}

	@Override
	public void makeUnionWith(IGraph iGraph) throws GraphException {
		for (int i = 0; i < iGraph.getShape().size(); ++i) {
			if (i <= iGraph.getMaxTerminalNodeId()) {
				addTerminalNode(iGraph.convertNodeToName(iGraph.getNodes()[i]));
			} else {
				addNode(iGraph.convertNodeToName(iGraph.getNodes()[i]));
			}
		}
	}

	@Override
	public void makeRemove(IGraph graph) {
		for (Node n : graph.getNodes()) {
			String name = graph.convertNodeToName(n);
			if (userAssociatedNodeNames.containsValue(name)) {
				for (Node m : nodes) {
					if (convertNodeToName(m) == name) {
						userAssociatedNodeNames.remove(m);
					}
				}
			}
		}
	}

	@Override
	public void empty() {
		userAssociatedNodeNames = new Hashtable<Node, String>();
		nodes = new Node[0];
		shape = new LinkedList<Arc>();
		maxTerminalNodeId = -1;
	}

	@Override
	public void addArc(String firstNode, String secondNode, int weight) {
		shape.add(new Arc(convertNameToNode(firstNode), convertNameToNode(secondNode), weight));
	}

	@Override
	public void removeArc(String firstNode, String secondNode) throws GraphException {
		Node node_1 = null;
		Node node_2 = null;
		for (Node n : userAssociatedNodeNames.keySet()) {
			if (userAssociatedNodeNames.get(n) == firstNode) {
				node_1 = n;
			} else if (userAssociatedNodeNames.get(n) == secondNode) {
				node_2 = n;
			}
		}
		if (node_1 == null || node_2 == null) {
			throw new GraphException("Le noeud n'as pas été trouvé !");
		}
		Arc arc = new Arc(node_1, node_2, 0);
		for (int i = 0; i < shape.size(); ++i) {
			if (arc.equalsWithoutWeight(shape.get(i))) {
				shape.remove(i);
				return;
			}
		}
		throw new GraphException("L'arc n'as pas été trouvé !");
	}


	@Override
	public void removeNode(String node) throws GraphException {
		for (int i = 0; i < nodes.length; ++i) {
			if (convertNodeToName(nodes[i]).equals(node)) {
				if (i <= maxTerminalNodeId) {
					userAssociatedNodeNames.replace(nodes[i], userAssociatedNodeNames.get(new Node(maxTerminalNodeId)));
					userAssociatedNodeNames.replace(nodes[maxTerminalNodeId], userAssociatedNodeNames.get(new Node(nodes.length - 1)));
					userAssociatedNodeNames.remove(nodes[nodes.length -1]);
					--maxTerminalNodeId;
					Node[] new_nodes = new Node[nodes.length - 1];
					for (int j = 0; j < nodes.length - 1; ++j) {
						new_nodes[j] = nodes[j];
					}
					nodes = new_nodes;
				} else {
					userAssociatedNodeNames.replace(nodes[i], userAssociatedNodeNames.get(new Node(nodes.length - 1)));
					userAssociatedNodeNames.remove(nodes[nodes.length -1]);
					Node[] new_nodes = new Node[nodes.length - 1];
					for (int j = 0; j < nodes.length - 1; ++j) {
						new_nodes[j] = nodes[j];
					}
					nodes = new_nodes;
				}
				return;
			}
		}
		throw new GraphException("Noeud introuvable !");
	}

	@Override
	public Hashtable<String, PointTime[]> getListSucc() {
		System.out.println("-- start");
		Hashtable<String, PointTime[]> map = new Hashtable<String, PointTime[]>();
		for (Node n : nodes) {
			int number = 0;
			for (Arc a : shape) {
				if (a.getNodes()[0].equals(n) || a.getNodes()[1].equals(n)) {
					++number;
				}
			}
			System.out.println("-- new Node of name : " + convertNodeToName(n) + " of size :" + number);
			PointTime[] pt = new PointTime[number];
			int i = 0;
			for (Arc a : shape) {
				if (a.getNodes()[0].equals(n)) {
					pt[i] = new PointTime(convertNodeToName(a.getNodes()[1]), a.getWeight());
					System.out.println("--arc : " + convertNodeToName(n) + " <-> " + convertNodeToName(a.getNodes()[1]) + " = " + a.getWeight());
					++i;
				}
				if (a.getNodes()[1].equals(n)) {
					pt[i] = new PointTime(convertNodeToName(a.getNodes()[0]), a.getWeight());
					System.out.println("--arc : " + convertNodeToName(n) + " <-> " + convertNodeToName(a.getNodes()[0]) + " = " + a.getWeight());
					++i;
				}
			}
			map.put(convertNodeToName(n), pt);
		}
		System.out.println("-- end");
		return map;
	}
	
}
