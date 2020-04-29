package steinerGraphJava.graph;

import java.io.Serializable;

public class Node implements Serializable {
	private int name;
	
	public Node(int name) {
		this.name = name;
	}
	
	public int getName() {
		return name;
	}
}
