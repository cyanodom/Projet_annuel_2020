package steinerGraphJava.graph.graphFile;

import java.io.File;

public class GraphFile implements IGraphFile {
	// ARGUMENTS :
	
	private String[] nodeNames;
	private ArcFile[] arcFiles;
	
	// CONSTRUCTOR :
	
	private GraphFile(File f) throws GraphFileException {
		loadFile(f);
	}

	// REQUESTS

	@Override
	public int getNumberNodes() {
		return nodeNames.length;
	}

	@Override
	public String[] getNodeNames() {
		return nodeNames;
	}

	@Override
	public ArcFile[] getArcFiles() {
		return arcFiles;
	}
	// OUTILS
	
	private void loadFile(File f) throws GraphFileException {
		int state;
		
		
	}

}
