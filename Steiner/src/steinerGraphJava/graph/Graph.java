package steinerGraphJava.graph;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import steinerGraphJava.graphics.PointTime;

public class Graph implements IGraph, Serializable {

	// ATTRIBUTS

	/**
	 *
	 */
	private static final long serialVersionUID = -3574766666525944701L;
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
	
	public Graph(IGraph graph) {
		this();
		for (Node n : graph.getNodes()) {
			try {
				addNode(graph.convertNodeToName(n));
			} catch (GraphException e) {
			}
		}
		for (Arc a : graph.getShape()) {
			try {
				addArc(graph.convertNodeToName(a.getNodes()[0]), graph.convertNodeToName(a.getNodes()[1]), a.getWeight());
			} catch (GraphException e) {
			}
		}
	}

	@Override
	public IGraph clone() {
		IGraph g = new Graph(this);
		return g;
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

	@Override
	public void addData(int length, Node[] nodeTab) {
		maxTerminalNodeId = length - 1;
		nodes = nodeTab;
	}

	@Override
	public void addTerminalNode(String nodeName) throws GraphException {
		insertNode(maxTerminalNodeId + 1, nodeName);
		maxTerminalNodeId += 1;
	}

	private void insertNode(int node, String name) throws GraphException {
		if (userAssociatedNodeNames.containsValue(name)) {
			throw new GraphException(name + " Ce noeud ne peut être ajouté : il est déja présent !", 
					GraphException.ErrorType.INSERT_NODE_ALREADY_IN);
		}
		
		Node[] new_nodes = new Node[nodes.length + 1];

		if (node == nodes.length) {
			new_nodes[nodes.length] = new Node(node + 1);
			userAssociatedNodeNames.put(new_nodes[nodes.length], name);
			for (int i = 0; i < nodes.length; ++i) {
				new_nodes[i] = nodes[i];
			}
			nodes = new_nodes;
			return;
		}

		for (int i = 0; i < nodes.length; ++i) {
			if (i == node) {
				Node endNode = new Node(new_nodes.length);
				new_nodes[new_nodes.length - 1] = endNode;
				System.out.println(nodes[i]);
				System.out.println(maxTerminalNodeId);
				userAssociatedNodeNames.put(endNode, convertNodeToName(nodes[i]));
				userAssociatedNodeNames.replace(nodes[i], name);
				for (Arc a : getShape()) {
					if (a.getNodes()[0].equals(nodes[i])) {
						a.getNodes()[0] = endNode;
					}
					if (a.getNodes()[1].equals(nodes[i])) {
						a.getNodes()[1] = endNode;
					}
				}
			}
			new_nodes[i] = nodes[i];
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
			throw new GraphException("noeud non trouvé", GraphException.ErrorType.RENAME_BUT_NODE_NOT_FOUND);
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
				if (graph.convertNodeToName(new Node (j)) == convertNodeToName(new Node(i + 1))) {
					result.add(convertNodeToName(new Node(i)));
				}
			}
		}
		for (int i = maxTerminalNodeId; i < nodes.length; ++i) {
			for (int j = graph.getMaxTerminalNodeId(); j < graph.getShape().size(); ++j) {
				if (graph.convertNodeToName(new Node (j)) == convertNodeToName(new Node(i + 1))) {
					result.add(convertNodeToName(new Node(i + 1)));
				}
			}
		}
		return result;
	}

	@Override
	public void makeUnionWith(IGraph iGraph) throws GraphException {
		for (int i = 0; i < iGraph.getNodes().length; ++i) {
			if (i <= iGraph.getMaxTerminalNodeId()) {
				addTerminalNode(iGraph.convertNodeToName(iGraph.getNodes()[i]));
			} else {
				addNode(iGraph.convertNodeToName(iGraph.getNodes()[i]));
			}
		}
		for (Arc a : iGraph.getShape()) {
			addArc(iGraph.convertNodeToName(a.getNodes()[0]), iGraph.convertNodeToName(a.getNodes()[1]), a.getWeight());
		}
	}

