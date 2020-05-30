package steinerGraphJava.model;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import steinerGraphJava.algorithms.Genetique;
import steinerGraphJava.algorithms.population.PoidsPenality;
import steinerGraphJava.graph.Arc;
import steinerGraphJava.graph.Graph;
import steinerGraphJava.graph.GraphException;
import steinerGraphJava.graph.IGraph;
import steinerGraphJava.graph.graphFile.GraphFileException;
import steinerGraphJava.graph.graphFile.Translator;

@SuppressWarnings("deprecation")
public class SteinerModel extends Observable implements ISteinerModel {

	// ATTRIBUTS

	private Genetique gene;

	private IGraph graph;

	private IGraph graphOriginal;

	private boolean switched;

	private PoidsPenality res;

	private LinkedList<File> files;

	private boolean isSolved;

	private State state;
	
	private long time;
	
	private UndoRedoStack stack;


	// CONSTRUCTEUR

	public SteinerModel () {
		switched = false;
		state = State.NOT_SPECIFIED;
		isSolved = false;
		files = new LinkedList<File>();
		graph = new Graph();
		stack = new UndoRedoStack(graph);
	}


	// COMMANDES

	public void runAlgo() throws GraphException {
		if (isSolved) {
			graph = graphOriginal;
		}

		// ============ TEST ==============

		//System.out.println("Nb Terminal : " + graph.getMaxTerminalNodeId());

		// System.out.println("Tous les Sommets :");
		// for (int i = 0; i < graph.getNodes().length; i++) {
		// 	System.out.println(graph.getNodes()[i].getName());
		// }

		// System.out.println();

		// System.out.println("Tous les sommets Terminaux :");
		//for (int i = 0; i < graph.getMaxTerminalNodeId(); i++) {
		//	System.out.println(graph.getNodes()[i].getName());
		//}

		// System.out.println();

		// System.out.println("Tous les Arcs :");
		// for (int i = 0; i < graph.getShape().size(); ++i) {
		// 	System.out.println("(" + graph.getShape().get(i).getNodes()[0].getName() + ","
		// 						   + graph.getShape().get(i).getNodes()[1].getName() + ","
		// 						   + graph.getShape().get(i).getWeight() + ")");
		// }

		// ============ TEST ==============


		// PARTIE Genetique

		// True = elitiste / false = generationnel
		long timeBefore = System.currentTimeMillis();
		gene = new Genetique(graph, true);
		gene.algoGene();

		try {
			res = gene.getRes();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new GraphException("Cela est problématique !", GraphException.ErrorType.SOLVE_ARRAY_OUT_OF_BOUNDS);
		}


		//System.out.println("\nOn sort de l'algo génétique en ayant obtenue les valeurs : ");
		//System.out.println("Les arcs restants sont : " + res.getArc().size());
		//printArray(res.getArc());
		//System.out.println("Poids " + ": " + res.getPoids());
		//System.out.println("Penality : " + res.getPenality());

		Graph resGraph = new Graph();
        resGraph.addData(graph.getMaxTerminalNodeId(), graph.getNodes());
        resGraph.addHash(graph.getUserAssociatedNodeNames());
        resGraph.changeShape(res.getArc());

        graphOriginal = graph;
        graph = resGraph;
		long timeAfter = System.currentTimeMillis();
		time = timeAfter - timeBefore;
	}

	// OUTILS

	public void printArray(LinkedList<Arc> res) {
		for (int i = 0; i < res.size(); ++i) {
			System.out.println("(" + res.get(i).getNodes()[0].getName() + ","
								   + res.get(i).getNodes()[1].getName() + ","
								   + res.get(i).getWeight() + ")");
		}
	}


	@Override
	public IGraph getGraph() {
		if (switched) {
			return graphOriginal;
		} else {
			return graph;
		}
	}


	@Override
	public void addFile(File selectedFile) throws GraphException {
		try {
			graph.makeUnionWith(Translator.readGraph(selectedFile));
		} catch (GraphFileException e) {
			throw new GraphException("Erreur d'entrée / sortie", GraphException.ErrorType.IO_ERROR);
		}
		files.add(selectedFile);
		stack.doIt(graph);
		change();
	}


	@Override
	public List<String> getFileNames() {
		LinkedList<String> filesString = new LinkedList<String>();
		for (File f : files) {
			filesString.add(f.getName() + "\t\t" + f.getAbsolutePath());
		}
		return filesString;
	}


	@Override
	public void removeFileAtIndex(Integer correspondingIndex) throws GraphException {
		try {
			graph.makeRemove(Translator.readGraph(files.get(correspondingIndex)));
		} catch (GraphFileException e) {
			throw new GraphException("Erreur d'entrée / sortie", GraphException.ErrorType.IO_ERROR);
		}
		stack.doIt(graph);
		change();
	}


