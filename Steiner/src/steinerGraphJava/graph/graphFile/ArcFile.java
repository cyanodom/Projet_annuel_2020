package steinerGraphJava.graph.graphFile;

public class ArcFile {
	private String first;
	private String second;
	private int weight;
	
	public ArcFile(String first, String second, int weight) {
		this.first  = first;
		this.second = second;
		this.weight = weight;
	}
	
	public String getFirst() {
		return first;
	}
	
	public String getSecond() {
		return second;
	}
	
	public int getWeight() {
		return weight;
	}
 }