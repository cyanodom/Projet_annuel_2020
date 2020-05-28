package steinerGraphJava.model;

import steinerGraphJava.graph.IGraph;
import steinerGraphJava.model.ObservableModel;

public interface ISteinerModel extends ObservableModel {

	
	enum State {
		SOLVED,
		FILE_CHANGED,
		GRAPH_CHANGED;
	}

	IGraph getGraph();
}
