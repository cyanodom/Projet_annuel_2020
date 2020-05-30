package steinerGraphJava.model;

import java.util.Stack;

import steinerGraphJava.graph.IGraph;

class UndoRedoStack {
	private Stack<IGraph> undoStack;
	private Stack<IGraph> redoStack;
	private IGraph actualGraph;
	
	private static final int MAX_UNDO_REDO_STACK_SIZE = 500;
	
	UndoRedoStack(IGraph g){
		undoStack = new Stack<IGraph>();
		redoStack = new Stack<IGraph>();
		actualGraph = g.clone();
	}
	
	void doIt(IGraph g) {
		undoStack.push(actualGraph);
		actualGraph = g.clone();
		redoStack.clear();
		if (undoStack.size() >= MAX_UNDO_REDO_STACK_SIZE) {
			undoStack.remove(0);
		}
	}
	
	IGraph undoIt() {
		redoStack.push(actualGraph);
		actualGraph = undoStack.pop();
		return actualGraph.clone();
	}
	
	IGraph redoIt() {
		undoStack.push(actualGraph);
		actualGraph = redoStack.pop();
		return actualGraph.clone();
	}
	
	boolean canUndo() {
		return !(undoStack.isEmpty());
	}
	
	boolean canRedo() {
		return !(redoStack.isEmpty());
	}
 }
