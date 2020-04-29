package steinerGraphJava.graph;

import java.io.Serializable;

public class Arc implements Serializable {
	private int weight;
	private Node[] nodes;
	
	public Arc(Node first, Node second, int weight) {
		nodes = new Node[2];
		nodes[0] = first;
		nodes[1] = second;
		this.weight = weight;
	}
	
	public Node[] getNodes() {
		return nodes;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void changeWeight(int i) {
		weight = i;
	}
}
