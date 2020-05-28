package steinerGraphJava.model;

import java.io.File;
import java.util.List;

import steinerGraphJava.graph.IGraph;
import steinerGraphJava.model.ObservableModel;

public interface ISteinerModel extends ObservableModel {
	
	enum State {
		SOLVED,
		FILE_CHANGED,
		GRAPH_CHANGED;
	}

	IGraph getGraph();

	void addFile(File selectedFile);

	List<String> getFileNames();

	void removeFileAtIndex(Integer correspondingIndex);

	void saveFileTo(File selectedFile);

	void undo();

	void redo();

	void emptyGraph();

	void addElement(String answer);

	void removeElement(String answer);

	boolean checkNodeExist(String answerSource);

	void renameElement(String answerSource, String answerDest);

	void solve();

	void switchGraphToOriginal();

	boolean canUndo();

	boolean canRedo();

	boolean isSolved();

	Integer getNbModification();

	Integer getTimeToSolve();

	Integer getEfficiency();
}
