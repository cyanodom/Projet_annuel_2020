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
import steinerGraphJava.graph.graphFile.Translator;

public class SteinerModel extends Observable implements ISteinerModel {

	// ATTRIBUTS

	private Genetique gene;
	
	private Graph graph;
	
	private Graph graphOriginal;
	
	private boolean switched;
	
	private PoidsPenality res;
	
	private LinkedList<File> files;
	
	private boolean isSolved;
	
	private State state;


	// CONSTRUCTEUR

	public SteinerModel () {
		switched = false;
		state = State.NOT_SPECIFIED;
		isSolved = false;
		files = new LinkedList<File>();
		graph = new Graph();
	}


	// COMMANDES

	public void runAlgo() {
		
		// ============ TEST ==============
		
		System.out.println("Nb Terminal : " + graph.getMaxTerminalNodeId());
		
		// System.out.println("Tous les Sommets :");
		// for (int i = 0; i < graph.getNodes().length; i++) {
		// 	System.out.println(graph.getNodes()[i].getName());
		// }

		// System.out.println();
		
		// System.out.println("Tous les sommets Terminaux :");
		for (int i = 0; i < graph.getMaxTerminalNodeId(); i++) {
			System.out.println(graph.getNodes()[i].getName());
		}
		
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
		gene = new Genetique(graph, true);
		gene.algoGene();
		
		res = gene.getRes();
		
		
		// System.out.println("\nOn sort de l'algo génétique en ayant obtenue les valeurs : ");
		System.out.println("Les arcs restants sont : "+res.getArc().size());
		printArray(res.getArc());
		System.out.println("Poids " + ": " + res.getPoids());
		System.out.println("Penality : " + res.getPenality());
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
		graph.makeUnionWith(Translator.trans(selectedFile));
		files.add(selectedFile);
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
	public void removeFileAtIndex(Integer correspondingIndex) {
		graph.makeRemove(Translator.trans(files.get(correspondingIndex)));
	}


	@Override
	public void saveFileTo(File selectedFile) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void emptyGraph() {
		graph.empty();
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
						throw new GraphException("La syntaxe est invalide !");
					}
					i += 2;
					thisIsSecondNode = true;
					arcMode = true;
				} else {
					if (thisIsWeight || answer.length() < i + 4 || answer.charAt(i + 1) != ':' 
							|| answer.charAt(i + 2) != ' ' || answer.charAt(i + 3) == ' ') {
						throw new GraphException("La syntaxe est invalide !");
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
			change();
		} else {
			if (firstNode.length() > 2 && firstNode.charAt(0) == '[' && firstNode.charAt(1) == 'T' && firstNode.charAt(2) == ']') {
				graph.addTerminalNode(firstNode);
				change();
			} else {
				graph.addNode(firstNode);
				change();
			}
		}
		
	}


	@Override
	public void removeElement(String answer) throws GraphException {
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
						throw new GraphException("La syntaxe est invalide !");
					}
					i += 2;
					thisIsSecondNode = true;
					arcMode = true;
				} else {
					if (thisIsWeight || answer.length() < i + 4 || answer.charAt(i + 1) != ':' 
							|| answer.charAt(i + 2) != ' ' || answer.charAt(i + 3) == ' ') {
						throw new GraphException("La syntaxe est invalide !");
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
			graph.removeArc(firstNode, secondNode);
			change();
		} else {
			graph.removeNode(firstNode);
			change();
		}		
	}


	@Override
	public boolean checkNodeExist(String answerSource) {
		return graph.getUserAssociatedNodeNames().containsValue(answerSource);
	}


	@Override
	public void renameNode(String answerSource, String answerDest) throws GraphException {
		graph.renameNode(answerSource, answerDest);
		change();
	}


	@Override
	public void solve() {
		isSolved = true;
		runAlgo();
	}


	@Override
	public void switchGraphToOriginal() throws GraphException {
		if (!isSolved) {
			throw new GraphException("Ceci est déja l'original !");
		}
		switched = true;
		change();
	}


	@Override
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean canRedo() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isSolved() {
		return isSolved;
	}


	@Override
	public Integer getNbModification() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Integer getTimeToSolve() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getEfficiency() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void change() {
		setChanged();
		notifyObservers(state);
	}
}
