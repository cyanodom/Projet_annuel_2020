package steinerGraphJava.graph;

import java.io.Serializable;

public class Node implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -924758096838125298L;
	private int name;
	
	public Node(int name) {
		this.name = name;
	}
	
	public int getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Node)) {
			return false;
		}
		Node that = (Node) o;
		return name == that.name;
	}
}