	@Override
	public void saveFileTo(File selectedFile) throws GraphException {
		try {
			Translator.writeGraph((IGraph) graph, selectedFile);
		} catch (GraphFileException e) {
			throw new GraphException("Erreur d'entrée / sortie", GraphException.ErrorType.IO_ERROR);
		}
	}


	@Override
	public void undo() {
		graph = stack.undoIt();
		change();
	}


	@Override
	public void redo() {
		graph = stack.redoIt();
		change();
	}


	@Override
	public void emptyGraph() {
		graph.empty();
		stack.doIt(graph);
		change();
	}


	@Override
	public void addElement(String answer) throws GraphException {
		boolean arcMode = false;
		boolean thisIsSecondNode = false;
		boolean thisIsWeight = false;
		String firstNode = "";
		String secondNode = "";
		String weight = "";

		for (int i = 0; i < answer.length(); ++i) {
			if (answer.charAt(i) == ' ' ) {
				if (!arcMode) {
					if (answer.length() < i + 4 || answer.charAt(i + 1) != '-'
							|| answer.charAt(i + 2) != ' ' || answer.charAt(i + 3) == ' ') {
						throw new GraphException("La syntaxe est invalide !", GraphException.ErrorType.ADD_ELEMENT_INVALID_SYNTAX);
					}
					i += 2;
					thisIsSecondNode = true;
					arcMode = true;
				} else {
					if (thisIsWeight || answer.length() < i + 4 || answer.charAt(i + 1) != ':'
							|| answer.charAt(i + 2) != ' ' || answer.charAt(i + 3) == ' ') {
						throw new GraphException("La syntaxe est invalide !", GraphException.ErrorType.ADD_ELEMENT_INVALID_SYNTAX);
					}
					i += 2;
					thisIsSecondNode = false;
					thisIsWeight = true;
				}
			} else {
				if (thisIsSecondNode) {
					secondNode += answer.charAt(i);
				} else if (thisIsWeight) {
					weight += answer.charAt(i);
				} else {
					firstNode += answer.charAt(i);
				}
			}
		}
		if (arcMode) {
			graph.addArc(firstNode, secondNode, Integer.parseInt(weight));
		} else {
			if (firstNode.length() > 2 && firstNode.charAt(0) == '[' && firstNode.charAt(1) == 'T' && firstNode.charAt(2) == ']') {
				graph.addTerminalNode(firstNode);
			} else {
				graph.addNode(firstNode);
			}
		}
		stack.doIt(graph);
		change();
	}


	@Override
	public void removeElement(String answer) throws GraphException {
		boolean arcMode = false;
		boolean thisIsSecondNode = false;
		String firstNode = "";
		String secondNode = "";

		for (int i = 0; i < answer.length(); ++i) {
			if (answer.charAt(i) == ' ' ) {
				if (!arcMode) {
					if (answer.length() < i + 4 || answer.charAt(i + 1) != '-'
							|| answer.charAt(i + 2) != ' ' || answer.charAt(i + 3) == ' ') {
						throw new GraphException("La syntaxe est invalide !", GraphException.ErrorType.ADD_ELEMENT_INVALID_SYNTAX);
					}
					i += 2;
					thisIsSecondNode = true;
					arcMode = true;
				} else {
					throw new GraphException("Erreur de syntaxe", GraphException.ErrorType.ADD_ELEMENT_INVALID_SYNTAX);
				}
			} else {
				if (thisIsSecondNode) {
					secondNode += answer.charAt(i);
				} else {
					firstNode += answer.charAt(i);
				}
			}
		}
		if (arcMode) {
			graph.removeArc(firstNode, secondNode);
		} else {
			graph.removeNode(firstNode);
		}
		stack.doIt(graph);
		change();
	}


	@Override
	public boolean checkNodeExist(String answerSource) {
		return graph.getUserAssociatedNodeNames().containsValue(answerSource);
	}


	@Override
	public void renameNode(String answerSource, String answerDest) throws GraphException {
		graph.renameNode(answerSource, answerDest);
		stack.doIt(graph);
		change();
	}


	@Override
	public void solve() throws GraphException {
		runAlgo();
		isSolved = true;
        change();
	}


	@Override
	public void switchGraphToOriginal() throws GraphException {
		if (!isSolved) {
			throw new GraphException("Ceci est déja l'original !", GraphException.ErrorType.SEE_ALREADY_ORIGINAL);
		}
		switched = !switched;
		change();
	}


	@Override
	public boolean canUndo() {
		return stack.canUndo();
	}


	@Override
	public boolean canRedo() {
		return stack.canRedo();
	}


	@Override
	public boolean isSolved() {
		return isSolved;
	}


	@Override
	public String getTimeToSolve() {
		return Long.toString(time);
	}

	private void change() {
		setChanged();
		notifyObservers(state);
	}

  @Override
  public String getPenality() {
    return Integer.toString(res.getPenality());
  }

  @Override
  public String getPoids() {
    return Integer.toString(res.getPoids());
  }
}
