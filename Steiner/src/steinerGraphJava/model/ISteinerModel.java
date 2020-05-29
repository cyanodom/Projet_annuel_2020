package steinerGraphJava.model;

import java.io.File;
import java.util.List;

import steinerGraphJava.graph.GraphException;
import steinerGraphJava.graph.IGraph;
import steinerGraphJava.model.ObservableModel;

public interface ISteinerModel extends ObservableModel {
	
	enum State {
		NOT_SPECIFIED,
		FILE_CHANGED,
		GRAPH_CHANGED;
	}

	IGraph getGraph();

	void addFile(File selectedFile) throws GraphException;

	List<String> getFileNames();

	void removeFileAtIndex(Integer correspondingIndex);

	void saveFileTo(File selectedFile);

	void undo();

	void redo();

	void emptyGraph();

	void addElement(String answer) throws GraphException;

	void removeElement(String answer) throws GraphException;

	boolean checkNodeExist(String answerSource);

	void solve();

	void switchGraphToOriginal() throws GraphException;

	boolean canUndo();

	boolean canRedo();

	boolean isSolved();

	Integer getNbModification();

	Integer getTimeToSolve();

	Integer getEfficiency();

	void renameNode(String answerSource, String answerDest) throws GraphException;
}
