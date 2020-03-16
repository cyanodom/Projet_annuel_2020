package steinerGraphJava.graph;

public class Arc {
	private int weight;
	private Node[] nodes;
	
	public Arc(Node first, Node second, int weight) {
		nodes = new Node[2];
		nodes[0] = first;
		nodes[1] = second;
	}
	
	public Node[] getNodes() {
		return nodes;
	}
	
	public int getWeight() {
		return weight;
	}
}
