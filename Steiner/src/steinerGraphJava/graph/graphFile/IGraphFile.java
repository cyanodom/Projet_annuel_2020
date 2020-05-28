package steinerGraphJava.graph.graphFile;

/**
 * This Interface is made to model a class that contains informations of a graph file.
 * 
 * @inv <pre> 
 * 		getNodeNb() == getNodeNames().length
 * 		forall ArcFile af : getArcFiles() =>
 * 			af.getFirst() is in getNodeNames() && af.getSecond() is in getNodeNames() </pre>
 * @cons <pre>
 * 		$ARGS$ File f
 * 		$POST$ requests return values are in coordination with the contents of f</pre>
 */

public interface IGraphFile {
	
	//REQUESTS :
	
	public int getNumberNodes();
	
	public String[] getNodeNames();
	
	public ArcFile[] getArcFiles();
}
