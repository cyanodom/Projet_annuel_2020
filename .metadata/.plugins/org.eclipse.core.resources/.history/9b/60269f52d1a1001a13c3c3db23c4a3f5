package steinerGraphJava.graph;

public class Arc {
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
	
	@Override
	public boolean equals(Object arc) {
		if (arc == null || !(arc instanceof Arc)) {
			return false;
		}
		Arc that = (Arc) arc;
		return ((that.getNodes()[0] == nodes[0] && that.getNodes()[1] == nodes[1])
				|| (that.getNodes()[1] == nodes[0] && that.getNodes()[0] == nodes[1])) && that.getWeight() == weight;
	}
	
	public boolean equalsWithoutWeight(Object arc) {
		if (arc == null || !(arc instanceof Arc)) {
			return false;
		}
		Arc that = (Arc) arc;
		return (that.getNodes()[0] == nodes[0] && that.getNodes()[1] == nodes[1])
				|| (that.getNodes()[1] == nodes[0] && that.getNodes()[0] == nodes[1]);
	}
}