	@Override
	public void makeRemove(IGraph graph) throws GraphException {
		for (Node n : graph.getNodes()) {
			String name = graph.convertNodeToName(n);
			if (userAssociatedNodeNames.containsValue(name)) {
				try {
					removeNode(name);
				} catch (GraphException e) {
					throw new GraphException("Ceci n'est pas censé arriver", GraphException.ErrorType.ASSERTION_ERROR);
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
	public void addArc(String firstNode, String secondNode, int weight) throws GraphException {
		Arc newArc = new Arc(convertNameToNode(firstNode), convertNameToNode(secondNode), weight);
		for (Arc a : shape) {
			if (a.equals(newArc)) {
				throw new GraphException("Cet Arc est déja existant !", GraphException.ErrorType.INSERT_NODE_ALREADY_IN);
			}
		}
		shape.add(newArc);
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
			throw new GraphException("Le noeud n'as pas été trouvé !", GraphException.ErrorType.REMOVE_NODE_NOT_FOUND);
		}
		Arc arc = new Arc(node_1, node_2, 0);
		for (int i = 0; i < shape.size(); ++i) {
			if (arc.equalsWithoutWeight(shape.get(i))) {
				shape.remove(i);
				return;
			}
		}
		throw new GraphException("L'arc n'as pas été trouvé !", GraphException.ErrorType.REMOVE_ARC_NOT_FOUND);
	}


	@Override
	public void removeNode(String node) throws GraphException {
		for (int i = 0; i < nodes.length; ++i) {
			if (convertNodeToName(nodes[i]).equals(node)) {
				int toReplace;
				Iterator<Arc> itera = getShape().iterator();
				while (itera.hasNext()) {
					Arc a = itera.next();
					if (a.getNodes()[0].equals(nodes[i]) || a.getNodes()[1].equals(nodes[i])) {
						itera.remove();
					}
				}
				if (i < maxTerminalNodeId) {
					userAssociatedNodeNames.replace(nodes[i], convertNodeToName(nodes[maxTerminalNodeId]));
					for (Arc a : getShape()) {
						if (a.getNodes()[0].equals(nodes[maxTerminalNodeId])) {
							a.getNodes()[0] = nodes[i];
						}
						if (a.getNodes()[1].equals(nodes[maxTerminalNodeId])) {
							a.getNodes()[1] = nodes[i];
						}
					}
					toReplace = maxTerminalNodeId;
					--maxTerminalNodeId;
				} else {
					toReplace = i;
				}
				if (toReplace != nodes.length - 1) {
					userAssociatedNodeNames.replace(nodes[toReplace], convertNodeToName(nodes[nodes.length - 1]));
					userAssociatedNodeNames.remove(nodes[nodes.length - 1]);
					for (Arc a : getShape()) {
						if (a.getNodes()[0].equals(nodes[nodes.length - 1])) {
							a.getNodes()[0] = nodes[toReplace];
						}
						if (a.getNodes()[1].equals(nodes[nodes.length - 1])) {
							a.getNodes()[1] = nodes[toReplace];
						}
					}
				} else {
					userAssociatedNodeNames.remove(nodes[nodes.length - 1]);
				}
				Node[] new_nodes = new Node[nodes.length - 1];
				for (int j = 0; j < nodes.length - 1; ++j) {
					new_nodes[j] = nodes[j];
				}
				nodes = new_nodes;
				return;
			}
		}
		throw new GraphException("Noeud introuvable !", GraphException.ErrorType.REMOVE_NODE_NOT_FOUND);
	}

	@Override
	public Hashtable<String, PointTime[]> getListSucc() {
		Hashtable<String, PointTime[]> map = new Hashtable<String, PointTime[]>();
		for (Node n : nodes) {
			int number = 0;
			for (Arc a : shape) {
				if (a.getNodes()[0].equals(n) || a.getNodes()[1].equals(n)) {
					++number;
				}
			}
			PointTime[] pt = new PointTime[number];
			int i = 0;
			for (Arc a : shape) {
				if (a.getNodes()[0].equals(n)) {
					pt[i] = new PointTime(convertNodeToName(a.getNodes()[1]), a.getWeight());
					++i;
				}
				if (a.getNodes()[1].equals(n)) {
					pt[i] = new PointTime(convertNodeToName(a.getNodes()[0]), a.getWeight());
					++i;
				}
			}
			map.put(convertNodeToName(n), pt);
		}
		return map;
	}
	
	@Override
	public int weightArc(Node firstNode, Node secondNode) {
        Arc newArc = new Arc(firstNode, secondNode, 0);
        for (Arc a : getShape()) {
            if (a.equalsWithoutWeight(newArc)) {
                return a.getWeight();
            }
         }
		return 0;
    }

	public void addHash(Hashtable<Node, String> userAssociatedNodeNames2) {
		userAssociatedNodeNames = userAssociatedNodeNames2;
	}

}
