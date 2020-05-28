package steinerGraphJava.graph.graphFile;

/**
 * This interface is made to model a previously read Arc from a GraphFile
 * @cons <pre>
 * 		$ARGS$ String first, String second, String, int weight </pre>
 *
 */

public interface IArcFile {
	public String getFirst();
	
	public String getSecond(); 
	
	public int getWeigth();
 }